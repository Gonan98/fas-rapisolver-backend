package com.rapisolver.api.controllers;

import com.rapisolver.api.dtos.CreateUserDTO;
import com.rapisolver.api.dtos.UserDTO;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.response.RapisolverResponse;
import com.rapisolver.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    private RapisolverResponse<UserDTO> create(@RequestBody @Valid CreateUserDTO createUserDTO) throws RapisolverException {
        return new RapisolverResponse<>(201, "CREATED","Usuario creado correctamente", userService.create(createUserDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    private RapisolverResponse<List<UserDTO>> getAll() throws RapisolverException {
        return new RapisolverResponse<>(200, "OK","Lista de usuarios", userService.findAll());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    private RapisolverResponse<UserDTO> getById(@PathVariable Long id) throws RapisolverException {
        return new RapisolverResponse<>(200, "OK","Usuario encontrado", userService.findById(id));
    }
}
