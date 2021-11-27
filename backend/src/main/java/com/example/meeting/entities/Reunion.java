package com.example.meeting.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Reunion implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "idSalle", referencedColumnName = "id")
    private Salle salle;



    @Enumerated(EnumType.STRING)
    @NotNull
    private TypeReunion typeReunion;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(shape=JsonFormat.Shape.STRING, timezone = "Europe/France")
    private LocalDateTime debutReservation;

    @JsonFormat(shape=JsonFormat.Shape.STRING , timezone = "Europe/France")
    private LocalDateTime finReservation;
    private int nbrPresonne;
    
    public void setDebutReservation(LocalDateTime debutReservation) {
        this.debutReservation = debutReservation;
    }
    public void setFinReservation(LocalDateTime finReservation) {
        this.finReservation = finReservation;
    }
    public void setSalle(Salle salle) {
        this.salle = salle;
    }
    public Salle getSalle() {
        return salle;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
    public TypeReunion getTypeReunion() {
        return typeReunion;
    }
    public void setNbrPresonne(int nbrPresonne) {
        this.nbrPresonne = nbrPresonne;
    }
    public void setTypeReunion(TypeReunion typeReunion) {
        this.typeReunion = typeReunion;
    }
    public int getNbrPresonne() {
        return nbrPresonne;
    }
    public LocalDateTime getDebutReservation() {
        return debutReservation;
    }
    public LocalDateTime getFinReservation() {
        return finReservation;
    }
   
}
