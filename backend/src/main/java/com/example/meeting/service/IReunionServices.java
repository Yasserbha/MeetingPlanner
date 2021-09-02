package com.example.meeting.service;

import com.example.meeting.entities.Reunion;

import java.util.List;

public interface IReunionServices {
    public String ajoutReunion(Reunion reunion);
    public String supprimeReunion(long idReunion);
    public List<Reunion> afficheReunion();
    public Reunion chercherReunion(long idReunion);

}
