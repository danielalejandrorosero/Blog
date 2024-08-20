package com.Blog.Personal.controller;


import com.Blog.Personal.config.AppConstants;
import com.Blog.Personal.payloads.UserDataTransfer;
import com.Blog.Personal.payloads.UserResponse;
import com.Blog.Personal.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")



public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String welcome() {
        return "Welcome to Blog Application Api";
    }

    @PostMapping("/user")
    public ResponseEntity<UserDataTransfer> createUser(@RequestBody UserDataTransfer userDTO) {

        UserDataTransfer userDataTransfer = userService.createUser(userDTO);
        return ResponseEntity.ok(userDataTransfer);
    }

    @GetMapping("/users")
    public ResponseEntity<UserResponse> getAllUser(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false)
            Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false)
            Integer pageSize
    ){
        return new ResponseEntity<>(userService.getAllUsers(pageNumber, pageSize, AppConstants.POST_SORT_BY, AppConstants.SORT_DIR), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public UserDataTransfer getAllUser(@PathVariable Integer userId){
        return userService.getUser(userId);
    }

    @PutMapping("/user/{userId}")
    public UserDataTransfer updateUser(@RequestBody UserDataTransfer userDTO, @PathVariable Integer userId){
        return userService.updateUser(userDTO, userId);
    }

    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
    }
}