package com.example.meeting.service;

import com.example.meeting.entities.Reunion;
import com.example.meeting.entities.Salle;

import java.util.List;

public interface ISalleServices {
    public String ajoutSalle(Salle salle);
    public String supprimeSalle(long idSalle);
    public List<Salle> afficheSalle();
    public Salle chercherSalle(long idSalle);
}
