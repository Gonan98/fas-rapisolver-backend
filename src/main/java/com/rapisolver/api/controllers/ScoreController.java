package com.rapisolver.api.controllers;

import com.rapisolver.api.dtos.CreateScoreDTO;
import com.rapisolver.api.dtos.ScoreDTO;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.response.RapisolverResponse;
import com.rapisolver.api.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/scores")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @PostMapping
    public RapisolverResponse<ScoreDTO> create(@RequestBody @Valid CreateScoreDTO createScoreDTO) {
        try {
            ScoreDTO scoreDTO = scoreService.create(createScoreDTO);
            return new RapisolverResponse<>(201, "CREATED", "Puntuacion creada correctamente", scoreDTO);
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }
    }

    @GetMapping
    public RapisolverResponse<List<ScoreDTO>> getAll() {
        try {
            List<ScoreDTO> scores = scoreService.findAll();
            return new RapisolverResponse<>(200, "OK", "Lista de puntuaciones", scores);
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }
    }

    @GetMapping("/user-source/{userSourceId}")
    public RapisolverResponse<List<ScoreDTO>> getByUserSource(@PathVariable Long userSourceId) {
        try {
            List<ScoreDTO> scores = scoreService.findByUserSource(userSourceId);
            return new RapisolverResponse<>(200, "OK", "Puntuaciones hechas por el usuario con Id:"+userSourceId, scores);
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }
    }

    @GetMapping("/user-destination/{userDestinationId}")
    public RapisolverResponse<List<ScoreDTO>> getByUserDestination(@PathVariable Long userDestinationId) {
        try {
            List<ScoreDTO> scores = scoreService.findByUserDestination(userDestinationId);
            return new RapisolverResponse<>(200, "OK", "Puntuaciones obtenidas del usuario con Id:"+userDestinationId, scores);
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }
    }
}
