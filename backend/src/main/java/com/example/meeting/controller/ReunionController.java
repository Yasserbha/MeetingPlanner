package com.example.meeting.controller;

import com.example.meeting.entities.Reunion;
import com.example.meeting.service.IReunionServices;
import com.example.meeting.service.ReunionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReunionController {
    private static final Logger LOG = LoggerFactory.getLogger(ReunionController.class);

    @Autowired
    IReunionServices iReunionServices;

    //localhost:8080/spring-service/ajoutReunion
    @PostMapping("/ajoutReunion")
    @ResponseBody
    public ResponseEntity<String> ajouterReunion(@RequestBody  Reunion reunion)
    {

        try {
            LOG.info("*************"+reunion);
            String msg = iReunionServices.ajoutReunion(reunion);
            if(msg.equals("Ajout valid !")) {
                return new ResponseEntity<>(msg, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //localhost:8080/spring-service/getReunionById/{id}
    @GetMapping(value = "/getReunionById/{id}")
    @ResponseBody
    public ResponseEntity<Reunion> getreunionById(@PathVariable("id")long reunionId) {
       Reunion reunion =  iReunionServices.chercherReunion(reunionId);
            return new ResponseEntity<>(reunion, HttpStatus.OK);

    }
    //localhost:8080/spring-service/getReunions
    @GetMapping(value = "/getReunions")
    @ResponseBody
    public List<Reunion> getReunions() {
        return iReunionServices.afficheReunion();

    }

    // http://localhost:8080/spring-service/{reunionId}
    @DeleteMapping("/remove-reunion/{reunionId}")
    @ResponseBody
    public void removeReunion(@PathVariable("reunionId") long Id) {
        iReunionServices.supprimeReunion(Id);
    }
}
