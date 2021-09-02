package com.example.meeting.service;

import com.example.meeting.entities.Reunion;
import com.example.meeting.entities.Salle;
import com.example.meeting.entities.TypeReunion;
import com.example.meeting.repository.RepoReunion;
import com.example.meeting.repository.RepoSalle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class ReunionServiceImpl implements IReunionServices {
    private static final Logger LOG = LoggerFactory.getLogger(ReunionServiceImpl.class);

    private int pieuvres = 4 ;
    private int ecrans = 5 ;
    private int webcams = 4 ;
    private int tableaux = 2 ;

    @Autowired
    SalleServiceImpl salleService;
    @Autowired
    RepoReunion repoReunion;

    @Override
    public String ajoutReunion(Reunion reunion) {

        // msg de return
        String msg ="Ajout valid !";
        // chercher la salle apartir de la reunion
        Salle salle = salleService.chercherSalle(reunion.getSalle().getId());
        //ajout static d'une Heure finReservation = debutReservation + 1H
        reunion.setFinReservation(reunion.getDebutReservation().plusHours(1));
        LOG.info("*******"+reunion.getFinReservation());
        // Filter pour recouper tous les réservations de la salle puis vérification sur la date si elle est déjà réservé ou une heure avant si elle est reservé ! return true s'il y a une réservation, false dans le cas contraire
        Boolean founddate = repoReunion.findAll().stream()
                .filter(e->e.getSalle().getId() == reunion.getSalle().getId())
                .anyMatch(e->e.getDebutReservation().equals(reunion.getDebutReservation()) || e.getFinReservation().equals(reunion.getDebutReservation().getHour()-1));

        List<String> listEquipement= salle.getEquipements();
        LOG.info("*********** listequipement  ********"+listEquipement);
        LOG.info("*********** TypeReunion ********"+reunion.getTypeReunion());

        //Verification des equipements selon le type de la reunion
        if(reunion.getTypeReunion().equals(TypeReunion.VC)) {
            LOG.info("*********** type reservation   VC ********");
            if( ecrans ==0 || pieuvres ==0 || webcams ==0){
                msg = "Stock insuffisant  ecrans: "+ecrans+" pieuvres: "+pieuvres+" webcams: "+webcams;
            } else {
                if(!listEquipement.stream().anyMatch(e ->e.contains("Ecran"))){
                    listEquipement.add("Ecran");
                    ecrans--;
                }
                if(!listEquipement.stream().anyMatch(e ->e.contains("Pieuvre")) ){
                    listEquipement.add("Pieuvre");
                    pieuvres--;
                }
                if(!listEquipement.stream().anyMatch(e ->e.contains("Webcam")) ){
                    listEquipement.add("Webcam");
                    webcams--;
                }
                LOG.info("*********** listequipement  ********"+listEquipement);
                salle.setEquipements(listEquipement);
            }

        }else if(reunion.getTypeReunion().equals(TypeReunion.SPEC)) {
            LOG.info("*********** type reservation SPEC ********");
            if(tableaux ==0){
                msg = "Stock insuffisant de tableau: "+tableaux;
            }else if(!listEquipement.stream().anyMatch(e ->e.contains("Tableau"))){
                listEquipement.add("Tableau");
                tableaux--;
                reunion.getSalle().setEquipements(listEquipement);
            }

        }else if(reunion.getTypeReunion().equals(TypeReunion.RS)) {
            LOG.info("*********** type reservation   RS ********");
            reunion.setNbrPresonne(reunion.getNbrPresonne()+3);

        }else if(reunion.getTypeReunion().equals(TypeReunion.RC)){
            LOG.info("*********** type reservation   RC ********");
            if( ecrans ==0 || pieuvres ==0 || tableaux ==0){
                msg = "Stock insuffisants  ecrans: "+ecrans+" pieuvres: "+pieuvres+" tableaux: "+tableaux;
            }else {
                if (!listEquipement.stream().anyMatch(e -> e.contains("Ecran"))) {
                    listEquipement.add("Ecran");
                    ecrans--;
                }
                if (!listEquipement.stream().anyMatch(e -> e.contains("Pieuvre")) ) {
                    listEquipement.add("Pieuvre");
                    pieuvres--;
                }
                if (!listEquipement.stream().anyMatch(e -> e.contains("Tableau")) ) {
                    listEquipement.add("Tableau");
                    tableaux--;
                }
                reunion.getSalle().setEquipements(listEquipement);
            }
        }
        // Weekend non inclus
         if (reunion.getDebutReservation().getDayOfWeek().equals("SATURDAY") || reunion.getDebutReservation().getDayOfWeek().equals("SUNDAY")){
            LOG.error("pas de reservation durant le Weekend ! "+reunion.getDebutReservation().getDayOfWeek());
            msg ="pas de reservation durant le Weekend ! "+reunion.getDebutReservation().getDayOfWeek() ;
        } if(8 > reunion.getDebutReservation().getHour() || reunion.getFinReservation().getHour() >20){
            LOG.error("Heure doit etre entre 8 et 20 ! "+reunion.getDebutReservation().getHour() );
            msg ="Heure doit etre entre 8 et 20 ! "+reunion.getDebutReservation().getHour() ;
        } if(founddate){
            LOG.error("1H avant une 2eme reunion  ! "+reunion.getDebutReservation().getHour());
            msg ="1H avant une 2eme reunion  ! "+reunion.getDebutReservation().getHour() ;
        } if(reunion.getNbrPresonne() > (salle.getNbrPlace() * 70)/100){
            LOG.error("Nombre de place  "+reunion.getNbrPresonne());
            LOG.error("Nombre de place limte "+(salle.getNbrPlace() * 70)/100);
            msg ="Nombre de place limte "+(salle.getNbrPlace() * 70)/100;
        }
         if(msg.contains("Ajout valid !")){
            LOG.info("les equipement restants pieuvres: "+pieuvres+" Ecran : "+ecrans+" WebCam : "+webcams+" Tableau : "+tableaux);
            repoReunion.save(reunion);
            LOG.info("salle "+salle);
            List<Reunion> reunions = salle.getReunion();
            reunions.add(reunion);
            salle.setReunion(reunions);
            // MAJ de l'entité Salle apres l'ajout du reunion
            salleService.ajoutSalle(salle);
        }
        return msg;
    }
    @Override
    public String supprimeReunion(long idReunion) {
        Reunion reunion =   repoReunion.findById(idReunion).orElse(null);
        try{
            repoReunion.delete(reunion);
        }
        catch (Exception e) {
            LOG.error("Error Salle not found  : " + e);
        }
        return reunion.toString();
    }

    @Override
    public List<Reunion> afficheReunion() {

        return repoReunion.findAll();
    }

    Reunion reunion;
    @Override
    public Reunion chercherReunion(long idReunion) {
        try{
            reunion=  repoReunion.findById(idReunion).orElse(null);
        }
        catch (Exception e) {
            LOG.error("Error Salle not found  : " + e);
        }
        return reunion;
    }
}
