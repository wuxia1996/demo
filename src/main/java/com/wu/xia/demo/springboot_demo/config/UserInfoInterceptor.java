package com.wu.xia.demo.springboot_demo.config;

import com.wu.xia.demo.springboot_demo.entity.User;
import com.wu.xia.demo.springboot_demo.utils.JsonHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserInfoInterceptor implements HandlerInterceptor {

    private static JsonHelper jsonHelper = JsonHelper.instance();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String authorization = request.getHeader("Authorization");
        String decode = new String(Base64.getDecoder().decode(authorization.getBytes(StandardCharsets.UTF_8)));
        User user = jsonHelper.parse(decode, User.class);
        List<User> userList =jsonHelper.parseList(jsonHelper.readUserInfo());
        List<User> filterList = userList.stream().filter(u->{
            if (Objects.equals(u.getUserId(), user.getUserId()) && u.getAccountName().equals(user.getAccountName()) && u.getRole().equals(user.getRole())){
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        if (!filterList.isEmpty()){
            return true;
        }else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
            return false;
        }
    }
}
