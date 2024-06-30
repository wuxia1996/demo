package com.wu.xia.demo.springboot_demo.controller;

import com.wu.xia.demo.springboot_demo.api.Resp;
import com.wu.xia.demo.springboot_demo.entity.User;
import com.wu.xia.demo.springboot_demo.service.UserService;
import com.wu.xia.demo.springboot_demo.utils.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService userService;
    private static JsonHelper jsonHelper = JsonHelper.instance();

    @PostMapping("/admin/addUser")
    public Object addUser(@RequestHeader("Authorization") String authorization, @RequestBody User userInfo) throws Exception {
        String decode = new String(Base64.getDecoder().decode(authorization.getBytes(StandardCharsets.UTF_8)));
        User user = jsonHelper.parse(decode, User.class);
        if ("admin".equals(user.getRole())){
            User row = userService.addUser(userInfo);
            if (row!=null){
                return Resp.success(row, "已添加权限!");
            }else {
                return Resp.failure(userInfo.getUserId()+" 不存在！");
            }
        }else {
            return Resp.failure("用户无权限!");
        }
    }

    @GetMapping("/user/{resource}")
    public Object get(@RequestHeader("Authorization") String authorization, @PathVariable("resource") String resource) {
        String decode = new String(Base64.getDecoder().decode(authorization.getBytes(StandardCharsets.UTF_8)));
        User user = jsonHelper.parse(decode, User.class);
        User userInfo = userService.get(user.getUserId(), resource);
        if (userInfo!=null){
            return Resp.success(userInfo);
        }else {
            return Resp.failure("无"+resource+"权限");
        }
    }

}
