package com.example.meeting.mappers;

import com.example.meeting.dto.SalleRequestDTO;
import com.example.meeting.dto.SalleResponseDTO;
import com.example.meeting.entities.Reunion;
import com.example.meeting.entities.Salle;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-27T22:45:37+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Ubuntu)"
)
@Component
public class SalleMapperImpl implements SalleMapper {

    @Override
    public SalleResponseDTO salleToSalleResponseDTO(Salle salle) {
        if ( salle == null ) {
            return null;
        }

        SalleResponseDTO salleResponseDTO = new SalleResponseDTO();

        salleResponseDTO.setId( salle.getId() );
        salleResponseDTO.setName( salle.getName() );
        salleResponseDTO.setNbrPlace( salle.getNbrPlace() );
        List<String> list = salle.getEquipements();
        if ( list != null ) {
            salleResponseDTO.setEquipements( new ArrayList<String>( list ) );
        }
        List<Reunion> list1 = salle.getReunion();
        if ( list1 != null ) {
            salleResponseDTO.setReunion( new ArrayList<Reunion>( list1 ) );
        }

        return salleResponseDTO;
    }

    @Override
    public Salle salleRequestDTOtoSalle(SalleRequestDTO salleRequestDTO) {
        if ( salleRequestDTO == null ) {
            return null;
        }

        Salle salle = new Salle();

        salle.setId( salleRequestDTO.getId() );
        List<String> list = salleRequestDTO.getEquipements();
        if ( list != null ) {
            salle.setEquipements( new ArrayList<String>( list ) );
        }
        salle.setName( salleRequestDTO.getName() );
        salle.setNbrPlace( salleRequestDTO.getNbrPlace() );
        List<Reunion> list1 = salleRequestDTO.getReunion();
        if ( list1 != null ) {
            salle.setReunion( new ArrayList<Reunion>( list1 ) );
        }

        return salle;
    }
}
