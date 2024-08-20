package com.Blog.Personal.service;


import com.Blog.Personal.payloads.CategoryDataTransfer;
import com.Blog.Personal.payloads.CategoryResponse;

public interface CategoryService {



    // create
    CategoryDataTransfer createCategory(CategoryDataTransfer categoryDTO);



    // get all

    CategoryResponse getAllCategory(int pageNumber, int pageSize, String sortBy, String sortDir);


    // get category by id

    CategoryDataTransfer getCategory(Integer categoryId);


    // update category

    CategoryDataTransfer updateCategory(CategoryDataTransfer categoryDTO, Integer categoryId);


    // delete category

    CategoryDataTransfer deleteCategory(Integer categoryId);





}