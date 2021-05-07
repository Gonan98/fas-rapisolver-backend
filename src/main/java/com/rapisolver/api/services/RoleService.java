package com.rapisolver.api.services;

import com.rapisolver.api.dtos.CreateRoleDTO;
import com.rapisolver.api.dtos.RoleDTO;
import com.rapisolver.api.exceptions.RapisolverException;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    RoleDTO create(CreateRoleDTO createRoleDTO) throws RapisolverException;
    List<RoleDTO> findAll() throws RapisolverException;
    RoleDTO update(Long id, CreateRoleDTO createRoleDTO) throws RapisolverException;
    void deleteById(Long id) throws RapisolverException;
}
