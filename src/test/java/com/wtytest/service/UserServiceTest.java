package com.wtytest.service;

import com.wtytest.dao.UserRepository;
import com.wtytest.entity.User;
import com.wtytest.helper.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.MediaType;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class UserServiceTest extends BaseTest{
    private static final Long INVALID_ID = 100l;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @MockBean
    private RedisTemplate<String, User> redisTemplate;

    @BeforeEach
    public void setup(){
        when(redisTemplate.opsForValue()).thenReturn(mock(ValueOperations.class));
        when(redisTemplate.opsForValue().get(any()))
                .thenReturn(null)
                .thenReturn(new User());
    }

    @Test
    public void should_return_null_when_id_is_invalid() {
        Assertions.assertEquals(null, userService.findOne(INVALID_ID));
    }

    @ParameterizedTest
    @ValueSource(longs = { 1l, 2l, 3l })
    public void should_return_user_from_redis_when_request_twice(Long id) throws Exception{
        this.mvc.perform(get("/users/" + id).accept(MediaType.APPLICATION_JSON));
        this.mvc.perform(get("/users/" + id).accept(MediaType.APPLICATION_JSON));
        verify(redisTemplate.opsForValue(), times(2)).get(anyString());
        verify(redisTemplate.opsForValue(), times(1)).set(anyString(), any());
    }

    @Test
    public void should_return_user_from_mysql_when_request_once() throws Exception{
        this.mvc.perform(get("/users/" + 1).accept(MediaType.APPLICATION_JSON));
        verify(userRepository).findOne(anyLong());
        verify(redisTemplate.opsForValue()).set(anyString(), any());
    }
}
