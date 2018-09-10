package org.hyj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.hyj.bean.*;
import org.hyj.dao.HistoryMapper;
import org.hyj.dao.NoteMapper;
import org.hyj.dao.UserMapper;
import org.hyj.dao.WordMapper;
import org.hyj.utils.WordUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 胡宇靖 on 2018/6/26 0026.
 */
@Controller
public class WordController {
    @RequestMapping(value = ("/word.do"), method = RequestMethod.GET)
    public String word(ModelMap mm, HttpServletRequest req, String wordName, Integer wordId) {
        WebApplicationContext context = WebApplicationContextUtils
                .getWebApplicationContext(req.getServletContext());
        WordMapper wordMapper = (WordMapper) context.getBean("wordMapper");
        WordWithBLOBs wordWithBLOBs = null;
        if (wordId != null) {
            wordWithBLOBs = wordMapper.selectByPrimaryKey(wordId);
            mm.addAttribute("word", wordWithBLOBs);
        } else if (wordName != null) {
            wordWithBLOBs = wordMapper.selectWordByName(wordName);
            mm.addAttribute("word", wordWithBLOBs);
        }
        if (wordWithBLOBs == null) {
            mm.addAttribute("info", "没有当前的这个单词信息:" + wordName);
            return "error";
        }else {
            System.out.println(JSONObject.toJSON(wordWithBLOBs));
            return "word";
        }
    }

    @ResponseBody
    @RequestMapping(value = ("/getWord.do"), method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getWord(HttpServletRequest req, Integer wordId) {
        WebApplicationContext context = WebApplicationContextUtils
                .getWebApplicationContext(req.getServletContext());
        WordMapper wordMapper = (WordMapper) context.getBean("wordMapper");
        HistoryMapper historyMapper = (HistoryMapper) context.getBean("historyMapper");
        WordWithBLOBs dbWord = wordMapper.selectByPrimaryKey(wordId);
        // 插入查询历史
        //
        User user = (User) req.getSession().getAttribute("user");
        History history = historyMapper.selectHistoryByUserIdAndWordId(user.getUserId(), wordId);
        if (history == null) {
            History historyTemp = new History();
            historyTemp.setWord(dbWord);
            historyTemp.setUser(user);
            historyTemp.setHistoryNum(1);
            historyMapper.insert(historyTemp);
        } else {
            history.setHistoryNum(history.getHistoryNum() + 1);
            historyMapper.updateByPrimaryKey(history);
        }
        System.out.println("Word-JSONObject:" + JSONObject.toJSONString(dbWord));
        // 格式化word的数据
        return JSONObject.toJSONString(dbWord);
    }

    @ResponseBody
    @RequestMapping(value = ("/getSearchKey.do"), method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getSearchKey(HttpServletRequest req, String wordName) {
        WebApplicationContext context = WebApplicationContextUtils
                .getWebApplicationContext(req.getServletContext());
        WordMapper wordMapper = (WordMapper) context.getBean("wordMapper");
        List<Word> word = wordMapper.selectWordKey(wordName);
        for (Word w : word) {
            System.out.println(JSONObject.toJSONString(w));
        }
        return JSONObject.toJSONString(word);
    }

    @ResponseBody
    @RequestMapping(value = ("/getNote.do"), method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getNote(HttpServletRequest req, Integer wordId) {
        WebApplicationContext context = WebApplicationContextUtils
                .getWebApplicationContext(req.getServletContext());
        NoteMapper noteMapper = (NoteMapper) context.getBean("noteMapper");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Integer userId = user.getUserId();
        Note note = noteMapper.selectNoteByUserIdAndWordId(userId, wordId);
        return JSONObject.toJSONString(note);
    }


    @ResponseBody
    @RequestMapping(value = ("/insertNote.do"), method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String insertNote(Note note, Word word, HttpServletRequest request) {
        WebApplicationContext context = WebApplicationContextUtils
                .getWebApplicationContext(request.getServletContext());
        NoteMapper noteMapper = (NoteMapper) context.getBean("noteMapper");
        try {
            note.setWord(word);
            User user = (User) request.getSession().getAttribute("user");
            note.setUser(user);
            noteMapper.insert(note);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }

    @ResponseBody
    @RequestMapping(value = ("/updateNote.do"), method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String updateNote(Note note, Word word, HttpServletRequest request) {
        WebApplicationContext context = WebApplicationContextUtils
                .getWebApplicationContext(request.getServletContext());
        NoteMapper noteMapper = (NoteMapper) context.getBean("noteMapper");
        try {
            note.setWord(word);
            User user = (User) request.getSession().getAttribute("user");
            note.setUser(user);
            noteMapper.updateNoteByWordId(word.getWordId(), user.getUserId(), note.getNoteText());
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }
}
