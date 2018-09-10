package org.hyj.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 胡宇靖 on 2018/6/28 0028.
 */
public class WordUtil {
    public static List<String> handleShit(String shit) {
        if (shit == null) return null;
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]*: [a-zA-Z]*");
        Matcher matcher = pattern.matcher(shit);
        List<String> strings = new ArrayList<>();
        while (matcher.find()) {
            strings.add(matcher.group());
        }
        return strings;
    }

    public static List<String> changeToList(String doubleMeaning) {
        if (doubleMeaning == null) return null;
        return Arrays.asList(doubleMeaning.split("\n"));
    }

}
