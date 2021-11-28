package com.example.meeting.entities;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity @Data @NoArgsConstructor @AllArgsConstructor  @ToString
@Builder
public class Salle implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id ;
    private String name;
    private int nbrPlace;
    @ElementCollection
    private List<String> equipements = new ArrayList<>() ;

    @OneToMany(mappedBy = "salle",
            cascade = CascadeType.ALL , fetch = FetchType.LAZY)

    private List<Reunion> reunion;

    public Salle(String name, int nbrPlace, List<String> equipements) {
        this.name = name;
        this.nbrPlace = nbrPlace;
        this.equipements = equipements;
    }


}
