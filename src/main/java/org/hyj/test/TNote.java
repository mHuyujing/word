package org.hyj.test;

import com.alibaba.fastjson.JSONObject;
import org.hyj.bean.Note;
import org.hyj.bean.User;
import org.hyj.bean.Word;
import org.hyj.dao.NoteMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 胡宇靖 on 2018/6/28 0028.
 */
public class TNote {
    private ClassPathXmlApplicationContext context;
    private NoteMapper noteMapper;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("spring_config.xml");
        noteMapper = (NoteMapper) context.getBean("noteMapper");
    }

    @Test
    public void insert(){
        Note note = new Note();
        User user = new User();user.setUserId(1);
        Word word = new Word();word.setWordId(1);
        note.setUser(user);
        note.setWord(word);
        note.setNoteText("TESTNOTE");
        noteMapper.insert(note);
    }
    @Test
    public void selectNoteByUserIdAndWordId() {
        Note note = noteMapper.selectNoteByUserIdAndWordId(1, 1);
        System.out.println(JSONObject.toJSONString(note));
    }

    @Test
    public void updateNoteByWordId() {
        noteMapper.updateNoteByWordId(1, 1, "nihaoTest");
    }
}
