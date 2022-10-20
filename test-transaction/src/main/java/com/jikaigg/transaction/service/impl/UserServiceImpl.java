package com.jikaigg.transaction.service.impl;

import com.jikaigg.transaction.domain.User;
import com.jikaigg.transaction.mapper.UserMapper;
import com.jikaigg.transaction.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    //    @Transactional
    public String test1() {
        try {
            userMapper.selectAll();
            User yaojikai = new User(1L, "yaojikai", 20);
            userMapper.updateById(yaojikai);
            int i = 1 / 0;
            User yaojikai2 = new User(1L, "yaojikai", 30);
            userMapper.updateById(yaojikai2);
            return "success";
        } catch (Exception e) {
            log.error("处理失败", e);
            return "fail";
        }
    }

    public String test2() {
        return null;
    }


}
