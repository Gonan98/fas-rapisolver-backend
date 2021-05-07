package com.rapisolver.api.services;

import com.rapisolver.api.dtos.AttentionDTO;
import com.rapisolver.api.dtos.CreateAttentionDTO;
import com.rapisolver.api.exceptions.RapisolverException;

import java.util.List;

public interface AttentionService {

    AttentionDTO create(CreateAttentionDTO createAttentionDTO) throws RapisolverException;
    List<AttentionDTO> getAll() throws RapisolverException;

}
