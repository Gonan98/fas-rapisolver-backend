package com.rapisolver.api.controllers;

import com.rapisolver.api.dtos.CreateUserAttentionDTO;
import com.rapisolver.api.dtos.UserAttentionDTO;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.response.RapisolverResponse;
import com.rapisolver.api.services.UserAttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users-attentions")
public class UserAttentionController {

    @Autowired
    private UserAttentionService userAttentionService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RapisolverResponse<UserAttentionDTO> create(@Valid @RequestBody CreateUserAttentionDTO createUserAttentionDTO) throws RapisolverException {
        return new RapisolverResponse<>(
                201,
                "CREATED",
                "Atencion de proveedor creada correctamente",
                userAttentionService.create(createUserAttentionDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/{userId}")
    public RapisolverResponse<List<UserAttentionDTO>> getByUserId(@PathVariable Long userId) throws RapisolverException {
        return new RapisolverResponse<>(200,"OK","Lista de atenciones creadas por el usuario "+userId, userAttentionService.getByUserId(userId));
    }
}
