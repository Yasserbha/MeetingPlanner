package com.example.meeting.repositoriesTests;

import com.example.meeting.entities.Reunion;
import com.example.meeting.entities.TypeReunion;
import com.example.meeting.repositories.RepoReunion;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.Before;



@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReunionRepositoryTests {

    @Autowired
    private RepoReunion repoReunion;

    @Before
    public void setUp(){

    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveReunionTest(){
        LocalDateTime dt1 = LocalDateTime.parse("2011-11-20T13:45:30");

        Reunion reunion = Reunion.builder()
                .typeReunion(TypeReunion.RS)
                .nbrPresonne(7)
                .debutReservation(dt1)
                .build();

        repoReunion.save(reunion);
        Assertions.assertThat(reunion.getId()).isGreaterThan(0);

    }

    @Test
    @Order(2)
    public void getReunion(){
        Reunion reunion = repoReunion.findById(1L).orElse(null);
        Assertions.assertThat(reunion.getId()).isEqualTo(1L);
    }


    @Test
    @Order(3)
    public void getListOfReunionTest(){

        List<Reunion> reunionList = repoReunion.findAll();
        Assertions.assertThat(reunionList.size()).isGreaterThan(0);

    }


    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateReunionTest(){

        Reunion reunion = repoReunion.findById(1L).get();
        reunion.setTypeReunion(TypeReunion.VC);
        Reunion reunionUpdated =  repoReunion.save(reunion);
        Assertions.assertThat(reunionUpdated.getTypeReunion()).isEqualTo(TypeReunion.VC);

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteReunionTest(){
        Reunion reunion = repoReunion.findById(1L).get();
        repoReunion.delete(reunion);
       // repoReunion.deleteById(1L);
        Reunion reunion1 = null;
        Optional<Reunion> optionalReunion = repoReunion.findById(1l);
        if(optionalReunion.isPresent()){
            reunion1 = optionalReunion.get();
        }
        Assertions.assertThat(reunion1).isNull();
    }



}
