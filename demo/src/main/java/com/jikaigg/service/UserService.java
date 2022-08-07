package com.jikaigg.service;

import com.jikaigg.orm.domain.BizUserDto;
import com.jikaigg.orm.mapper.BizUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class UserService {
    @Resource
    private BizUserMapper bizUserMapper;
    public List<BizUserDto> getOne(String username) {
        List<BizUserDto> userDtos = bizUserMapper.selectByUsername(username);
        log.debug("查询结果为：{}",userDtos);
        return userDtos;
    }
}
