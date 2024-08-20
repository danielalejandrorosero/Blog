package com.Blog.Personal.repository;


import com.Blog.Personal.model.Category;
import com.Blog.Personal.model.Post;
import com.Blog.Personal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);

}