package com.example.meeting.service;

import com.example.meeting.dto.ReunionRequestDTO;
import com.example.meeting.dto.ReunionResponseDTO;

import java.util.List;

public interface IReunionServices {
     String ajoutReunion(ReunionRequestDTO reunionRequestDTO);
     ReunionResponseDTO supprimeReunion(long idReunion);
     List<ReunionResponseDTO> afficheReunion();
     ReunionResponseDTO chercherReunion(long idReunion);

}
