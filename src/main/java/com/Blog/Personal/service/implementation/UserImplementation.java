package com.Blog.Personal.service.implementation;

import com.Blog.Personal.exception.ResourceNotFoundException;
import com.Blog.Personal.model.User;
import com.Blog.Personal.payloads.UserDataTransfer;
import com.Blog.Personal.payloads.UserResponse;
import com.Blog.Personal.repository.UserRepository;
import com.Blog.Personal.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserImplementation implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserImplementation(UserRepository userRepository ) {
        this.userRepository = userRepository;
        this.modelMapper = createModelMapper(); // Definición del bean aquí
    }

    private ModelMapper createModelMapper() {
        return new ModelMapper();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con el correo: " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    @Override
    public UserDataTransfer createUser(UserDataTransfer userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDataTransfer.class);
    }

    @Override
    public UserResponse getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<User> pageUser = userRepository.findAll(pageable);
        List<User> users = pageUser.getContent();
        List<UserDataTransfer> userDTO = users.stream()
                .map(user -> modelMapper.map(user, UserDataTransfer.class))
                .toList();

        UserResponse userResponse = new UserResponse();
        userResponse.setContent(userDTO);
        userResponse.setTotalElements(pageUser.getTotalElements());
        userResponse.setPageNumber(pageUser.getNumber());
        userResponse.setPageSize(pageUser.getSize());
        userResponse.setLastPage(pageUser.isLast());
        userResponse.setTotalPages(pageUser.getTotalPages());

        return userResponse;
    }


    @Override
    public UserDataTransfer getUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return modelMapper.map(user, UserDataTransfer.class);
    }

    @Override
    public UserDataTransfer updateUser(UserDataTransfer userDTO, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDataTransfer.class);
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        userRepository.delete(user);
    }



}



