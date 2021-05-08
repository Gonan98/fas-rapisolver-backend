package com.rapisolver.api.controllers;

import com.rapisolver.api.dtos.CategoryDTO;
import com.rapisolver.api.dtos.CreateCategoryDTO;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.response.RapisolverResponse;
import com.rapisolver.api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RapisolverResponse<CategoryDTO> create(@Valid @RequestBody CreateCategoryDTO createCategoryDTO) throws RapisolverException {
        return new RapisolverResponse<>(201,
                "CREATED",
                "Categoria creada correctamente",
                categoryService.create(createCategoryDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public RapisolverResponse<List<CategoryDTO>> getAll() throws RapisolverException {
        return new RapisolverResponse<>(201,
                "OK",
                "Lista de categorias",
                categoryService.findAll());
    }

}
