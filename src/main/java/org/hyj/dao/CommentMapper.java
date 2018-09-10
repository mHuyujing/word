package org.hyj.dao;

import org.apache.ibatis.annotations.Param;
import org.hyj.bean.Comment;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);

    //    查询单词的所有评论 [时间逆序,分页]
    List<Comment> selectCommentByWordId(@Param("page") Integer page, @Param("wordId") Integer wordId);

    //    删除用户的一条评论
    int deleteCommentByCommentIdAndUserId(@Param("commentId") Integer commentId, @Param("userId") Integer userId);

    //    查询用户的所有评论
    List<Comment> selectCommentByUserId(@Param("page") Integer page, @Param("userId") Integer UserId);

}