package com.example.meeting.service;

import com.example.meeting.dto.SalleRequestDTO;
import com.example.meeting.dto.SalleResponseDTO;
import com.example.meeting.entities.Reunion;
import com.example.meeting.entities.Salle;

import java.util.List;

public interface ISalleServices {
    public SalleResponseDTO ajoutSalle(SalleRequestDTO salleRequestDTO);
    public SalleResponseDTO supprimeSalle(long idSalle);
    public List<SalleResponseDTO> afficheSalle();
    public SalleResponseDTO chercherSalle(long idSalle);
}
