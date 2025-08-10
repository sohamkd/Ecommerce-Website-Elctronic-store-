package com.cbs.elctronic.store.services;


import com.cbs.elctronic.store.dtos.PageableResponse;
import com.cbs.elctronic.store.dtos.ProductDto;

public interface ProductService {

    ProductDto create(ProductDto productDto);

    ProductDto update(ProductDto productDto,String productId);

    void delete(String productId);

    ProductDto getSinglePro(String productId);

    PageableResponse<ProductDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);

    PageableResponse<ProductDto> getAllLive(int pageNumber,int pageSize,String sortBy,String sortDir);

    PageableResponse<ProductDto> searchByTitle(String subTitle,int pageNumber,int pageSize,String sortBy,String sortDir);
}
