package com.rapisolver.api.services.implementations;

import com.rapisolver.api.dtos.CreateUserAttentionDTO;
import com.rapisolver.api.dtos.UserAttentionDTO;
import com.rapisolver.api.entities.Attention;
import com.rapisolver.api.entities.User;
import com.rapisolver.api.entities.UserAttention;
import com.rapisolver.api.exceptions.BadRequestException;
import com.rapisolver.api.exceptions.InternalServerErrorException;
import com.rapisolver.api.exceptions.NotFoundException;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.repositories.AttentionRepository;
import com.rapisolver.api.repositories.UserAttentionRepository;
import com.rapisolver.api.repositories.UserRepository;
import com.rapisolver.api.services.UserAttentionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAttentionServiceImpl implements UserAttentionService {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    @Autowired
    private UserAttentionRepository userAttentionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AttentionRepository attentionRepository;

    @Override
    public UserAttentionDTO create(CreateUserAttentionDTO createUserAttentionDTO) throws RapisolverException {
        User userDB = userRepository.findById(createUserAttentionDTO.getUserId()).orElseThrow(() -> new NotFoundException("USER_NOT_FOUND"));

        if (!userDB.getRole().isCanPublish())
            throw new BadRequestException("USER_IS_NOT_SUPPLIER");

        if (userAttentionRepository.findByAttentionIdAndUserId(createUserAttentionDTO.getAttentionId(), createUserAttentionDTO.getUserId()).isPresent())
            throw new BadRequestException("USER_ATTENTION_ALREADY_EXIST");

        Attention attentionDB = attentionRepository.findById(createUserAttentionDTO.getAttentionId()).orElseThrow(() -> new NotFoundException("ATTENTION_NOT_FOUND"));



        try {
            UserAttention userAttention = new UserAttention();
            userAttention.setPrice(createUserAttentionDTO.getPrice());
            userAttention.setDetail(createUserAttentionDTO.getDetail());
            userAttention.setUser(userDB);
            userAttention.setAttention(attentionDB);
            userAttention = userAttentionRepository.save(userAttention);
            return MODEL_MAPPER.map(userAttention, UserAttentionDTO.class);
        } catch (Exception e) {
            throw new InternalServerErrorException("CREATE_USER_ATTENTION_ERROR");
        }
    }

    @Override
    public List<UserAttentionDTO> getByUserId(Long userId) throws RapisolverException {
        try {
            List<UserAttention> userAttentions = userAttentionRepository.findByUserId(userId);
            return userAttentions.stream().map(ua -> MODEL_MAPPER.map(ua, UserAttentionDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new InternalServerErrorException("GET_USER_ATTENTION_BY_USERID_ERROR");
        }
    }
}
