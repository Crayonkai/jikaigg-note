package com.jikaigg.note.user.web;

import com.jikaigg.note.user.pojo.User;
import com.jikaigg.note.user.config.ParttenConfig;
import com.jikaigg.note.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/user")
//@RefreshScope  // 自动刷新，配置热更新
public class UserController {

    @Autowired
    private UserService userService;

//    @Value("${partten.dateformat}")
//    private String dateformat;
    @Autowired
    private ParttenConfig parttenConfig;

    @GetMapping("now")
    private String getNow(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(parttenConfig.getDateformat()));
    }
    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id) {
        return userService.queryById(id);
    }
}
