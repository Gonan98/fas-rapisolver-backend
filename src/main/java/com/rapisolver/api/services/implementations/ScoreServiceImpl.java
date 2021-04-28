package com.rapisolver.api.services.implementations;

import com.rapisolver.api.dtos.CreateScoreDTO;
import com.rapisolver.api.dtos.ScoreDTO;
import com.rapisolver.api.entities.Score;
import com.rapisolver.api.entities.User;
import com.rapisolver.api.exceptions.InternalServerErrorException;
import com.rapisolver.api.exceptions.NotFoundException;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.repositories.ScoreRepository;
import com.rapisolver.api.repositories.UserRepository;
import com.rapisolver.api.services.ScoreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreServiceImpl implements ScoreService {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    public ScoreDTO create(CreateScoreDTO createScoreDTO) throws RapisolverException {
        User source = userRepository.findById(createScoreDTO.getUserId()).orElseThrow(() -> new NotFoundException("Usuario fuente no encontrado"));
        User destination = userRepository.findById(createScoreDTO.getUserReceiverId()).orElseThrow(() -> new NotFoundException("Usuario destino no encontrado"));

        if (!destination.getRole().getName().equals("Proveedor"))
            throw new RapisolverException(400, "BAD_REQUEST", "El usuario destino no es un proveedor");

        Score score = new Score();
        score.setMark(createScoreDTO.getMark());
        score.setNote(createScoreDTO.getNote());
        score.setUser(source);
        score.setUserReceiver(destination);

        try {
            score = scoreRepository.save(score);
            return MODEL_MAPPER.map(score, ScoreDTO.class);
        } catch (Exception e) {
            throw new InternalServerErrorException("Error al insertar una calificacion");
        }
    }

    @Override
    public List<ScoreDTO> findAll() throws RapisolverException {
        try {
            List<Score> scores = scoreRepository.findAll();
            return scores.stream().map(s -> MODEL_MAPPER.map(s, ScoreDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new InternalServerErrorException("Error el obtener todas las calificaciones");
        }
    }

    @Override
    public List<ScoreDTO> findByUserSource(Long userSourceId) throws RapisolverException {
        try {
            List<Score> scores = scoreRepository.findByUserSource(userSourceId);
            return scores.stream().map(s -> MODEL_MAPPER.map(s, ScoreDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new InternalServerErrorException("Error el obtener las calificaciones hechas");
        }
    }

    @Override
    public List<ScoreDTO> findByUserDestination(Long userDestinationId) throws RapisolverException {
        try {
            List<Score> scores = scoreRepository.findByUserDestination(userDestinationId);
            return scores.stream().map(s -> MODEL_MAPPER.map(s, ScoreDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new InternalServerErrorException("Error el obtener las calificaciones obtenidas");
        }
    }
}
