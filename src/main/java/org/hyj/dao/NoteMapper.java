package org.hyj.dao;

import org.apache.ibatis.annotations.Param;
import org.hyj.bean.Note;

public interface NoteMapper {
    int deleteByPrimaryKey(Integer noteId);

    int insert(Note record);

    int insertSelective(Note record);

    Note selectByPrimaryKey(Integer noteId);

    int updateByPrimaryKeySelective(Note record);

    int updateByPrimaryKeyWithBLOBs(Note record);

    int updateByPrimaryKey(Note record);

    Note selectNoteByUserIdAndWordId(@Param("userId") Integer userId,@Param("wordId") Integer wordId);

    int updateNoteByWordId(@Param("wordId")Integer wordId,@Param("userId")Integer userId,@Param("noteText")String noteText);

}