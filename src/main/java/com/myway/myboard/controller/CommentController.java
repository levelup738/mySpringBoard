package com.myway.myboard.controller;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.mysql.cj.xdevapi.JsonArray;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.myway.myboard.common.paging.PageInfo;
import com.myway.myboard.common.paging.PageMaker;
import com.myway.myboard.dao.BoardDAO;
import com.myway.myboard.model.BoardVO;
import com.myway.myboard.model.CommentVO;
import com.myway.myboard.model.UserVO;
import com.myway.myboard.service.BoardService;
import com.myway.myboard.service.CommentService;

@Controller
@SessionAttributes("sessionUser")
public class CommentController {
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@Resource(name = "CommentServiceImpl")
	public CommentService commentService;
	
	@RequestMapping(value = "/comment/write.do", method = RequestMethod.POST)
	public String comment_write(@ModelAttribute("commentVO") CommentVO commentVO) {
		commentService.createComment(commentVO);
		return "redirect:/board/view.do?b_seq="+commentVO.getBoardseq();
	}
	@RequestMapping(value = "/comment/delete.do", method = RequestMethod.GET)
	public String comment_delete(@RequestParam(value = "c_seq", required = true) Integer c_seq,
			@RequestParam(value = "b_seq", required = true) Integer b_seq,
			@RequestParam(value = "writer", required = true) String writer) {
		Integer state = commentService.deleteComment(c_seq, writer);
		if(state > 0) {
			logger.debug("댓글 삭제 성공.");
		}
		return "redirect:/board/view.do?b_seq="+b_seq;
	}
	@RequestMapping(value = "/comment/update.do", method = RequestMethod.POST)
	public String comment_delete(@ModelAttribute("commentVO") CommentVO commentVO) {
		Integer state = commentService.updateComment(commentVO);
		if(state > 0) {
			logger.debug("댓글 수정 성공.");
		}
		return "redirect:/board/view.do?b_seq="+commentVO.getBoardseq();
	}
	@RequestMapping(value = "/comment/list.do", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	@ResponseBody
	public ResponseEntity comment_list(
			@RequestParam(value = "curPage", required = true, defaultValue = "1") Integer curPage,
			@RequestParam(value = "b_seq", required = true) Integer b_seq) {
		ArrayList<HashMap> hmlist = new ArrayList<HashMap>();

		PageMaker pageMaker = new PageMaker(curPage, 5);
		int totalPost = commentService.cntTotal(b_seq);
		PageInfo pageInfo = pageMaker.pageSetting(totalPost);

		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<CommentVO> commentVOs = commentService.setCommentList(pageMaker, b_seq);
		if(!commentVOs.isEmpty()) {
			for(int i = 0; i < commentVOs.size(); i++){
				HashMap<String, Object> voMap = new HashMap<String, Object>();
				voMap.put("c_seq", commentVOs.get(i).getSeq());
				voMap.put("c_writer", commentVOs.get(i).getWriter());
				voMap.put("c_content", commentVOs.get(i).getContent());
				voMap.put("c_regdate", commentVOs.get(i).getRegdate());
				hmlist.add(voMap);
			}
		}
		//dataMap.put("voList", commentVOs);
		//dataMap.put("pageInfo", pageInfo);

		// data를 json으로
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(hmlist);

		return new ResponseEntity(jsonArray.toString(), new HttpHeaders(), HttpStatus.CREATED);
	}
}
