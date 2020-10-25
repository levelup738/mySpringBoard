package com.myway.myboard.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myway.myboard.dao.BoardDAO;
import com.myway.myboard.dao.CommentDAO;
import com.myway.myboard.model.BoardVO;
import com.myway.myboard.model.CommentVO;

@Repository
public class CommentDAOImpl implements CommentDAO{
	@Autowired
	public SqlSession sqlSession;
	
	public static final String NAME_SPACE = "com.myway.myboard.dao.CommentDAO";

	@Override
	public Integer createComment(CommentVO commentVO) {
		return sqlSession.insert(NAME_SPACE + ".createComment", commentVO);
	}

	@Override
	public List<CommentVO> setCommentList(Map<String, Object> map) {
		return sqlSession.selectList(NAME_SPACE + ".setCommentList", map);
	}

	@Override
	public Integer cntTotal(Integer b_seq) {
		return sqlSession.selectOne(NAME_SPACE + ".cntTotal", b_seq);
	}

	@Override
	public List<CommentVO> findCommentsBySeq(Integer seq) {
		return sqlSession.selectList(NAME_SPACE + ".findCommentsBySeq", seq);
	}

	@Override
	public Integer deleteComment(Map<String, Object> map) {
		return sqlSession.delete(NAME_SPACE + ".deleteComment", map);
	}

	@Override
	public Integer updateComment(CommentVO commentVO) {
		return sqlSession.update(NAME_SPACE + ".updateComment", commentVO);
	}


}
