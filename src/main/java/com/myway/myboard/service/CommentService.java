package com.myway.myboard.service;

import java.util.List;
import java.util.Map;

import com.myway.myboard.common.paging.PageMaker;
import com.myway.myboard.model.BoardVO;
import com.myway.myboard.model.CommentVO;

public interface CommentService {
	public Integer createComment(CommentVO commentVO);
	public List<CommentVO> setCommentList(PageMaker pageMaker, Integer b_seq);
	public Integer cntTotal(Integer b_seq);
	public List<CommentVO> findCommentsBySeq(Integer seq);
	public Integer deleteComment(Integer seq, String writer);
	public Integer updateComment(CommentVO commentVO);
}
