package com.rapisolver.api.services.impl;

import com.rapisolver.api.dtos.AttentionDTO;
import com.rapisolver.api.dtos.CreateAttentionDTO;
import com.rapisolver.api.dtos.CreateReservationDTO;
import com.rapisolver.api.entities.Attention;
import com.rapisolver.api.exceptions.InternalServerErrorException;
import com.rapisolver.api.exceptions.NotFoundException;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.repositories.AttentionRepository;
import com.rapisolver.api.services.AttentionService;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class AttentionServiceImpl implements AttentionService {
    AttentionRepository attentionRepository;

    public static final ModelMapper modelMapper=new ModelMapper();

    @Override
    public List<AttentionDTO> findAll() throws RapisolverException {
        List<Attention> attentionList;
        try {
            attentionList = attentionRepository.findAll();
        }catch (Exception e){
            throw new InternalServerErrorException("Lista de atenciones no encontranda");
        }
        return attentionList.stream().map(attention -> modelMapper.map(attention,AttentionDTO.class)).collect(Collectors.toList());
    }

    @Override
    public AttentionDTO findById(Long attentionId) throws RapisolverException {
        Attention attention = attentionRepository.findById(attentionId).orElseThrow(() -> new NotFoundException("Attencion con el id"+ attentionId +  "no encontrado"));
        return modelMapper.map(attention,AttentionDTO.class);
    }

    @Override
    public AttentionDTO createAttention(CreateAttentionDTO createAttentionDTO) throws RapisolverException {
        return null;
    }

    @Override
    public AttentionDTO updateAttention(Long id, CreateReservationDTO createReservationDTO) throws RapisolverException {
        return null;
    }
}
