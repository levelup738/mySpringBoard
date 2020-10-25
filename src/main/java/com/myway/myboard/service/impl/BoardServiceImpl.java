package com.myway.myboard.service.impl;

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
		List<BoardVO> vos = boardDAO.findPostBySeq(seq);
		if(vos.isEmpty()) {return false;}
		for (BoardVO boardVO : vos) {
			if (boardVO.getSeq().equals(seq)) {
				map.put("boardVO", boardVO);
			} else if (boardVO.getSeq().equals(seq - 1)) {
				map.put("prevTitle", boardVO.getTitle());
			} else if (boardVO.getSeq().equals(seq + 1)) {
				map.put("nextTitle", boardVO.getTitle());
			} else {
				return false;
			}
		}
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
