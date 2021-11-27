package com.example.meeting.mappers;

import com.example.meeting.dto.ReunionRequestDTO;
import com.example.meeting.dto.ReunionResponseDTO;
import com.example.meeting.entities.Reunion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReunionMapper {
    ReunionResponseDTO reunionToReunionResponseDTO(Reunion reunion);
    Reunion reunionRequestDTOtoReunion(ReunionRequestDTO reunionRequestDTO);
}
