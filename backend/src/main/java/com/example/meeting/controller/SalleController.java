package com.example.meeting.controller;

import com.example.meeting.entities.Reunion;
import com.example.meeting.entities.Salle;
import com.example.meeting.service.ISalleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SalleController {
    @Autowired
    ISalleServices iSalleServices;


    //localhost:8080/spring-service/ajoutSalle
    @PostMapping("/ajoutSalle")
    @ResponseBody
    public ResponseEntity<Salle> ajouterSalle(@RequestBody Salle salle)
    {
        try {
            iSalleServices.ajoutSalle(salle);
            return new ResponseEntity<>(salle, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //localhost:8080/spring-service/getSalleById/{id}
    @GetMapping(value = "/getSalleById/{id}")
    @ResponseBody
    public ResponseEntity<Salle> getSalleById(@PathVariable("id")long id) {
        Salle salle =  iSalleServices.chercherSalle(id);
        return new ResponseEntity<>(salle, HttpStatus.OK);

    }
    //localhost:8080/spring-service/getSalle
    @GetMapping(value = "/getSalle")
    @ResponseBody
    public List<Salle> getSalle() {
        return iSalleServices.afficheSalle();

    }

    // http://localhost:8080/spring-service/remove-salle/{salleId}
    @DeleteMapping("/remove-salle/{salleId}")
    @ResponseBody
    public void removeSalle(@PathVariable("salleId") long salleId) {
        iSalleServices.supprimeSalle(salleId);
    }

}
