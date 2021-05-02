package com.rapisolver.api.controllers;

import com.rapisolver.api.dtos.AttentionDTO;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.response.RapisolverResponse;
import com.rapisolver.api.services.AttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/rapiSolver/"+"/v1")
public class AttentionController {
    @Autowired
    AttentionService attentionService;

    @GetMapping("/attention/")
    private RapisolverResponse<List<AttentionDTO>> getAll() {
        List<AttentionDTO> attentionList;
        try {
            attentionList = attentionService.findAll();
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }
        return new RapisolverResponse<>(200, "OK","Lista de atenciones", attentionList);
    }

    @GetMapping("/attention/{id}")
    private RapisolverResponse<AttentionDTO> getByAttentionId(@PathVariable Long attentionId) {
        AttentionDTO attention;
        try {
            attention = attentionService.findById(attentionId);
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }
        return new RapisolverResponse<>(200, "OK","Atencion encontrada", attention);
    }
}
