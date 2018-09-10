package org.hyj.bean;

public class WordWithBLOBs extends Word {
    private String wordSimpleMeaning;

    private String wordShit;

    private String wordDoubleMeaning;

    private String wordEnMeaning;

    private String wordWords;

    private String wordSyno;

    private String wordAnto;

    private String wordDeri;

    private String wordSimilar;

    private String wordDetail;

    public String getWordSimpleMeaning() {
        return wordSimpleMeaning;
    }

    public void setWordSimpleMeaning(String wordSimpleMeaning) {
        this.wordSimpleMeaning = wordSimpleMeaning == null ? null : wordSimpleMeaning.trim();
    }

    public String getWordShit() {
        return wordShit;
    }

    public void setWordShit(String wordShit) {
        this.wordShit = wordShit == null ? null : wordShit.trim();
    }

    public String getWordDoubleMeaning() {
        return wordDoubleMeaning;
    }

    public void setWordDoubleMeaning(String wordDoubleMeaning) {
        this.wordDoubleMeaning = wordDoubleMeaning == null ? null : wordDoubleMeaning.trim();
    }

    public String getWordEnMeaning() {
        return wordEnMeaning;
    }

    public void setWordEnMeaning(String wordEnMeaning) {
        this.wordEnMeaning = wordEnMeaning == null ? null : wordEnMeaning.trim();
    }

    public String getWordWords() {
        return wordWords;
    }

    public void setWordWords(String wordWords) {
        this.wordWords = wordWords == null ? null : wordWords.trim();
    }

    public String getWordSyno() {
        return wordSyno;
    }

    public void setWordSyno(String wordSyno) {
        this.wordSyno = wordSyno == null ? null : wordSyno.trim();
    }

    public String getWordAnto() {
        return wordAnto;
    }

    public void setWordAnto(String wordAnto) {
        this.wordAnto = wordAnto == null ? null : wordAnto.trim();
    }

    public String getWordDeri() {
        return wordDeri;
    }

    public void setWordDeri(String wordDeri) {
        this.wordDeri = wordDeri == null ? null : wordDeri.trim();
    }

    public String getWordSimilar() {
        return wordSimilar;
    }

    public void setWordSimilar(String wordSimilar) {
        this.wordSimilar = wordSimilar == null ? null : wordSimilar.trim();
    }

    public String getWordDetail() {
        return wordDetail;
    }

    public void setWordDetail(String wordDetail) {
        this.wordDetail = wordDetail == null ? null : wordDetail.trim();
    }
}