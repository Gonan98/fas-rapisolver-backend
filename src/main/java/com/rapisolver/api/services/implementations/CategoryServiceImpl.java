package com.rapisolver.api.services.implementations;

import com.rapisolver.api.dtos.CategoryDTO;
import com.rapisolver.api.dtos.CreateCategoryDTO;
import com.rapisolver.api.entities.Category;
import com.rapisolver.api.exceptions.InternalServerErrorException;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.repositories.CategoryRepository;
import com.rapisolver.api.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO create(CreateCategoryDTO createCategoryDTO) throws RapisolverException {
        Category category = new Category();
        category.setName(createCategoryDTO.getName());
        category.setDescription(createCategoryDTO.getDescription());

        try {
            category = categoryRepository.save(category);
        } catch (Exception e) {
            throw new InternalServerErrorException("Error al crear una categoria");
        }

        return MODEL_MAPPER.map(category, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> findAll() throws RapisolverException {
        try {
            List<Category> categories = categoryRepository.findAll();
            return categories.stream().map(c -> MODEL_MAPPER.map(c, CategoryDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new InternalServerErrorException("Error al obtener todas las categorias");
        }
    }
}
