package com.Blog.Personal.payloads;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDataTransfer {
    private Integer id;

    private String name;

    private String email;

    private String password;

    private String about;

}