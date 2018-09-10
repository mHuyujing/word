package org.hyj.test;

import com.alibaba.fastjson.JSONObject;
import org.hyj.bean.History;
import org.hyj.bean.User;
import org.hyj.bean.Word;
import org.hyj.dao.HistoryMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by 胡宇靖 on 2018/6/28 0028.
 */
public class THistory {

    private ClassPathXmlApplicationContext context;
    private HistoryMapper historyMapper;

    @Before
    public void init(){
        context = new ClassPathXmlApplicationContext("spring_config.xml");
        historyMapper = (HistoryMapper) context.getBean("historyMapper");
    }
    @Test
    public void insert(){
        History history = new History();
        history.setHistoryNum(5);
        User user = new User();user.setUserId(1);
        Word word = new Word();word.setWordId(1);
        history.setUser(user);
        history.setWord(word);
        historyMapper.insert(history);
    }
    @Test
    public void selectHistoryByUserId(){
        List<History> histories = historyMapper.selectHistoryByUserId(null, 1);
        System.out.println(JSONObject.toJSONString(histories));
    }
    @Test
    public void deleteHistoryByUserId(){
        historyMapper.deleteHistoryByUserId(2);
    }
}
