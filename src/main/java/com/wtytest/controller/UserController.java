package com.wtytest.controller;

import com.wtytest.entity.User;
import com.wtytest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")     // 通过这里配置使下面的映射都在/users下
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public User get(@PathVariable Long id) {
        return userService.findOne(id);
    }

    @PostMapping(value = "/")
    public String postUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping(value = "/")
    public String putUser(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.delete(id);
    }

}