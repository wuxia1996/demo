package com.wu.xia.demo.springboot_demo.service;

import com.wu.xia.demo.springboot_demo.entity.User;
import com.wu.xia.demo.springboot_demo.utils.JsonHelper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class UserService {
    private static JsonHelper jsonHelper =  JsonHelper.instance();

    public List<User> userList (){
        try {
            String user = jsonHelper.readUserInfo();
            return jsonHelper.parseList(user);
        }catch (Exception ex){
            return Collections.emptyList();
        }
    }

    public User addUser(User userInfo) throws Exception {
        List<User> users = userList();
        for (User u : users) {
            if (Objects.equals(u.getUserId(), userInfo.getUserId())){
                List<String> endpoint = u.getEndpoint();
                if (endpoint==null || endpoint.isEmpty()){
                    u.setEndpoint(userInfo.getEndpoint());
                }else {
                    for (String resource : userInfo.getEndpoint()) {
                        if (!endpoint.contains(resource)){
                            endpoint.add(resource);
                        }
                    }
                    u.setEndpoint(endpoint);
                }
                jsonHelper.writeFile("user.json", users);
                return u;
            }
        }
        return null;
    }

    public User get(Integer userId, String resource) {
        List<User> users = userList();
        for (User u : users) {
            if (Objects.equals(u.getUserId(), userId)){
                List<String> endpoint = u.getEndpoint();
                if (endpoint!=null && !endpoint.isEmpty() && endpoint.contains(resource)){
                    return u;
                }else {
                    return null;
                }
            }
        }
        return null;
    }


}
