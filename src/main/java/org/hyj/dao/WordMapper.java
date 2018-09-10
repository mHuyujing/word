package org.hyj.dao;

import org.apache.ibatis.annotations.Param;
import org.hyj.bean.Word;
import org.hyj.bean.WordWithBLOBs;

import java.util.List;

public interface WordMapper {
    int deleteByPrimaryKey(Integer wordId);

    int insert(WordWithBLOBs record);

    int insertSelective(WordWithBLOBs record);

    WordWithBLOBs selectByPrimaryKey(Integer wordId);

    int updateByPrimaryKeySelective(WordWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(WordWithBLOBs record);

    int updateByPrimaryKey(Word record);
    List<Word> selectWordKey(String wordName);

    WordWithBLOBs selectWordByName(String wordName);

    List<WordWithBLOBs> selectLikeWordByName(@Param("page") Integer Page, @Param("wordName") String wordName);

    List<WordWithBLOBs> selectLikeWordsByName(@Param("page")Integer Page,@Param("wordsName")String wordsName);
    List<WordWithBLOBs>  selectErrorWord();
}