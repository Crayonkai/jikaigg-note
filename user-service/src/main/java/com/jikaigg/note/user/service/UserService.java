package com.jikaigg.note.user.service;

import com.jikaigg.note.user.mapper.UserMapper;
import com.jikaigg.note.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryById(Long id) {
        return userMapper.findById(id);
    }
}