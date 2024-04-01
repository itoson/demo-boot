package org.example.controller;

import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/m1")
    @ResponseBody
    public String m1() {
        List<User> userList = userMapper.selectList(null);
        return userList.toString();
    }
}
