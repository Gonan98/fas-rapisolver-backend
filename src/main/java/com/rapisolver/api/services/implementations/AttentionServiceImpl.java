package com.rapisolver.api.services.implementations;

import com.rapisolver.api.dtos.AttentionDTO;
import com.rapisolver.api.dtos.CreateAttentionDTO;
import com.rapisolver.api.entities.Attention;
import com.rapisolver.api.entities.Category;
import com.rapisolver.api.exceptions.InternalServerErrorException;
import com.rapisolver.api.exceptions.NotFoundException;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.repositories.AttentionRepository;
import com.rapisolver.api.repositories.CategoryRepository;
import com.rapisolver.api.services.AttentionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttentionServiceImpl implements AttentionService {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    @Autowired
    private AttentionRepository attentionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public AttentionDTO create(CreateAttentionDTO createAttentionDTO) throws RapisolverException {
        Category categoryDB = categoryRepository.findById(createAttentionDTO.getCategoryId()).orElseThrow(() -> new NotFoundException("CATEGORY_NOT_FOUND"));

        try {
            Attention attention = new Attention();
            attention.setName(createAttentionDTO.getName());
            attention.setCategory(categoryDB);
            attention = attentionRepository.save(attention);
            return MODEL_MAPPER.map(attention, AttentionDTO.class);
        } catch (Exception e) {
            throw new InternalServerErrorException("CREATE_ATTENTION_ERROR");
        }
    }

    @Override
    public List<AttentionDTO> getAll() throws RapisolverException {
        try {
            List<Attention> attentions = attentionRepository.findAll();
            return attentions.stream().map(a -> MODEL_MAPPER.map(a, AttentionDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new InternalServerErrorException("GET_ALL_ATTENTIONS_ERROR");
        }
    }
}
