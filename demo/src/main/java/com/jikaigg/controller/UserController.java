package com.jikaigg.controller;

import com.jikaigg.common.Result;
import com.jikaigg.domain.UserVo;
import com.jikaigg.orm.domain.BizUserDto;
import com.jikaigg.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.assertj.core.util.DateUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/getOne")
    public Result getUser(@RequestBody UserVo userVo) {
        log.debug("userVo is : {}", userVo);
        List<BizUserDto> userDtos = null;
        String username = userVo.getUsername();
        if (StringUtils.isNotBlank(username))
            userDtos = userService.getOne(username);
        if (CollectionUtils.isEmpty(userDtos))
            return Result.fail("9999", "查询用户信息为空");
        return Result.ok(userDtos.get(0));
    }

    public static void main(String[] args) {
        long start = 1657123200000L;
        long end = 1657209600000L;
        long result = end-start;
        int a = 60*60*24;
        System.out.println(result);
        System.out.println(a);
    }

}
