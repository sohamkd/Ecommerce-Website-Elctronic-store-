package com.cbs.elctronic.store.services;

import com.cbs.elctronic.store.dtos.CategoryDto;
import com.cbs.elctronic.store.dtos.PageableResponse;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto,String categoryId);

    void deleteCategory(String categoryId);

    PageableResponse<CategoryDto> getAllCategories(int pageNumber,int pageSize,String sortBy,String sortDir);

    CategoryDto getSingleCategory(String categoryId);


}
