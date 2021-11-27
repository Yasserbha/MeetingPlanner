package com.example.meeting.repositories;

import com.example.meeting.entities.Reunion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoReunion extends JpaRepository<Reunion,Long> {
}
