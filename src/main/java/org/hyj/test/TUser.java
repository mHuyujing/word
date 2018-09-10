package org.hyj.test;

import com.alibaba.fastjson.JSONObject;
import org.hyj.bean.User;
import org.hyj.dao.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 胡宇靖 on 2018/6/28 0028.
 */
public class TUser {
    private ClassPathXmlApplicationContext context;
    private UserMapper userMapper;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("spring_config.xml");
        userMapper= (UserMapper) context.getBean("userMapper");
    }
    @Test
    public void selectUserByName(){
        User hyj = userMapper.selectUserByName("hyj");
        System.out.println(JSONObject.toJSONString(hyj));
    }
    @Test
    public void selectUserByNameAndPassword(){
        User hyj = userMapper.selectUserByNameAndPassword("hyj", "123456");
        System.out.println(JSONObject.toJSONString(hyj));
    }
    @Test
    public void selectUserPasswordByUserId(){
        User user = userMapper.selectUserPasswordByUserId(1);
        System.out.println(JSONObject.toJSONString(user));
    }
}
