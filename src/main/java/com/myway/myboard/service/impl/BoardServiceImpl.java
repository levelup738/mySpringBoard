package com.myway.myboard.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.myway.myboard.common.paging.PageMaker;
import com.myway.myboard.dao.BoardDAO;
import com.myway.myboard.model.BoardVO;
import com.myway.myboard.service.BoardService;

@Service("BoardServiceImpl")
public class BoardServiceImpl implements BoardService{
	@Autowired
	public BoardDAO boardDAO;
	
	@Override
	public Integer createPost(BoardVO boardVO) {
		return boardDAO.createPost(boardVO);
	}

	@Override
	public List<BoardVO> setBoardList(PageMaker pageMaker, String searchOpt, String keyword) {
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put("startCnt", pageMaker.getStartCnt());
		sqlMap.put("perPageCnt", pageMaker.getPerPageCnt());
		sqlMap.put("searchOpt", searchOpt);
		sqlMap.put("keyword", keyword);
		//System.out.println("findPosts : " + searchOpt +", "+keyword);
		return boardDAO.setBoardList(sqlMap);
	}
	@Override
	public Integer cntTotal(String searchOpt, String keyword) {
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put("searchOpt", searchOpt);
		sqlMap.put("keyword", keyword);
		//System.out.println("cntTotal : " + searchOpt +", "+keyword);
		return boardDAO.cntTotal(sqlMap);
	}

	@Override
	public boolean findPostBySeq(Integer seq, Map<String, Object> map) {
		Map<String, Object> boardViewMap = boardDAO.findPostBySeq(seq);
		BoardVO vo = new BoardVO();
		vo.setSeq((Integer) boardViewMap.get("b_seq"));
		vo.setTitle((String) boardViewMap.get("b_title"));
		vo.setWriter((String) boardViewMap.get("b_writer"));
		vo.setHit((Integer) boardViewMap.get("b_hit"));
		vo.setRegdate((Date) boardViewMap.get("b_regdate"));
		vo.setContent((String) boardViewMap.get("b_content"));

		map.put("boardVO", vo);
		map.put("prevTitle", boardViewMap.get("PREV_TITLE"));
		map.put("nextTitle", boardViewMap.get("NEXT_TITLE"));
		map.put("prevSeq", boardViewMap.get("PREV_SEQ"));
		map.put("nextSeq", boardViewMap.get("NEXT_SEQ"));
		return true;
	}

	@Override
	public Integer deletePost(Integer seq, String writer) {
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put("seq", seq);
		sqlMap.put("writer", writer);
		return boardDAO.deletePost(sqlMap);
	}

	@Override
	public Integer updatePost(BoardVO boardVO) {
		return boardDAO.updatePost(boardVO);
	}

	@Override
	public Integer updateHit(Integer seq) {
		return boardDAO.updateHit(seq);
	}

	@Override
	public HashMap<String, Object> findMINMAXseq() {
		return boardDAO.findMINMAXseq();
	}
	
}
