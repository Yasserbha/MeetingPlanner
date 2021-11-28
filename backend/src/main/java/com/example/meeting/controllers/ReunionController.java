package com.example.meeting.controllers;

import com.example.meeting.dto.ReunionRequestDTO;
import com.example.meeting.dto.ReunionResponseDTO;
import com.example.meeting.service.IReunionServices;
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

    //localhost:8080/ajoutReunion
    @PostMapping("/ajoutReunion")
    @ResponseBody
    public ResponseEntity<String> ajouterReunion(@RequestBody ReunionRequestDTO reunionRequestDTO)
    {

        try {
            LOG.info("*************"+reunionRequestDTO);
            String msg = iReunionServices.ajoutReunion(reunionRequestDTO);
            LOG.info("**** msg *********"+msg);
            if(msg.contains("Reunion")) {
                return new ResponseEntity<>(msg, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //localhost:8080/getReunionById/{id}
    @GetMapping(value = "/getReunionById/{id}")
    @ResponseBody
    public ResponseEntity<ReunionResponseDTO> getreunionById(@PathVariable("id")long reunionId) {
        ReunionResponseDTO reunionResponseDTO =  iReunionServices.chercherReunion(reunionId);
            return new ResponseEntity<>(reunionResponseDTO, HttpStatus.OK);

    }
    //localhost:8080/getReunions
    @GetMapping(value = "/getReunions")
    @ResponseBody
    public List<ReunionResponseDTO> getReunions() {
        return iReunionServices.afficheReunion();

    }

    // http://localhost:8080/{reunionId}
    @DeleteMapping("/remove-reunion/{reunionId}")
    @ResponseBody
    public void removeReunion(@PathVariable("reunionId") long Id) {
        iReunionServices.supprimeReunion(Id);
    }
}
