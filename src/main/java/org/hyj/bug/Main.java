package org.hyj.bug;

import org.hyj.dao.WordMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;


public class Main {

    private WordMapper wordMapper;

    @Before
    public void begin() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring_config.xml");
        wordMapper = (WordMapper) context.getBean("wordMapper");

    }

    @Test
    public void startMain() {
        try {
            FileInputStream fileInputStream = new FileInputStream("/home/yujing/word.txt");
            byte[] bytes = new byte[100];
            int length = 0;
            StringBuffer stringBuffer = new StringBuffer();
            while ((length = fileInputStream.read(bytes)) != -1) {
                stringBuffer.append(new String(bytes, 0, length));
            }
            String[] split = stringBuffer.toString().split("\n");
            System.out.println("包含了重复的单词，总数："+split.length);
            HashSet<String> strings = new HashSet<>();
            for (int i = 0; i < split.length; i++) {
                // 将数据添加到HashSet
                boolean add = strings.add(split[i]);
                if (!add) {
                    System.out.println("重复单词"+split[i] + "：" + i);
                }
            }
            System.out.println("处理后单词数："+strings.size());

            WordBug.startBug(new ArrayList<>(strings));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
