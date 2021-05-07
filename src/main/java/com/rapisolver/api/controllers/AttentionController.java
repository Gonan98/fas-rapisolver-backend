package com.rapisolver.api.controllers;

import com.rapisolver.api.dtos.AttentionDTO;
import com.rapisolver.api.dtos.CreateAttentionDTO;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.response.RapisolverResponse;
import com.rapisolver.api.services.AttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/attentions")
public class AttentionController {

    @Autowired
    private AttentionService attentionService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RapisolverResponse<AttentionDTO> create(@Valid @RequestBody CreateAttentionDTO createAttentionDTO) throws RapisolverException {
        return new RapisolverResponse<>(201,"CREATED","Atencion creada correctamente", attentionService.create(createAttentionDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public RapisolverResponse<List<AttentionDTO>> getAll() throws RapisolverException {
        return new RapisolverResponse<>(200,"OK","Lista de atenciones", attentionService.getAll());
    }

}
