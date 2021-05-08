package com.rapisolver.api.services;

import com.rapisolver.api.dtos.CreateScoreDTO;
import com.rapisolver.api.dtos.ScoreDTO;
import com.rapisolver.api.exceptions.RapisolverException;

import java.util.List;

public interface ScoreService {
    ScoreDTO create(CreateScoreDTO createScoreDTO) throws RapisolverException;
    List<ScoreDTO> getAll() throws RapisolverException;
}
