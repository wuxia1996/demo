package com.wu.xia.demo.springboot_demo;

import com.wu.xia.demo.springboot_demo.entity.User;
import com.wu.xia.demo.springboot_demo.utils.JsonHelper;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JsonHelperTest {

    @Test
    public void parseList(){
        String json = "[{\"userId\":123456,\"accountName\":\"XXXXXXX\",\"role\":\"admin\",\"endpoint\":[\"A\",\"B\",\"C\"]},{\"userId\":1234,\"accountName\":\"wwww\",\"role\":\"user\",\"endpoint\":[]}]";
        List<User> userList = JsonHelper.instance().parseList(json);
        for (User user : userList) {
            System.out.print(user);
        }
    }
}
