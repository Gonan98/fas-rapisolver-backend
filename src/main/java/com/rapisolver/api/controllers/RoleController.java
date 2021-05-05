package com.rapisolver.api.controllers;

import com.rapisolver.api.dtos.CreateRoleDTO;
import com.rapisolver.api.dtos.RoleDTO;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.response.RapisolverResponse;
import com.rapisolver.api.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RapisolverResponse<RoleDTO> create(@RequestBody @Valid CreateRoleDTO createRoleDTO) throws RapisolverException {
        return new RapisolverResponse<>(201,
                "CREATED",
                "Rol creado correctamente",
                roleService.create(createRoleDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public RapisolverResponse<List<RoleDTO>> getAll() throws RapisolverException {
        return new RapisolverResponse<>(200,
                "OK",
                "Lista de roles",
                roleService.findAll());
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public RapisolverResponse<RoleDTO> updateById(@PathVariable Long id, @RequestBody @Valid CreateRoleDTO createRoleDTO) throws RapisolverException {
        return new RapisolverResponse<>(200,
                "OK",
                "Rol actualizado correctamente",
                roleService.update(id, createRoleDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public RapisolverResponse<RoleDTO> deleteById(@PathVariable Long id) throws RapisolverException {
        roleService.deleteById(id);
        return new RapisolverResponse<>(200,
                "OK",
                "Rol eliminado correctamente");
    }

}
