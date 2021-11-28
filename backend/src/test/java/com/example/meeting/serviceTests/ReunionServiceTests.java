package com.example.meeting.serviceTests;



import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.meeting.dto.ReunionRequestDTO;
import com.example.meeting.entities.Reunion;
import com.example.meeting.entities.Salle;
import com.example.meeting.entities.TypeReunion;
import com.example.meeting.mappers.ReunionMapper;
import com.example.meeting.repositories.RepoReunion;
import com.example.meeting.repositories.RepoSalle;
import com.example.meeting.service.IReunionServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReunionServiceTests {

    @Autowired
    private IReunionServices iReunionServices;

    @Autowired
    private ReunionMapper reunionMapper;

    @Autowired
    private RepoSalle repoSalle;

    @MockBean
    private RepoReunion repoReunion;
    @Before
    public void setUp(){

    }

    @Test
    public void getRunionTest() {
        LocalDateTime dt1 = LocalDateTime.parse("2011-11-20T13:45:30");

        when(repoReunion.findAll()).thenReturn(Stream
                .of( Reunion.builder().typeReunion(TypeReunion.RS)
                .nbrPresonne(7)
                .debutReservation(dt1)
                .build(),
                        Reunion.builder().typeReunion(TypeReunion.VC)
                        .nbrPresonne(23)
                        .debutReservation(dt1)
                        .build()).collect(Collectors.toList()));
        assertEquals(2, iReunionServices.afficheReunion().size());
    }


    @Test
    public void saveReunionTest() {
        LocalDateTime dateDebut = LocalDateTime.parse("2011-11-20T13:45:30");
        LocalDateTime dateFin = LocalDateTime.parse("2011-11-20T14:45:30");
        List<String> listEquipement =new ArrayList<>();
        listEquipement.add("Webcam");
        Salle salle = new Salle("E1001", 23, listEquipement);
        repoSalle.save(salle);
        ReunionRequestDTO reunionRequestDTOExpected= new ReunionRequestDTO(1l,salle,TypeReunion.VC,dateDebut,dateFin,7);

        when(repoReunion.save(reunionMapper.reunionRequestDTOtoReunion(reunionRequestDTOExpected))).thenReturn(reunionMapper.reunionRequestDTOtoReunion(reunionRequestDTOExpected));
        assertEquals(reunionMapper.reunionRequestDTOtoReunion(reunionRequestDTOExpected).toString(), iReunionServices.ajoutReunion(reunionRequestDTOExpected));
    }

    @Test
    public void deleteReunionTest() {
        LocalDateTime dateDebut = LocalDateTime.parse("2011-11-20T13:45:30");
        LocalDateTime dateFin = LocalDateTime.parse("2011-11-20T14:45:30");

        List<String> listEquipement =new ArrayList<>();
        listEquipement.add("Webcam");
        Salle salle = new Salle("E1001", 23, listEquipement);

        repoSalle.save(salle);
        ReunionRequestDTO reunionRequestDTO= new ReunionRequestDTO(1L,salle,TypeReunion.VC,dateDebut,dateFin,7);

        iReunionServices.ajoutReunion(reunionRequestDTO);
        iReunionServices.supprimeReunion(reunionRequestDTO.getId());


        verify(repoReunion, times(1)).deleteById(reunionMapper.reunionRequestDTOtoReunion(reunionRequestDTO).getId());


    }



}
