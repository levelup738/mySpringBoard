package com.myway.myboard.dao;

import java.util.List;
import java.util.Map;

import com.myway.myboard.model.BoardVO;
import com.myway.myboard.model.CommentVO;

public interface CommentDAO {
	public Integer createComment(CommentVO commentVO);
	public List<CommentVO> setCommentList(Map<String, Object> map);
	public Integer cntTotal(Integer b_seq);
	public List<CommentVO> findCommentsBySeq(Integer seq);
	public Integer deleteComment(Map<String, Object> map);
	public Integer updateComment(CommentVO commentVO);
}
