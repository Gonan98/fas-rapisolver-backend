package com.rapisolver.api.services;

import com.rapisolver.api.dtos.CreateUserAttentionDTO;
import com.rapisolver.api.dtos.UserAttentionDTO;
import com.rapisolver.api.exceptions.RapisolverException;

import java.util.List;

public interface UserAttentionService {

    UserAttentionDTO create(CreateUserAttentionDTO createUserAttentionDTO) throws RapisolverException;

    List<UserAttentionDTO> getByUserId(Long userId) throws RapisolverException;

}
