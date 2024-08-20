package com.Blog.Personal.service;


import com.Blog.Personal.model.User;
import com.Blog.Personal.payloads.UserDataTransfer;
import com.Blog.Personal.payloads.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {


    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    UserDataTransfer createUser(UserDataTransfer userDTO);


    UserResponse getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);


    UserDataTransfer getUser(Integer userId);


    UserDataTransfer updateUser(UserDataTransfer userDTO, Integer userId);


    void deleteUser(Integer userId);

    //User findUserByEmail(String email);
}


// createUser, getAllUsers, getUser, updateUser, deleteUser