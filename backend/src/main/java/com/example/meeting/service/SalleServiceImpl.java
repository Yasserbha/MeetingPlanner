package com.example.meeting.service;

import com.example.meeting.dto.SalleRequestDTO;
import com.example.meeting.dto.SalleResponseDTO;
import com.example.meeting.entities.Salle;
import com.example.meeting.mappers.SalleMapper;
import com.example.meeting.repositories.RepoSalle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SalleServiceImpl implements ISalleServices{
    private static final Logger LOG = LoggerFactory.getLogger(SalleServiceImpl.class);
    @Autowired
    RepoSalle repoSalle;

    private SalleMapper salleMapper;

    public SalleServiceImpl(SalleMapper salleMapper) {
        this.salleMapper = salleMapper;
    }

    @Override
    public SalleResponseDTO ajoutSalle(SalleRequestDTO salleRequestDTO) {
        Salle salle = salleMapper.salleRequestDTOtoSalle(salleRequestDTO);
         repoSalle.save(salle);
        return salleMapper.salleToSalleResponseDTO(salle);
    }

    @Override
    public SalleResponseDTO supprimeSalle(long idSalle) {
        Salle salle = repoSalle.findById(idSalle).orElse(null);
        try{
            repoSalle.deleteById(idSalle);
           }
        catch (Exception e) {
            LOG.error("Error Salle not found  : " + e);
        }
        return salleMapper.salleToSalleResponseDTO(salle);
    }

    @Override
    public List<SalleResponseDTO> afficheSalle() {
        List<Salle> salles = repoSalle.findAll();
        List<SalleResponseDTO> salleResponseDTOS= salles.stream().
                map(sal->salleMapper.salleToSalleResponseDTO(sal))
                .collect(Collectors.toList());
        LOG.info("Reunions  : " + salleResponseDTOS);
        return salleResponseDTOS;
    }

    @Override
    public SalleResponseDTO chercherSalle(long idSalle) {
        Salle salle = repoSalle.findById(idSalle).get();
        return salleMapper.salleToSalleResponseDTO(salle);
    }
}
