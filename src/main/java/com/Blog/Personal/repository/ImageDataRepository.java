package com.Blog.Personal.repository;


import com.Blog.Personal.model.ImageData;
import com.Blog.Personal.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ImageDataRepository extends JpaRepository<ImageData, Integer> {

    ImageData findByPost(Post post);

    Optional<ImageData> findByNameAndPost(String name, Post post);
}