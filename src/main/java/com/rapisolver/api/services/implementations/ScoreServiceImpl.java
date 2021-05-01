package com.rapisolver.api.services.implementations;

import com.rapisolver.api.dtos.CreateScoreDTO;
import com.rapisolver.api.dtos.ScoreDTO;
import com.rapisolver.api.entities.Score;
import com.rapisolver.api.entities.User;
import com.rapisolver.api.exceptions.BadRequestException;
import com.rapisolver.api.exceptions.InternalServerErrorException;
import com.rapisolver.api.exceptions.NotFoundException;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.repositories.ScoreRepository;
import com.rapisolver.api.repositories.UserRepository;
import com.rapisolver.api.services.ScoreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreServiceImpl implements ScoreService {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Transactional
    @Override
    public ScoreDTO create(CreateScoreDTO createScoreDTO) throws RapisolverException {
        return null;
    }

    @Override
    public List<ScoreDTO> findAll() throws RapisolverException {
        return null;
    }
}
