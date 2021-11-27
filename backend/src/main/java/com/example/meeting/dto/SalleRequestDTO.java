package com.example.meeting.dto;

import com.example.meeting.entities.Reunion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class SalleRequestDTO {
    private long id ;
    private String name;
    private int nbrPlace;
    private List<String> equipements = new ArrayList<>() ;
    private List<Reunion> reunion;

    public SalleRequestDTO(String name, int nbrPlace, List<String> equipements) {
        this.name = name;
        this.nbrPlace = nbrPlace;
        this.equipements = equipements;
    }
}
