package com.Blog.Personal.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;


    private String title;



    @Column(length = 1000000)
    private String content;


    private String imageName;


    private Date date;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;



    @ManyToOne
    private  User user;


    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ImageData imageData;

}