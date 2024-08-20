package com.Blog.Personal.service.implementation;

import com.Blog.Personal.model.ImageData;
import com.Blog.Personal.model.Post;
import com.Blog.Personal.repository.ImageDataRepository;
import com.Blog.Personal.repository.PostRepository;
import com.Blog.Personal.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.Optional;

@Service
public class ImageImplementation implements ImageService {

    @Autowired
    private ImageDataRepository imageDataRepository;

    @Autowired
    private PostRepository postRepository;

    @Value("${image.upload.file}")
    private String folderPath;

    @Override
    public String uploadImage(MultipartFile image, Integer postId) throws IOException {
        if (image.getOriginalFilename() == null) {
            throw new IllegalArgumentException("El nombre del archivo de imagen no puede ser nulo");
        }

        String imageName = image.getOriginalFilename();
        String imageNameModified = postId.toString() + "_" + imageName;
        String imagePath = folderPath + imageNameModified;

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post con ID " + postId + " no encontrado"));

        ImageData imageData = imageDataRepository.findByPost(post);
        if (imageData == null) {
            imageData = new ImageData();
        }

        imageData.setName(imageNameModified);
        imageData.setType(image.getContentType());
        imageData.setFilePath(imagePath);
        imageData.setDate(new Date());
        imageData.setPost(post);

        imageDataRepository.save(imageData);

        File dir = new File(folderPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        Path path = Path.of(imagePath);
        Files.deleteIfExists(path);
        Files.copy(image.getInputStream(), path);

        return imageNameModified;
    }

    @Override
    public byte[] downloadImage(String imageName, Post post) throws IOException {
        String imageNameModified = post.getPostId().toString() + "_" + imageName;

        Optional<ImageData> fileData = imageDataRepository.findByNameAndPost(imageNameModified, post);

        String filePath = fileData.map(ImageData::getFilePath).orElse(null);

        if (filePath != null) {
            return Files.readAllBytes(Path.of(filePath));
        }

        return null;
    }
}
