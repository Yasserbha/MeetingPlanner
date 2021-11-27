package com.example.meeting.dto;

import com.example.meeting.entities.Salle;
import com.example.meeting.entities.TypeReunion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReunionResponseDTO implements Serializable {
    private long id ;
    @JsonIgnore
    private Salle salle;
    private TypeReunion typeReunion;
    private LocalDateTime debutReservation;
    private LocalDateTime finReservation;
    private int nbrPresonne;
}
