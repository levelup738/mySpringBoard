package com.myway.myboard.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.myway.myboard.model.BoardVO;

public interface BoardDAO {
	public Integer createPost(BoardVO barodVO);
	public List<BoardVO> setBoardList(Map<String, Object> map);
	public Integer cntTotal(Map<String, Object> map);
	public Map<String, Object> findPostBySeq(Integer seq);
	public Integer deletePost(Map<String, Object> map);
	public Integer updatePost(BoardVO barodVO);
	public Integer updateHit(Integer seq);
	public HashMap<String, Object> findMINMAXseq();
}
