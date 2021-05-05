package com.rapisolver.api.services;

import com.rapisolver.api.dtos.CreateUserDTO;
import com.rapisolver.api.dtos.UserDTO;
import com.rapisolver.api.exceptions.RapisolverException;

import java.util.List;

public interface UserService {

    UserDTO create(CreateUserDTO createUserDTO) throws RapisolverException;
    List<UserDTO> findAll() throws RapisolverException;
    UserDTO findById(Long id) throws RapisolverException;
    String buySubscription(Long id) throws RapisolverException;

}
