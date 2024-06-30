package com.wu.xia.demo.springboot_demo;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncodeTest {

    @Test
    public void encode(){
        String str = "{\n" +
                "\"userId\":12345,\n" +
                "\"accountName\": \"XXXXXXX\",\n" +
                "\"role\": \"admin\"\n" +
                "}";
        System.out.println(Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8)));
    }
}
