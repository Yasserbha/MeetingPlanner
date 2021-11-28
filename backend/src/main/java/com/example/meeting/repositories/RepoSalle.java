package com.example.meeting.repositories;

import com.example.meeting.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RepoSalle extends JpaRepository<Salle,Long> {
     Optional<Salle> findSalleByName(String name);
}
