package com.Blog.Personal.repository;


import com.Blog.Personal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String userDTO);

    //User findUserByEmail(UserDataTransfer email);
}