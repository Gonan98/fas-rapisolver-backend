package com.rapisolver.api.services;

import com.rapisolver.api.dtos.CreateUserDTO;
import com.rapisolver.api.dtos.UserDTO;
import com.rapisolver.api.exceptions.RapisolverException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDTO create(CreateUserDTO createUserDTO) throws RapisolverException;
    List<UserDTO> findAll() throws RapisolverException;
    UserDTO findById(Long id) throws RapisolverException;

}
