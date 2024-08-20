package com.Blog.Personal.payloads;


import com.Blog.Personal.model.Category;
import com.Blog.Personal.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDataTransfer {
    private Integer postId;

    private String title;

    private String content;

    private String imageName;

    private Date date;

    private Category category;

    private User user;

}