package com.jikaigg.transaction.service.impl;

import com.jikaigg.transaction.domain.User;
import com.jikaigg.transaction.mapper.UserMapper;
import com.jikaigg.transaction.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }
}
