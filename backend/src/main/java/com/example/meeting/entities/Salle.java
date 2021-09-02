package com.example.meeting.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @ToString
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
    @JsonManagedReference
    private List<Reunion> reunion;

    public void setId(long id) {
        this.id = id;
    }
    public void setEquipements(List<String> equipements) {
        this.equipements = equipements;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setNbrPlace(int nbrPlace) {
        this.nbrPlace = nbrPlace;
    }
    public void setReunion(List<Reunion> reunion) {
        this.reunion = reunion;
    }
    public List<String> getEquipements() {
        return equipements;
    }
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getNbrPlace() {
        return nbrPlace;
    }
    public List<Reunion> getReunion() {
        return reunion;
    }

}
