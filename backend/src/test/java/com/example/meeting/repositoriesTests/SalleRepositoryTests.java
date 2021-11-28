package com.example.meeting.repositoriesTests;


import com.example.meeting.entities.Salle;
import com.example.meeting.repositories.RepoSalle;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SalleRepositoryTests {
    @Autowired
    private RepoSalle repoSalle;

    @Before
    public void setUp(){

    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveSalleTest(){
        List<String> listEquipement =new ArrayList<>();
        listEquipement.add("Webcam");
        listEquipement.add("Ecran");

        Salle salle = Salle.builder()
                .name("E1001")
                .equipements(listEquipement)
                .nbrPlace(23)
                .build();

        repoSalle.save(salle);
        Assertions.assertThat(salle.getId()).isGreaterThan(0);

    }

    @Test
    @Order(2)
    public void getSalle(){
        Salle salle = repoSalle.findById(1L).orElse(null);
        Assertions.assertThat(salle.getId()).isEqualTo(1L);
    }


    @Test
    @Order(3)
    public void getListOfSalleTest(){
        List<Salle> salles = repoSalle.findAll();
        Assertions.assertThat(salles.size()).isGreaterThan(0);

    }


    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateSalleTest(){

        Salle salle = repoSalle.findById(1L).get();
        salle.setName("E1002");
        Salle salleUpdated =  repoSalle.save(salle);
        Assertions.assertThat(salleUpdated.getName()).isEqualTo("E1002");

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteSalleTest(){
        Salle salle = repoSalle.findById(1l).get();
        repoSalle.delete(salle);
        // repoReunion.deleteById(1L);
        Salle salle1 = null;
        Optional<Salle> optionalSalle = repoSalle.findSalleByName("E1002");
        if(optionalSalle.isPresent()){
            salle1 = optionalSalle.get();
        }
        Assertions.assertThat(salle1).isNull();
    }


}
