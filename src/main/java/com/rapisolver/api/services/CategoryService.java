package com.rapisolver.api.services;

import com.rapisolver.api.dtos.CategoryDTO;
import com.rapisolver.api.dtos.CreateCategoryDTO;
import com.rapisolver.api.exceptions.RapisolverException;

import java.util.List;

public interface CategoryService {

    CategoryDTO create(CreateCategoryDTO createCategoryDTO) throws RapisolverException;
    List<CategoryDTO> findAll() throws RapisolverException;

}
