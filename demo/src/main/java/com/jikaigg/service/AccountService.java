package com.jikaigg.service;

import com.jikaigg.orm.domain.Account;
import com.jikaigg.orm.mapper.AccountMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountService {
    @Resource
    private AccountMapper accountMapper;

    public Account findByUsername(String username) {
        Account account = accountMapper.selectByUsername(username);
        return account;
    }
}
