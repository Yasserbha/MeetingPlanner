package com.example.meeting.dto;

import com.example.meeting.entities.Reunion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SalleResponseDTO {
    private long id ;
    private String name;
    private int nbrPlace;
    private List<String> equipements = new ArrayList<>() ;
    private List<Reunion> reunion;
}
