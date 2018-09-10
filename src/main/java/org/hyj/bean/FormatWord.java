package org.hyj.bean;

import java.util.List;

/**
 * Created by 胡宇靖 on 2018/6/28 0028.
 */
public class FormatWord {
    private Integer wordId;

    private String wordName;

    private String wordPron;

    private String wordSourceLink;

    private List<String> wordSimpleMeaning;

    private List<String> wordShit;

    private List<String> wordDoubleMeaning;

    private List<String> wordEnMeaning;

    private List<String> wordWords;

    private List<String> wordSyno;

    private String wordAnto;

    private String wordDeri;

    private List<String> wordSimilar;

    private List<String> wordDetail;
    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public String getWordPron() {
        return wordPron;
    }

    public void setWordPron(String wordPron) {
        this.wordPron = wordPron;
    }

    public String getWordSourceLink() {
        return wordSourceLink;
    }

    public void setWordSourceLink(String wordSourceLink) {
        this.wordSourceLink = wordSourceLink;
    }

    public List<String> getWordSimpleMeaning() {
        return wordSimpleMeaning;
    }

    public void setWordSimpleMeaning(List<String> wordSimpleMeaning) {
        this.wordSimpleMeaning = wordSimpleMeaning;
    }

    public List<String> getWordShit() {
        return wordShit;
    }

    public void setWordShit(List<String> wordShit) {
        this.wordShit = wordShit;
    }

    public List<String> getWordDoubleMeaning() {
        return wordDoubleMeaning;
    }

    public void setWordDoubleMeaning(List<String> wordDoubleMeaning) {
        this.wordDoubleMeaning = wordDoubleMeaning;
    }

    public List<String> getWordEnMeaning() {
        return wordEnMeaning;
    }

    public void setWordEnMeaning(List<String> wordEnMeaning) {
        this.wordEnMeaning = wordEnMeaning;
    }

    public List<String> getWordWords() {
        return wordWords;
    }

    public void setWordWords(List<String> wordWords) {
        this.wordWords = wordWords;
    }

    public List<String> getWordSyno() {
        return wordSyno;
    }

    public void setWordSyno(List<String> wordSyno) {
        this.wordSyno = wordSyno;
    }

    public String getWordAnto() {
        return wordAnto;
    }

    public List<String> getWordSimilar() {
        return wordSimilar;
    }

    public void setWordSimilar(List<String> wordSimilar) {
        this.wordSimilar = wordSimilar;
    }

    public void setWordAnto(String wordAnto) {
        this.wordAnto = wordAnto;
    }

    public String getWordDeri() {
        return wordDeri;
    }

    public void setWordDeri(String wordDeri) {
        this.wordDeri = wordDeri;
    }

    public List<String> getWordDetail() {
        return wordDetail;
    }

    public void setWordDetail(List<String> wordDetail) {
        this.wordDetail = wordDetail;
    }
}
