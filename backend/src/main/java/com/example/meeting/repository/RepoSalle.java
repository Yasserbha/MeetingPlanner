package com.example.meeting.repository;

import com.example.meeting.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepoSalle extends JpaRepository<Salle,Long> {
}
