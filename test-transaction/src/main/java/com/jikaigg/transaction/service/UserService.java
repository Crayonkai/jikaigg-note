package com.jikaigg.transaction.service;

import com.jikaigg.transaction.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    List<User> selectAll();
}
