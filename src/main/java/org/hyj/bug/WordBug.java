package org.hyj.bug;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.hyj.bean.Word;
import org.hyj.bean.WordWithBLOBs;
import org.hyj.dao.WordMapper;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import javax.json.JsonObject;
import java.util.*;


public class WordBug implements PageProcessor {

    static int index = 3393;
    static List<String> sList = null;
    static String url = "http://dict.cn/";

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me()
            .setRetryTimes(10)
            .setDomain("http://dict.cn")
            .setUserAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36")
            .setSleepTime(100).setTimeOut(100000);
    private static ClassPathXmlApplicationContext context;
    private static WordMapper wordMapper;


    public void process(Page page) {
        Html html = page.getHtml();
        //-------------------------
        WordWithBLOBs word = new WordWithBLOBs();

        String pron = html.xpath("//div[@class='phonetic']/span[2]/bdo//text()").toString();
        String shit = html.xpath("/html/body/div[@id='content']/div[@class='main']/div[@class='word']/div[@class='shape']//allText()").toString();
        String meaningXpath = "/html/body/div[@id='content']/div[@class='main']/div[@class='word']/div[@class='basic clearfix']/ul[@class='dict-basic-ul']/li/allText()";
        List<String> all = html.xpath(meaningXpath).all();
        all.remove("");
        String URL = page.getUrl().get();
        String detail = html.xpath("//div[@class='layout ess']//html()").get();
        List<String> wordDoubleMeaning = html.xpath("//div[@class='layout dual']//html()").all();
        List<String> wordEnMeaning = html.xpath("//div[@class='layout en']//html()").all();
        // 近义词
        List<String> syno = html.xpath("//div[@class='layout nfo']/ul[1]/li/a//allText()").all();
        // 反义词
        List<String> anto = html.xpath("//div[@class='layout nfo']/ul[2]/li/a//allText()").all();
        // 派生词
        String deri = html.xpath("//div[@class='layout derive']//html()").get();
        // 形似词
        List<String> similar = html.xpath("//div[@class='layout nwd']/a//text()").all();
        // 例句
        List<String> words = html.xpath("//div[@class='layout sort']/ol/li//html()").all();

        if (all.size() > 0) word.setWordSimpleMeaning(JSON.toJSONString(all));
        if (wordDoubleMeaning.size() > 0) word.setWordDoubleMeaning(JSONObject.toJSONString(wordDoubleMeaning));
        if (wordEnMeaning.size() > 0) word.setWordEnMeaning(JSONObject.toJSONString(wordEnMeaning));
        if (syno.size() > 0) word.setWordSyno(JSONObject.toJSONString(syno));
        if (anto.size() > 0) word.setWordAnto(JSONObject.toJSONString(anto));
        if (similar.size() > 0) word.setWordSimilar(JSONObject.toJSONString(similar));
        if (words.size() > 0) word.setWordWords(JSONObject.toJSONString(words));

        word.setWordPron(pron);
        word.setWordSourceLink(URL);
        word.setWordShit(shit);
        word.setWordName(sList.get(index));
        word.setWordDetail(detail);
        word.setWordDeri(deri);
        // System.out.println("pron:" + pron);
        // System.out.println("URL:" + URL);
        // System.out.println("meaningsJson:" + meaningsJson);
        // System.out.println("wordDoubleMeaning:" + wordDoubleMeaning);
        // System.out.println("wordEnMeaning:" + wordEnMeaning);
        // System.out.println("shit:" + shit);
        // System.out.println("syno:" + syno);
        // System.out.println("anto:" + anto);
        // System.out.println("deri:" + deri);
        // System.out.println("similar:" + similar);
        // System.out.println("words" + similar);
        try {
            wordMapper.insert(word);
        } catch (Exception e) {
            System.err.println("异常中断位置:" + index);
            e.printStackTrace();
        }
        System.out.println("--WordName--:" + word.getWordName());
        System.out.println("--Get index now--:" + index);
        index++;
        if (index < sList.size()) {
            page.addTargetRequest(new Request(url + sList.get(index)));
        }
    }

    public Site getSite() {
        return site;
    }

    public static void startBug(List<String> list) {
        context = new ClassPathXmlApplicationContext("spring_config.xml");
        wordMapper = (WordMapper) context.getBean("wordMapper");
        sList = list;
        Spider.create(new WordBug())
                .addUrl(url + sList.get(index))
                .thread(10)
                .run();
    }

    public void init() {
        context = new ClassPathXmlApplicationContext("spring_config.xml");
        wordMapper = (WordMapper) context.getBean("wordMapper");
        ArrayList<String> strings = new ArrayList<>();
        String[] a = {"absent"};
        strings.addAll(Arrays.asList(a));
        startBug(strings);
    }

    public static void startBug(String word) {
        sList = new ArrayList<String>();
        sList.add(word);
        Spider.create(new WordBug())
                .addUrl(url + sList.get(index))
                .thread(8)
                .run();
    }
}