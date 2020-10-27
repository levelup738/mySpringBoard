package com.myway.myboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	public String comment_list(
			@RequestParam(value = "curPage", required = true, defaultValue = "1") Integer curPage,
			@RequestParam(value = "b_seq", required = true) Integer b_seq) {
		PageMaker pageMaker = new PageMaker(curPage, 5);
		int totalPost = commentService.cntTotal(b_seq);
		PageInfo pageInfo = pageMaker.pageSetting(totalPost);

		Map<String, Object> map = new HashMap<String, Object>();
		List<CommentVO> commentVOs = commentService.setCommentList(pageMaker, b_seq);
		if(!commentVOs.isEmpty()) {
			map.put("commentVOs", commentVOs);
		}
		map.put("pageInfo", pageInfo);

		return "";
	}
}
