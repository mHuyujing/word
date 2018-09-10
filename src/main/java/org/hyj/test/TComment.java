package org.hyj.test;

import com.alibaba.fastjson.JSONObject;
import org.hyj.bean.Comment;
import org.hyj.bean.User;
import org.hyj.bean.Word;
import org.hyj.dao.CommentMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

/**
 * Created by 胡宇靖 on 2018/6/28 0028.
 */
public class TComment {
    private ClassPathXmlApplicationContext context;
    private CommentMapper commentMapper;

    @Before
    public void init() {
        System.out.println("init");
        context = new ClassPathXmlApplicationContext(
                "spring_config.xml");
        commentMapper = (CommentMapper) context.getBean("commentMapper");
    }

    @Test
    public void insert() {
        Comment comment = new Comment();
        comment.setCommentDate(new Date());
        comment.setCommentText("第二次測試");
        User user = new User();user.setUserId(1);
        Word word = new Word();word.setWordId(1);
        comment.setUser(user);comment.setWord(word);
        commentMapper.insert(comment);
    }

    @Test
    public void selectCommentByWordId() {
        List<Comment> comments = commentMapper.selectCommentByWordId(null, 1);
        System.out.println(JSONObject.toJSONString(comments));
    }

    @Test
    public void deleteCommentByCommentIdAndUserId() {
        commentMapper.deleteCommentByCommentIdAndUserId(5, 1);
    }

    @Test
    public void selectCommentByUserId() {
        List<Comment> comments = commentMapper.selectCommentByUserId(null, 2);
        System.out.println(JSONObject.toJSONString(comments));
    }
}
