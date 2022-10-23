package com.jikaigg.transaction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jikaigg.transaction.domain.User;
import com.jikaigg.transaction.service.UserService;
import com.jikaigg.transaction.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping("selectAll")
    @ResponseBody
    public String selectAll(){
        List<User> users = userServiceImpl.selectAll();
        String string = JSONObject.toJSONString(users);
        return string;
    }

    @RequestMapping("test")
    @ResponseBody
    public String test() throws Exception {
        userServiceImpl.A();
        return "success";
    }
}
