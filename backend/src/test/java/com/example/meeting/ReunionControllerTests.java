package com.example.meeting;



import com.example.meeting.entities.Reunion;
import com.example.meeting.entities.TypeReunion;
import com.example.meeting.repository.RepoReunion;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;



import java.time.LocalDateTime;
import java.util.List;
import org.junit.Test;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ReunionControllerTests {
/*
    @Autowired
    private MockMvc mockMvc ;

    @Autowired
    private ObjectMapper objectMapper;
*/
    @Autowired
    private RepoReunion repoReunion;
/*
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
*/

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
    /*
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
