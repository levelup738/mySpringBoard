package com.myway.myboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.myway.myboard.common.paging.PageMaker;
import com.myway.myboard.model.BoardVO;
import org.springframework.stereotype.Repository;

public interface BoardService {
	public Integer createPost(BoardVO boardVO);
	public List<BoardVO> setBoardList(PageMaker pageMaker, String searchOpt, String keyword);
	public Integer cntTotal(String searchOpt, String keyword);
	public boolean findPostBySeq(Integer seq, Map<String, Object> map);
	public Integer deletePost(Integer seq, String writer);
	public Integer updatePost(BoardVO boardVO);
	public Integer updateHit(Integer seq);
	public HashMap<String, Object> findMINMAXseq();
}
