package com.example.meeting.controllers;

import com.example.meeting.dto.SalleRequestDTO;
import com.example.meeting.dto.SalleResponseDTO;
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


    //localhost:8080/ajoutSalle
    @PostMapping("/ajoutSalle")
    @ResponseBody
    public ResponseEntity<SalleResponseDTO> ajouterSalle(@RequestBody SalleRequestDTO salleRequestDTO)
    {
        try {
            SalleResponseDTO salleResponseDTO= iSalleServices.ajoutSalle(salleRequestDTO);
            return new ResponseEntity<>(salleResponseDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //localhost:8080/getSalleById/{id}
    @GetMapping(value = "/getSalleById/{id}")
    @ResponseBody
    public ResponseEntity<SalleResponseDTO> getSalleById(@PathVariable("id")long id) {

        SalleResponseDTO salleResponseDTO =  iSalleServices.chercherSalle(id);
        return new ResponseEntity<>(salleResponseDTO, HttpStatus.OK);

    }
    //localhost:8080/getSalle
    @GetMapping(value = "/getSalle")
    @ResponseBody
    public List<SalleResponseDTO> getSalle() {
        return iSalleServices.afficheSalle();

    }

    // http://localhost:8080/remove-salle/{salleId}
    @DeleteMapping("/remove-salle/{salleId}")
    @ResponseBody
    public void removeSalle(@PathVariable("salleId") long salleId) {
        iSalleServices.supprimeSalle(salleId);
    }

}
