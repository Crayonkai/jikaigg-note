package com.jikaigg.transaction.mapper;

import com.jikaigg.transaction.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> selectAll();
    User selectById(Long id);
    int updateById(User user);

    int deleteById(Long id);
}
