package com.rapisolver.api.services.implementations;

import com.rapisolver.api.dtos.CreateScoreDTO;
import com.rapisolver.api.dtos.ScoreDTO;
import com.rapisolver.api.entities.Score;
import com.rapisolver.api.entities.User;
import com.rapisolver.api.entities.UserAttention;
import com.rapisolver.api.exceptions.BadRequestException;
import com.rapisolver.api.exceptions.InternalServerErrorException;
import com.rapisolver.api.exceptions.NotFoundException;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.repositories.ScoreRepository;
import com.rapisolver.api.repositories.UserAttentionRepository;
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

    @Autowired
    private UserAttentionRepository userAttentionRepository;

    @Transactional
    @Override
    public ScoreDTO create(CreateScoreDTO createScoreDTO) throws RapisolverException {
        User userDB = userRepository.findById(createScoreDTO.getUserId()).orElseThrow(() -> new NotFoundException("USER_NOT_FOUND"));
        UserAttention userAttentionDB = userAttentionRepository.findById(createScoreDTO.getUserAttentionId()).orElseThrow(() -> new NotFoundException("USER_ATTENTION_NOT_FOUND"));

        try {
            Score score = new Score();
            score.setMark(createScoreDTO.getMark());
            score.setNote(createScoreDTO.getNote());
            score.setUser(userDB);
            score.setUserAttention(userAttentionDB);
            score = scoreRepository.save(score);
            return MODEL_MAPPER.map(score, ScoreDTO.class);
        } catch (Exception e) {
            throw new InternalServerErrorException("CREATE_SCORE_ERROR");
        }
    }

    @Override
    public List<ScoreDTO> getAll() throws RapisolverException {
        try {
            List<Score> scores = scoreRepository.findAll();
            return scores.stream().map(s -> MODEL_MAPPER.map(s, ScoreDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new InternalServerErrorException("GET_ALL_SCORES_ERROR");
        }
    }
}
