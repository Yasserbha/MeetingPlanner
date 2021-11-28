package com.example.meeting.service;

import com.example.meeting.dto.ReunionRequestDTO;
import com.example.meeting.dto.ReunionResponseDTO;
import com.example.meeting.dto.SalleRequestDTO;
import com.example.meeting.dto.SalleResponseDTO;
import com.example.meeting.entities.Reunion;
import com.example.meeting.entities.TypeReunion;
import com.example.meeting.mappers.ReunionMapper;
import com.example.meeting.repositories.RepoReunion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    private ReunionMapper reunionMapper;

    public ReunionServiceImpl(ReunionMapper reunionMapper) {
        this.reunionMapper = reunionMapper;
    }

    @Override
    public String ajoutReunion(ReunionRequestDTO reunionRequestDTO) {
        Reunion reunion = reunionMapper.reunionRequestDTOtoReunion(reunionRequestDTO);
        // msg de return
        String msg ="Ajout valid !";
        // chercher la salle apartir de la reunion
        SalleResponseDTO salleResponseDTO = salleService.chercherSalle(reunion.getSalle().getId());
        //ajout static d'une Heure finReservation = debutReservation + 1H
        reunion.setFinReservation(reunion.getDebutReservation().plusHours(1));
        LOG.info("*******"+reunion.getFinReservation());
        // Filter pour recouper tous les réservations de la salle puis vérification sur la date si elle est déjà réservé ou une heure avant si elle est reservé ! return true s'il y a une réservation, false dans le cas contraire
        Boolean founddate = repoReunion.findAll().stream()
                .filter(e->e.getSalle().getId() == reunion.getSalle().getId())
                .anyMatch(e->e.getDebutReservation().equals(reunion.getDebutReservation()) || e.getFinReservation().equals(reunion.getDebutReservation().getHour()-1));

        List<String> listEquipement= salleResponseDTO.getEquipements();
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
                salleResponseDTO.setEquipements(listEquipement);
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
        } if(reunion.getNbrPresonne() > (salleResponseDTO.getNbrPlace() * 70)/100){
            LOG.error("Nombre de place  "+reunion.getNbrPresonne());
            LOG.error("Nombre de place limte "+(salleResponseDTO.getNbrPlace() * 70)/100);
            msg ="Nombre de place limte "+(salleResponseDTO.getNbrPlace() * 70)/100;
        }
         if(msg.contains("Ajout valid !")){
            LOG.info("les equipement restants pieuvres: "+pieuvres+" Ecran : "+ecrans+" WebCam : "+webcams+" Tableau : "+tableaux);
            repoReunion.save(reunion);
             msg =reunion.toString();
             LOG.info("msg *************** "+reunionMapper.reunionToReunionResponseDTO(reunion));

             LOG.info("salle "+salleResponseDTO);
            List<Reunion> reunions = salleResponseDTO.getReunion();
            reunions.add(reunion);
             salleResponseDTO.setReunion(reunions);
            // MAJ de l'entité Salle apres l'ajout du reunion
             SalleRequestDTO salleRequestDTO = new SalleRequestDTO();
             salleRequestDTO.setReunion(salleResponseDTO.getReunion());
             salleRequestDTO.setId(salleResponseDTO.getId());
             salleRequestDTO.setName(salleResponseDTO.getName());
             salleRequestDTO.setNbrPlace(salleResponseDTO.getNbrPlace());
             salleRequestDTO.setEquipements(salleResponseDTO.getEquipements());
            salleService.ajoutSalle(salleRequestDTO);
        }
        return msg;
    }
    @Override
    public ReunionResponseDTO supprimeReunion(long idReunion) {
        LOG.info("*********** iD ***** "+idReunion);
       Reunion reunion =   repoReunion.findById(idReunion).orElse(null);
        try{
            repoReunion.deleteById(idReunion);
        }
        catch (Exception e) {
            LOG.error("Error reunion not found  : " + e);
        }
        return reunionMapper.reunionToReunionResponseDTO(reunion);
    }

    @Override
    public List<ReunionResponseDTO> afficheReunion() {

        List<Reunion> reunions= repoReunion.findAll();
        List<ReunionResponseDTO> reunionResponseDTOS = reunions.stream()
                .map(reu->reunionMapper.reunionToReunionResponseDTO(reu))
                .collect(Collectors.toList());
        return reunionResponseDTOS;
    }


    @Override
    public ReunionResponseDTO chercherReunion(long idReunion) {
           Reunion reunion=  repoReunion.findById(idReunion).get();
        return reunionMapper.reunionToReunionResponseDTO(reunion);
    }
}
