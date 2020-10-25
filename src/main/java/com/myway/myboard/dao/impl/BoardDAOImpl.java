package com.myway.myboard.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myway.myboard.dao.BoardDAO;
import com.myway.myboard.model.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{
	@Autowired
	public SqlSession sqlSession;
	
	public static final String NAME_SPACE = "com.myway.myboard.dao.BoardDAO";

	@Override
	public Integer createPost(BoardVO boardVO) {
		return sqlSession.insert(NAME_SPACE + ".createPost", boardVO);
	}

	@Override
	public List<BoardVO> setBoardList(Map<String, Object> map) {
		return sqlSession.selectList(NAME_SPACE + ".setBoardList", map);
	}
	@Override
	public Integer cntTotal(Map<String, Object> map) {
		return sqlSession.selectOne(NAME_SPACE + ".cntTotal", map);
	}

	@Override
	public List<BoardVO> findPostBySeq(Integer seq) {
		return sqlSession.selectList(NAME_SPACE + ".findPostBySeq", seq);
	}

	@Override
	public Integer deletePost(Map<String, Object> map) {
		return sqlSession.delete(NAME_SPACE + ".deletePost", map);
	}

	@Override
	public Integer updatePost(BoardVO boardVO) {
		return sqlSession.update(NAME_SPACE + ".updatePost", boardVO);
	}

	@Override
	public Integer updateHit(Integer seq) {
		return sqlSession.update(NAME_SPACE + ".updateHit", seq);
	}

	@Override
	public HashMap<String, Object> findMINMAXseq() {
		return sqlSession.selectOne(NAME_SPACE + ".findMINMAXseq");
	}
}
