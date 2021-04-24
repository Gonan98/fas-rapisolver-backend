package com.rapisolver.api.controllers;

import com.rapisolver.api.dtos.CreateRoleDTO;
import com.rapisolver.api.dtos.RoleDTO;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.response.RapisolverResponse;
import com.rapisolver.api.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    private RapisolverResponse<RoleDTO> create(@RequestBody @Valid CreateRoleDTO createRoleDTO) {
        RoleDTO roleDTO;
        try {
            roleDTO = roleService.create(createRoleDTO);
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }

        return new RapisolverResponse<>(201, "CREATED","Rol creado correctamente", roleDTO);
    }

    @GetMapping
    private RapisolverResponse<List<RoleDTO>> getAll() {
        List<RoleDTO> roleDTOS;
        try {
            roleDTOS = roleService.findAll();
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }

        return new RapisolverResponse<>(200, "OK","Lista de roles", roleDTOS);
    }

    @PutMapping("/{id}")
    private RapisolverResponse<RoleDTO> updateById(@PathVariable Long id, @RequestBody @Valid CreateRoleDTO createRoleDTO) {
        RoleDTO roleDTO;
        try {
            roleDTO = roleService.update(id, createRoleDTO);
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }

        return new RapisolverResponse<>(200, "OK","Rol actualizado correctamente", roleDTO);
    }

    @DeleteMapping("/{id}")
    private RapisolverResponse<RoleDTO> deleteById(@PathVariable Long id) {
        try {
            roleService.deleteById(id);
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }

        return new RapisolverResponse<>(200, "OK","Rol eliminado correctamente");
    }

}
