package com.Blog.Personal.service.implementation;


import com.Blog.Personal.model.Category;
import com.Blog.Personal.model.Post;
import com.Blog.Personal.model.User;
import com.Blog.Personal.payloads.PostDataTransfer;
import com.Blog.Personal.payloads.PostResponse;
import com.Blog.Personal.repository.CategoryRepository;
import com.Blog.Personal.repository.PostRepository;
import com.Blog.Personal.repository.UserRepository;
import com.Blog.Personal.service.PostService;
import org.hibernate.ResourceClosedException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class    PostImplementation implements PostService {

    // injection of dependencies

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PostImplementation(PostRepository postRepository, UserRepository userRepository, CategoryRepository categoryRepository ) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = createModelMapper(); // Definición del bean aquí
    }

    private ModelMapper createModelMapper() {
        return new ModelMapper();
    }


    @Override
    public PostDataTransfer createPost(PostDataTransfer postDTO, Integer userId, Integer categoryId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceClosedException("User" + userId + "not found"));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceClosedException("Category" + categoryId + "not found"));

        Post post = modelMapper.map(postDTO, Post.class);
        post.setUser(user);
        post.setDate(new Date());
        post.setCategory(category);
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());

        Post savePost  = postRepository.save(post);

        return modelMapper.map(savePost, PostDataTransfer.class);
    }

    @Override
    public PostDataTransfer updatePost(PostDataTransfer postDTO, Integer postId) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceClosedException("Post" + postId + "not found"));

        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setDate(new Date());
        post.setImageName(postDTO.getImageName());

        Post updatePost = postRepository.save(post);

        return modelMapper.map(updatePost, PostDataTransfer.class);
    }

    @Override
    public void deletePost(Integer postId) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceClosedException("Post" + postId + "not found"));
        postRepository.delete(post);

    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();




        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost = postRepository.findAll(pageable);

        List<Post> posts = pagePost.getContent();

        List<PostDataTransfer> postDTO = posts.stream().map((post) -> modelMapper.map(post, PostDataTransfer.class)).toList();

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDTO);
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    // obtener por id los  3 primeros posts

    @Override
    public PostDataTransfer getPostById(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceClosedException("Post" + postId + "not found"));
        return modelMapper.map(post, PostDataTransfer.class);
    }

    @Override
    public List<PostDataTransfer> getAllPostByCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceClosedException("Category" + categoryId + "not found"));

        List<Post> posts = postRepository.findByCategory(category);

        return posts.stream().map((post) -> modelMapper.map(post, PostDataTransfer.class)).toList();
    }

    @Override
    public List<PostDataTransfer> getAllPostByUser(Integer userId) {

        User user  = userRepository.findById(userId).orElseThrow(() -> new ResourceClosedException("User" + userId + "not found"));

        List<Post> posts = postRepository.findByUser(user);

        return posts.stream().map((post) -> modelMapper.map(post, PostDataTransfer.class)).toList();

    }

    @Override
    public List<PostDataTransfer> searchPost(String keyword) {

        List<Post> posts = postRepository.findByTitleContaining(keyword);
        return posts.stream().map((post) -> modelMapper.map(post, PostDataTransfer.class)).toList();
    }
}