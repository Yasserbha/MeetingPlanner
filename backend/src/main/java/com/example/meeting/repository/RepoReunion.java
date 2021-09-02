package com.example.meeting.repository;

import com.example.meeting.entities.Reunion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoReunion extends JpaRepository<Reunion,Long> {
}
