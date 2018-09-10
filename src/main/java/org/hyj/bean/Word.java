package org.hyj.bean;

public class Word {
    private Integer wordId;

    private String wordName;

    private String wordPron;

    private String wordSourceLink;

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
        this.wordName = wordName == null ? null : wordName.trim();
    }

    public String getWordPron() {
        return wordPron;
    }

    public void setWordPron(String wordPron) {
        this.wordPron = wordPron == null ? null : wordPron.trim();
    }

    public String getWordSourceLink() {
        return wordSourceLink;
    }

    public void setWordSourceLink(String wordSourceLink) {
        this.wordSourceLink = wordSourceLink == null ? null : wordSourceLink.trim();
    }
}