package com.wu.xia.demo.springboot_demo.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wu.xia.demo.springboot_demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class JsonHelper {
    public static JsonHelper instance(){
        return new JsonHelper();
    }

    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> T parse(String json, Class clazz){
        try {
            return (T) mapper.readValue(json, clazz);
        } catch (Exception ex){
            log.error("发序列化{}失败",json);
            return null;
        }
    }

    public static List<User> parseList(String json){
        try {
            return mapper.readValue(json, new TypeReference<List<User>>() {
            });
        } catch (Exception ex){
            log.error("发序列化{}失败",json);
            return null;
        }
    }

    public static String serialize(Object obj){
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception ex) {
            log.error("序列化{}失败",obj);
            return "";
        }
    }


    public void writeFile(String file, Object content) throws Exception {
        try{
            mapper.writeValue(new File(System.getProperty("user.dir")+"/user.json"),content);
        } catch (Exception ex) {
            log.error("{} 写入文件 {} 失败", content, file);
            throw new Exception(ex);
        }
    }


    public static String readUserInfo() throws Exception {
        try(InputStream inputStream = new FileInputStream(System.getProperty("user.dir")+"/user.json")) {
            return IOUtils.toString(inputStream, String.valueOf(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("读取用户信息失败");
            throw new Exception(e);
        }
    }

}
