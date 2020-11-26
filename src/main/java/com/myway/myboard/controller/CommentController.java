package com.myway.myboard.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.xml.bind.SchemaOutputResolver;

import com.mysql.cj.xdevapi.JsonArray;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
	@ResponseBody
	public String comment_write(@ModelAttribute("commentVO") CommentVO commentVO) {
		//System.out.println(commentVO.toString());
		if(commentService.createComment(commentVO) > 0){
			return "success";
		}else{
			return "fail";
		}
	}
	@RequestMapping(value = "/comment/delete.do", method = RequestMethod.GET)
	@ResponseBody
	public String comment_delete(@RequestParam(value = "c_seq", required = true) Integer c_seq,
			@RequestParam(value = "writer", required = true) String writer) {
		if(commentService.deleteComment(c_seq, writer) > 0) {
			return "success";
		}else{
			return "fail";
		}
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
	public ResponseEntity<Object> comment_list(
			@RequestParam(value = "curPage", required = true, defaultValue = "1") Integer curPage,
			@RequestParam(value = "b_seq", required = true) Integer b_seq) {
		List<JSONObject> jsonList = new ArrayList<JSONObject>();

		PageMaker pageMaker = new PageMaker(curPage, 5);
		int totalPost = commentService.cntTotal(b_seq);
		PageInfo pageInfo = pageMaker.pageSetting(totalPost);

		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<CommentVO> commentVOs = commentService.setCommentList(pageMaker, b_seq);

		if(!commentVOs.isEmpty()) {
			for(int i = 0; i < commentVOs.size(); i++){
				JSONObject entity = new JSONObject();
				entity.put("c_seq", commentVOs.get(i).getSeq());
				entity.put("c_writer", commentVOs.get(i).getWriter());
				entity.put("c_content", commentVOs.get(i).getContent());
				// 날짜 변환
				DateTimeFormatter dateTimeFmt = DateTimeFormatter.ofPattern("yy.MM.dd hh:mm");
				String strDate = commentVOs.get(i).getRegdate().format(dateTimeFmt);
				//System.out.println(strDate);
				entity.put("c_regdate", strDate);
				jsonList.add(entity);
			}
		}
		JSONObject pageInfoJson = new JSONObject();
		pageInfoJson.put("totalPage", pageInfo.getTotalPage());
		pageInfoJson.put("totalPost", pageInfo.getTotalPost());
		pageInfoJson.put("curPage", pageInfo.getCurPage());
		pageInfoJson.put("startPage", pageInfo.getStartPage());
		pageInfoJson.put("endPage", pageInfo.getEndPage());
		jsonList.add(pageInfoJson);

		return new ResponseEntity<Object>(jsonList, new HttpHeaders(), HttpStatus.CREATED);
	}
}
