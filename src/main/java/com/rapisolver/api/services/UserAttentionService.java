package com.rapisolver.api.services;

import com.rapisolver.api.dtos.CreateUserAttentionDTO;
import com.rapisolver.api.dtos.UserAttentionDTO;
import com.rapisolver.api.exceptions.RapisolverException;

public interface UserAttentionService {

    UserAttentionDTO create(CreateUserAttentionDTO createUserAttentionDTO) throws RapisolverException;

}
