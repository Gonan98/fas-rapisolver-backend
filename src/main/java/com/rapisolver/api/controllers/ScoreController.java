package com.rapisolver.api.controllers;

import com.rapisolver.api.dtos.CreateScoreDTO;
import com.rapisolver.api.dtos.ScoreDTO;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.response.RapisolverResponse;
import com.rapisolver.api.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/scores")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RapisolverResponse<ScoreDTO> create(@RequestBody @Valid CreateScoreDTO createScoreDTO) throws RapisolverException {
        return new RapisolverResponse<>(201, "CREATED", "Puntuacion creada correctamente", scoreService.create(createScoreDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public RapisolverResponse<List<ScoreDTO>> getAll() throws RapisolverException {
        return new RapisolverResponse<>(200, "OK", "Lista de puntuaciones", scoreService.findAll());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user-source/{userSourceId}")
    public RapisolverResponse<List<ScoreDTO>> getByUserSource(@PathVariable Long userSourceId) throws RapisolverException {
        return new RapisolverResponse<>(200, "OK", "Puntuaciones hechas por el usuario con Id:"+userSourceId, scoreService.findByUserSource(userSourceId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user-destination/{userDestinationId}")
    public RapisolverResponse<List<ScoreDTO>> getByUserDestination(@PathVariable Long userDestinationId) throws RapisolverException {
        return new RapisolverResponse<>(200, "OK", "Puntuaciones obtenidas del usuario con Id:"+userDestinationId, scoreService.findByUserDestination(userDestinationId));
    }
}
