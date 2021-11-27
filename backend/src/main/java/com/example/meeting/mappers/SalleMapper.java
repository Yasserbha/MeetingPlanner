package com.example.meeting.mappers;


import com.example.meeting.dto.SalleRequestDTO;
import com.example.meeting.dto.SalleResponseDTO;
import com.example.meeting.entities.Salle;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface SalleMapper {

    SalleResponseDTO salleToSalleResponseDTO(Salle salle);
    Salle salleRequestDTOtoSalle(SalleRequestDTO salleRequestDTO);

}
