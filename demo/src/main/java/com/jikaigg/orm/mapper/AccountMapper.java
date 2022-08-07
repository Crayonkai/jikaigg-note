package com.jikaigg.orm.mapper;

import com.jikaigg.orm.domain.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
    Account selectByUsername(String username);
}