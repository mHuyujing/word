package org.hyj.dao;

import org.apache.ibatis.annotations.Param;
import org.hyj.bean.History;

import java.util.List;

public interface HistoryMapper {
    int deleteByPrimaryKey(Integer historyId);

    int insert(History record);

    int insertSelective(History record);

    History selectByPrimaryKey(Integer historyId);

    int updateByPrimaryKeySelective(History record);

    int updateByPrimaryKey(History record);

    List<History> selectHistoryByUserId(@Param("page") Integer page, @Param("userId") Integer userId);

    History selectHistoryByUserIdAndWordId(@Param("userId") Integer userId, @Param("wordId") Integer wordId);

    int deleteHistoryByUserId(Integer userId);

}