package com.jikaigg.orm.mapper;

import com.jikaigg.orm.domain.BizUserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BizUserMapper {
    List<BizUserDto> selectByUsername(String name);
}
