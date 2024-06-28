package org.example.service;

import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User getUserById(Long id) {
        return userMapper.selectById(id);
    }
}
