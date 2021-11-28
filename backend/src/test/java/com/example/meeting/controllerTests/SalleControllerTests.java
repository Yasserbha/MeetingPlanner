package com.example.meeting.controllerTests;



import com.example.meeting.controllers.SalleController;


import com.example.meeting.dto.SalleRequestDTO;
import com.example.meeting.dto.SalleResponseDTO;
import com.example.meeting.entities.Salle;
import com.example.meeting.mappers.SalleMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.example.meeting.service.ISalleServices;
import static org.hamcrest.Matchers.hasSize;
import java.util.Arrays;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SalleController.class)
public class SalleControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ISalleServices iSalleServices;
    @Before
    public void setUp(){
    }

    @Test
    public void createSalle_whenPostMethod() throws Exception {
        SalleRequestDTO salleRequestDTO = new SalleRequestDTO();
        salleRequestDTO.setName("E1001");
        SalleResponseDTO salleResponseDTO=new SalleResponseDTO();
        salleResponseDTO.setName("E1001");
        given(iSalleServices.ajoutSalle(salleRequestDTO)).willReturn(salleResponseDTO);
        mockMvc.perform(post("/ajoutSalle")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(salleRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(salleRequestDTO.getName())));
    }

    @Test
    public void listAllUsers_whenGetMethod() throws Exception {
        SalleResponseDTO salleResponseDTO = new SalleResponseDTO();
        salleResponseDTO.setName("E1001");
        List<SalleResponseDTO> allSalles = Arrays.asList(salleResponseDTO);
        given(iSalleServices
                .afficheSalle())
                .willReturn(allSalles);

        mockMvc.perform(get("/getSalle")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(salleResponseDTO.getName())));
    }






























/*
    @Autowired
    private MockMvc mockMvc ;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RepoReunion repoReunion;

    @Test
    @Order(1)
    public void ajoutReunion() throws  Exception {
        Reunion newReunion =new Reunion();
        newReunion.setNbrPresonne(32);
        newReunion.setTypeReunion(TypeReunion.RC);
        newReunion.setDebutReservation(LocalDateTime.now());
        Reunion savedReunion =new Reunion();
        savedReunion.setNbrPresonne(32);
        savedReunion.setTypeReunion(TypeReunion.RC);
        Mockito.when(repoReunion.save(newReunion)).thenReturn(savedReunion);
        mockMvc.perform(MockMvcRequestBuilders.post("/ajoutReunion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newReunion)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }


    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveEmployeeTest(){

        Reunion reunion = Reunion.builder()
                .typeReunion(TypeReunion.RS)
                .nbrPresonne(30)
                .debutReservation(LocalDateTime.now())
                .build();

        repoReunion.save(reunion);

        Assertions.assertThat(reunion.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getReunionTest(){

        Reunion reunion = repoReunion.findById(36L).get();

        Assertions.assertThat(reunion.getId()).isEqualTo(36L);

    }

    @Test
    @Order(3)
    public void getListOfReunionTest(){

        List<Reunion> reunions = repoReunion.findAll();

        Assertions.assertThat(reunions.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateReunionTest(){

        Reunion reunion = repoReunion.findById(36L).get();

        reunion.setTypeReunion(TypeReunion.RS);

        Reunion employeeUpdated =  repoReunion.save(reunion);

        Assertions.assertThat(employeeUpdated.getTypeReunion()).isEqualTo(TypeReunion.RS);

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteReunionTest(){

        Reunion reunion = repoReunion.findById(36L).get();

        repoReunion.delete(reunion);

        //employeeRepository.deleteById(1L);

        Reunion reunion1 = null;

        Optional<Reunion> optionalReunion = repoReunion.findById(1L);

        if(optionalReunion.isPresent()){
            reunion1 = optionalReunion.get();
        }

        Assertions.assertThat(reunion1).isNull();
    }
        */


}
