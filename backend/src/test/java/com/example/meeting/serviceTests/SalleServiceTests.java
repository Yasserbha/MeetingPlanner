package com.example.meeting.serviceTests;

import com.example.meeting.dto.SalleRequestDTO;
import com.example.meeting.mappers.SalleMapper;
import com.example.meeting.service.ISalleServices;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.meeting.entities.Salle;
import com.example.meeting.repositories.RepoSalle;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SalleServiceTests {
    @Autowired
    private ISalleServices iSalleServices;

    @Autowired
    private SalleMapper salleMapper;

    @MockBean
    private RepoSalle repoSalle;

    @Before
    public void setUp(){

    }
    @Test
    public void getSallesTest() {

        List<String> listEquipement =new ArrayList<>();
        listEquipement.add("Webcam");
        when(repoSalle.findAll()).thenReturn(Stream
                .of(new Salle("E1001", 23, listEquipement), new Salle("E1002", 23, listEquipement)).collect(Collectors.toList()));
        assertEquals(2, iSalleServices.afficheSalle().size());
    }

    @Test
    public void saveUserTest() {
        List<String> listEquipement =new ArrayList<>();
        listEquipement.add("Webcam");
        SalleRequestDTO salleRequestDTO = new SalleRequestDTO("E1001", 23, listEquipement);
        Salle salle =  salleMapper.salleRequestDTOtoSalle(salleRequestDTO);
        when(repoSalle.save(salle)).thenReturn(salle);
        assertEquals(salleMapper.salleToSalleResponseDTO(salle), iSalleServices.ajoutSalle(salleRequestDTO));
    }

    @Test
    public void deleteUserTest() {
        List<String> listEquipement =new ArrayList<>();
        listEquipement.add("Webcam");
        SalleRequestDTO salleRequestDTO = new SalleRequestDTO("E1001", 23, listEquipement);
        iSalleServices.supprimeSalle(salleRequestDTO.getId());
        verify(repoSalle, times(1)).deleteById(salleRequestDTO.getId());
    }





}
