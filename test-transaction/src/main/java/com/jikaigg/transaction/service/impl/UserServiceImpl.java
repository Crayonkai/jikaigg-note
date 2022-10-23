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

    @Transactional(rollbackFor = Exception.class)
    public String A() throws Exception {
        userMapper.deleteById(2L);
        try {
            int i = 1 / 0;
        }catch (ArithmeticException a){
            throw new Exception("aaa");
        }
        return "success";
    }

    @Transactional
    public String B(){
        userMapper.deleteById(3L);
        int i = 1 / 0;
        return "success";
    }


}
