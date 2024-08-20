package com.Blog.Personal.controller;


import com.Blog.Personal.config.AppConstants;
import com.Blog.Personal.payloads.CategoryDataTransfer;
import com.Blog.Personal.payloads.CategoryResponse;
import com.Blog.Personal.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class CategoryController {

    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<CategoryResponse> getAllCategory(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false)
            Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false)
            Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.CATEGORY_SORT_BY, required = false)
            String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false)
            String sortDir
    ){
        return new ResponseEntity<>(
                categoryService.getAllCategory(pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @PostMapping("/category/{id}")
    public CategoryDataTransfer getCategory(@PathVariable("id") Integer categoryId){
        return categoryService.getCategory(categoryId);
    }

    @PostMapping("/category")

    public ResponseEntity<CategoryDataTransfer> createCategory(@RequestBody CategoryDataTransfer categoryDTO) {

        CategoryDataTransfer created = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }


    @PutMapping("/category/{id}")
    public ResponseEntity<CategoryDataTransfer> updateCategory(@RequestBody CategoryDataTransfer categoryDTO, @PathVariable("id") Integer categoryId) {
        CategoryDataTransfer updated = categoryService.updateCategory(categoryDTO, categoryId);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }


    @DeleteMapping("/category/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
    }
}