package com.Blog.Personal.payloads;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CategoryDataTransfer {


    private String categoryId;

    private String categoryTitle;

    private String categoryDescription;
}