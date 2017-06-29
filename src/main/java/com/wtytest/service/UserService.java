package com.wtytest.service;

import com.wtytest.dao.UserRepository;
import com.wtytest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * Created by tywang on 27/06/2017.
 */
@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    private boolean isIdValid(Long id){
        return ( id <= 0 || id >= 100 ) ? false : true;
    }

    public User findOne(Long id){
        if(!isIdValid(id)){
            return null;
        }
        User user = redisTemplate.opsForValue().get(id + "");
        if (user != null) {
            Logger.getLogger("UserService").info("user " + id + ", find it in redis");
        } else {
            Logger.getLogger("UserService").info("user " + id + ", find it in mysql");
            user = userRepository.findOne(id);
            redisTemplate.opsForValue().set(id + "", user);
        }
        return user;
    }

    public String save(User user){
        userRepository.save(user);
        return "success";
    }

    public String delete(Long id){
        userRepository.delete(id);
        return "success";
    }
}
