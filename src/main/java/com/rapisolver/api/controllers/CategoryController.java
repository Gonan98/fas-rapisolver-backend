package com.rapisolver.api.controllers;

import com.rapisolver.api.dtos.CategoryDTO;
import com.rapisolver.api.dtos.CreateCategoryDTO;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.response.RapisolverResponse;
import com.rapisolver.api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public RapisolverResponse<CategoryDTO> create(@RequestBody @Valid CreateCategoryDTO createCategoryDTO) {
        CategoryDTO categoryDTO;
        try {
            categoryDTO = categoryService.create(createCategoryDTO);
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }
        return new RapisolverResponse<>(201, "CREATED","Categoria creada correctamente", categoryDTO);
    }

    @GetMapping
    public RapisolverResponse<List<CategoryDTO>> getAll() {
        List<CategoryDTO> categoryDTOS;
        try {
            categoryDTOS = categoryService.findAll();
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }

        return new RapisolverResponse<>(201, "OK","Lista de categorias", categoryDTOS);
    }

}
