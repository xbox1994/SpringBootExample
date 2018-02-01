package com.wtytest.service;

import com.wtytest.config.PreInspectionItemMappingProperties;
import com.wtytest.config.ServiceContractBaseDataProperties;
import com.wtytest.dao.UserRepository;
import com.wtytest.entity.User;
import com.wtytest.helper.PdfUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

/**
 * Created by tywang on 27/06/2017.
 */
@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    private boolean isIdValid(Long id) {
        return id > 0 && id < 100;
    }

    @Autowired
    private PreInspectionItemMappingProperties preInspectionItemMappingProperties;

    @Autowired
    private ServiceContractBaseDataProperties serviceContractBaseDataProperties;

    @PostConstruct
    void show(){
        System.out.println(preInspectionItemMappingProperties);
        log.info(preInspectionItemMappingProperties.toString());
        System.out.println(serviceContractBaseDataProperties);
        log.info(serviceContractBaseDataProperties.toString());
    }


    public User findOne(Long id) {
        if (!isIdValid(id)) {
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

        PdfUtils.p();
        return user;
    }

    public String save(User user) {
        userRepository.save(user);
        return "success";
    }

    public String delete(Long id) {
        userRepository.delete(id);
        return "success";
    }
}
