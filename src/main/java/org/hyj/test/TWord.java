package org.hyj.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.hyj.bean.FormatWord;
import org.hyj.bean.Word;
import org.hyj.bean.WordWithBLOBs;
import org.hyj.dao.WordMapper;
import org.hyj.utils.WordUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by 胡宇靖 on 2018/6/27 0027.
 */
public class TWord {
    private ClassPathXmlApplicationContext context;
    private WordMapper wordMapper;

    @Before
    public void init() {
        System.out.println("init");
        context = new ClassPathXmlApplicationContext(
                "spring_config.xml");
        wordMapper = (WordMapper) context.getBean("wordMapper");
    }

    @Test
    public void testInsert() {
        WordWithBLOBs wordWithBLOBs = new WordWithBLOBs();
        wordWithBLOBs.setWordName("nihao");
        wordMapper.insert(wordWithBLOBs);
    }


    @Test
    public void testUpdate() {
        Word word = new Word();
        word.setWordId(3402);
        word.setWordName("hello");
        wordMapper.updateByPrimaryKey(word);
    }

    @Test
    public void testSelectByPrimaryKey() {
        WordWithBLOBs wordWithBLOBs = wordMapper.selectByPrimaryKey(3388);
        System.out.println(JSONObject.toJSON(wordWithBLOBs));
    }

    @Test
    public void testDeleteWord() {
        wordMapper.deleteByPrimaryKey(3403);
    }

    @Test
    public void selectLikeWordByName() {
        List<WordWithBLOBs> application = wordMapper.selectLikeWordByName(1, "applica");
        for (WordWithBLOBs wordWithBLOBs : application) {
            System.out.println(wordWithBLOBs);
        }
    }

    @Test
    public void selectLikeWordsByName() {
        List<WordWithBLOBs> icatio = wordMapper.selectLikeWordsByName(null, "icatio");
        System.out.println(JSONObject.toJSON(icatio));
    }

    @Test
    public void selectWordByName() {
        WordWithBLOBs dbWord = wordMapper.selectWordByName("application");
        FormatWord word = new FormatWord();
        word.setWordId(dbWord.getWordId());
        word.setWordName(dbWord.getWordName());
        word.setWordPron(dbWord.getWordPron());
        word.setWordSourceLink(dbWord.getWordSourceLink());
        word.setWordSimpleMeaning(JSON.parseArray(dbWord.getWordSimpleMeaning(), String.class));
        word.setWordAnto(dbWord.getWordAnto());
        word.setWordDeri(dbWord.getWordDeri());
        word.setWordSyno(JSON.parseArray(dbWord.getWordSyno(), String.class));
        word.setWordSimilar(JSON.parseArray(dbWord.getWordSimilar(), String.class));
        word.setWordShit(WordUtil.handleShit(dbWord.getWordShit()));
        word.setWordDoubleMeaning(WordUtil.changeToList(dbWord.getWordDoubleMeaning()));
        word.setWordEnMeaning(WordUtil.changeToList(dbWord.getWordEnMeaning()));
        word.setWordWords(WordUtil.changeToList(dbWord.getWordWords()));
        word.setWordDetail(WordUtil.changeToList(dbWord.getWordDetail()));
        System.out.println(JSONObject.toJSONString(word));
    }

    @Test
    public void handle2List() {
        String s = " n. (名词) \n" +
                " * [C][U]争论,争吵,辩论 disagreement; quarrel, discussion based on reasoning \n" +
                " * [U]说理,论证 the use of reason to decide sth or persuade sb \n" +
                " * [C]论据,论点,理由 reason or reasons put forward ";
        List<String> strings = WordUtil.changeToList(s);
        for (String string : strings) {
            System.out.println(string);
        }
    }

    @Test
    public void selectWordKey() {
        List<Word> app = wordMapper.selectWordKey( "app");
        for (Word wordWithBLOBs : app) {
            System.out.println(JSONObject.toJSONString(wordWithBLOBs));
        }
    }

}
