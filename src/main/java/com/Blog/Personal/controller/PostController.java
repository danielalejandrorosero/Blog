package com.Blog.Personal.controller;


import com.Blog.Personal.config.AppConstants;
import com.Blog.Personal.exception.ResourceNotFoundException;
import com.Blog.Personal.model.Post;
import com.Blog.Personal.payloads.PostDataTransfer;
import com.Blog.Personal.payloads.PostResponse;
import com.Blog.Personal.service.ImageService;
import com.Blog.Personal.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;


    private final ImageService imageService;

    private final ModelMapper modelMapper;

    @Autowired
    public PostController(PostService postService,ImageService imageService) {
        this.postService = postService;
        this.imageService = imageService;
        this.modelMapper =  createModelMapper();
    }

    private ModelMapper createModelMapper() {
        return new ModelMapper();
    }

    // create
    @PostMapping("/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostDataTransfer> createPost(@RequestBody PostDataTransfer postDTO,
                                                       @PathVariable Integer userId,
                                                       @PathVariable Integer categoryId) {
        PostDataTransfer created = postService.createPost(postDTO, userId, categoryId);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/post")
    public ResponseEntity<List<PostDataTransfer>> getPostByUser(@PathVariable Integer userId){

        return new ResponseEntity<>(postService.getAllPostByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/post")
        public ResponseEntity<List<PostDataTransfer>> getPostByCategory(@PathVariable Integer categoryId){

            return new ResponseEntity<>(postService.getAllPostByCategory(categoryId), HttpStatus.OK);
        }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false)
            Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false)
            Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.POST_SORT_BY, required = false)
            String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false)
            String sortDir
    ){
        return new ResponseEntity<>(postService.getAllPost(pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    // get post by id
    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDataTransfer> getPostById(@PathVariable Integer postId) {
        return new ResponseEntity<>(postService.getPostById(postId), HttpStatus.OK);
    }

    // update post
    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDataTransfer> updatePost(@RequestBody PostDataTransfer postDTO, @PathVariable Integer postId) {

        PostDataTransfer updated = postService.updatePost(postDTO, postId);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // delete post
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }



        /*
        This is API is used to upload image for post. Only 1 image is allowed to upload for one post.
        This will store image into file storage and store that image path in database.
        Image path store in database and table name is "image_data".
        This table will store image name, image type, image path, image modified date with postId
        And for Post image name will also store in "post" table in database.
    */
    @PostMapping("/post/upload/image/{postId}")
    public ResponseEntity<?> uploadPostImage(@RequestParam("image")MultipartFile image, @PathVariable Integer postId) throws Exception {


        if (image.isEmpty()) {
            throw new IllegalArgumentException("El archivo de imagen no puede ser nulo");
        }

        String imageName;

        PostDataTransfer postDTO = postService.getPostById(postId);


        if (postDTO == null) {
            return new ResponseEntity<>(new ResourceNotFoundException("Post", "id", postId), HttpStatus.NOT_FOUND);
        } else {
            try {

                imageName = imageService.uploadImage(image, postId);

                postDTO.setImageName(imageName);

                PostDataTransfer updated = postService.updatePost(postDTO, postId);

                return new ResponseEntity<>(updated, HttpStatus.OK);

            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }


    @GetMapping("/post/{postId}/image/{imageName}")
    public ResponseEntity<?> download(@PathVariable Integer postId, @PathVariable String imageName) throws Exception {

        PostDataTransfer postDTO = postService.getPostById(postId);

        byte[] imageData = imageService.downloadImage(imageName, modelMapper.map(postDTO, Post.class));

        if (imageData != null ){
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_PNG).body(imageData);
    } else {
        return new ResponseEntity<>(new ResourceNotFoundException("Post", "id", postId), HttpStatus.NOT_FOUND);
        }
    }
}


