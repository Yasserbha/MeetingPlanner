package com.example.meeting.service;

import com.example.meeting.entities.Reunion;
import com.example.meeting.entities.Salle;
import com.example.meeting.repository.RepoSalle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SalleServiceImpl implements ISalleServices{
    private static final Logger LOG = LoggerFactory.getLogger(SalleServiceImpl.class);
    @Autowired
    RepoSalle repoSalle;

    @Override
    public String ajoutSalle(Salle salle) {
         repoSalle.save(salle);
        return salle.toString();
    }

    @Override
    public String supprimeSalle(long idSalle) {
        Salle salle = repoSalle.findById(idSalle).orElse(null);
        try{
            repoSalle.delete(salle);
           }
        catch (Exception e) {
            LOG.error("Error Salle not found  : " + e);
        }
        return salle.toString();
    }

    @Override
    public List<Salle> afficheSalle() {
        return repoSalle.findAll();
    }


    Salle salle ;
    @Override
    public Salle chercherSalle(long idSalle) {
        try{
             salle = repoSalle.findById(idSalle).orElse(null);
        }
        catch (Exception e) {
            LOG.error("Error Salle not found  : " + e);
        }
        return salle;
    }
}
