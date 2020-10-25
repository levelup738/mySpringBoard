package com.myway.myboard.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myway.myboard.common.paging.PageMaker;
import com.myway.myboard.dao.BoardDAO;
import com.myway.myboard.dao.CommentDAO;
import com.myway.myboard.model.BoardVO;
import com.myway.myboard.model.CommentVO;
import com.myway.myboard.service.BoardService;
import com.myway.myboard.service.CommentService;

@Service("CommentServiceImpl")
public class CommentServiceImpl implements CommentService{
	@Autowired
	public CommentDAO commentDAO;

	@Override
	public Integer createComment(CommentVO commentVO) {
		return commentDAO.createComment(commentVO);
	}

	@Override
	public List<CommentVO> setCommentList(PageMaker pageMaker, Integer b_seq) {
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put("startCnt", pageMaker.getStartCnt());
		sqlMap.put("perPageCnt", pageMaker.getPerPageCnt());
		sqlMap.put("b_seq", b_seq);
		return commentDAO.setCommentList(sqlMap);
	}

	@Override
	public Integer cntTotal(Integer b_seq) {
		return commentDAO.cntTotal(b_seq);
	}

	@Override
	public List<CommentVO> findCommentsBySeq(Integer seq) {
		return commentDAO.findCommentsBySeq(seq);
	}

	@Override
	public Integer deleteComment(Integer seq, String writer) {
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put("seq", seq);
		sqlMap.put("writer", writer);
		return commentDAO.deleteComment(sqlMap);
	}

	@Override
	public Integer updateComment(CommentVO commentVO) {
		return commentDAO.updateComment(commentVO);
	}
}
