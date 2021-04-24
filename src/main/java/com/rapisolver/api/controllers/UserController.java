package com.rapisolver.api.controllers;

import com.rapisolver.api.dtos.CreateUserDTO;
import com.rapisolver.api.dtos.UserDTO;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.response.RapisolverResponse;
import com.rapisolver.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    private RapisolverResponse<UserDTO> create(@RequestBody @Valid CreateUserDTO createUserDTO) {
        UserDTO userDTO;
        try {
            userDTO = userService.create(createUserDTO);
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }
        return new RapisolverResponse<>(201, "CREATED","Usuario creado correctamente", userDTO);
    }

    @GetMapping
    private RapisolverResponse<List<UserDTO>> getAll() {
        List<UserDTO> userDTOS;
        try {
            userDTOS = userService.findAll();
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }

        return new RapisolverResponse<>(200, "OK","Lista de usuarios", userDTOS);
    }

    @GetMapping("/{id}")
    private RapisolverResponse<UserDTO> getById(@PathVariable Long id) {
        UserDTO userDTO;
        try {
            userDTO = userService.findById(id);
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }

        return new RapisolverResponse<>(200, "OK","Usuario encontrado", userDTO);
    }
}
