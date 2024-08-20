package com.Blog.Personal.service.implementation;

import com.Blog.Personal.model.Category;
import com.Blog.Personal.payloads.CategoryDataTransfer;
import com.Blog.Personal.payloads.CategoryResponse;
import com.Blog.Personal.repository.CategoryRepository;
import com.Blog.Personal.service.CategoryService;
import org.hibernate.ResourceClosedException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryImplementation implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryImplementation(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = createModelMapper(); // Definición del bean aquí
    }

    // Método para crear el bean ModelMapper
    // @Bean
    private ModelMapper createModelMapper() {
        return new ModelMapper();
    }

    @Override
    public CategoryDataTransfer createCategory(CategoryDataTransfer categoryDTO) {
        Category category = this.modelMapper.map(categoryDTO, Category.class);
        Category saved = categoryRepository.save(category);
        return modelMapper.map(saved, CategoryDataTransfer.class);
    }

    @Override
    public CategoryResponse getAllCategory(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);

        List<Category> categories = categoryPage.getContent();
        List<CategoryDataTransfer> categoryDTO = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDataTransfer.class))
                .toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTO);
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setLastPage(categoryPage.isLast());

        return null;
    }

    @Override
    public CategoryDataTransfer getCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).get();
        return modelMapper.map(category, CategoryDataTransfer.class);
    }

    @Override
    public CategoryDataTransfer updateCategory(CategoryDataTransfer categoryDTO, Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceClosedException("Category " + categoryId + " not found"));

        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setCategoryDescription(categoryDTO.getCategoryDescription());

        Category updated = categoryRepository.save(category);
        return modelMapper.map(updated, CategoryDataTransfer.class);
    }

    @Override
    public CategoryDataTransfer deleteCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceClosedException("Category " + categoryId + " not found"));

        categoryRepository.delete(category);
        return null;
    }
}
