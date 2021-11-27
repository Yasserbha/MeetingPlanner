package com.example.meeting.mappers;

import com.example.meeting.dto.ReunionRequestDTO;
import com.example.meeting.dto.ReunionResponseDTO;
import com.example.meeting.entities.Reunion;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-27T23:22:33+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Ubuntu)"
)
@Component
public class ReunionMapperImpl implements ReunionMapper {

    @Override
    public ReunionResponseDTO reunionToReunionResponseDTO(Reunion reunion) {
        if ( reunion == null ) {
            return null;
        }

        ReunionResponseDTO reunionResponseDTO = new ReunionResponseDTO();

        reunionResponseDTO.setId( reunion.getId() );
        reunionResponseDTO.setSalle( reunion.getSalle() );
        reunionResponseDTO.setTypeReunion( reunion.getTypeReunion() );
        reunionResponseDTO.setDebutReservation( reunion.getDebutReservation() );
        reunionResponseDTO.setFinReservation( reunion.getFinReservation() );
        reunionResponseDTO.setNbrPresonne( reunion.getNbrPresonne() );

        return reunionResponseDTO;
    }

    @Override
    public Reunion reunionRequestDTOtoReunion(ReunionRequestDTO reunionRequestDTO) {
        if ( reunionRequestDTO == null ) {
            return null;
        }

        Reunion reunion = new Reunion();

        reunion.setDebutReservation( reunionRequestDTO.getDebutReservation() );
        reunion.setFinReservation( reunionRequestDTO.getFinReservation() );
        reunion.setSalle( reunionRequestDTO.getSalle() );
        reunion.setId( reunionRequestDTO.getId() );
        reunion.setNbrPresonne( reunionRequestDTO.getNbrPresonne() );
        reunion.setTypeReunion( reunionRequestDTO.getTypeReunion() );

        return reunion;
    }
}
