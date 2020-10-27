package com.myway.myboard.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
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
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Resource(name = "BoardServiceImpl")
	public BoardService boardService;
	
	@RequestMapping(value = "/board/list.do", method = RequestMethod.GET)
	public ModelAndView board_listPage(Model model, 
			@RequestParam(value = "curPage", required = true, defaultValue = "1") Integer curPage,
			@RequestParam(value = "searchOpt", required = false, defaultValue = "all") String searchOpt,
			@RequestParam(value = "keyword", required = false) String keyword) {
		// 리턴값을 ModelAndView로 설정하고 한번 사용해보았습니다.
		ModelAndView mav = new ModelAndView();
		// 뷰 설정
		mav.setViewName("board/board_list");
		// pageMaker클래스에서 시작페이지와 한페이지당 몇개를 표시할껀지 설정합니다.
		PageMaker pageMaker = new PageMaker(curPage, 10);
		// 전체 페이시 개수를 구합니다.
		int totalPost = boardService.cntTotal(searchOpt, keyword);
		// pageinfo객체는 totalPage, totalPost, curPage, startPage, endPage
		PageInfo pageInfo = pageMaker.pageSetting(totalPost);
		// 정보를 이용해서 페이지들을 필터링해서 불러옵니다.
		List<BoardVO> voList = boardService.setBoardList(pageMaker, searchOpt, keyword);

		Map<String, Object> map = new HashMap<String, Object>();
		if(!voList.isEmpty()) {
			//logger.debug(voList.size()+"");
			map.put("voList", voList);
		}
		map.put("boardVO", new BoardVO());
		map.put("pageInfo", pageInfo);
		map.put("searchOpt", searchOpt);
		map.put("keyword", keyword);
		mav.addAllObjects(map);
		return mav;
	}
	@RequestMapping(value = "/board/view.do", method = RequestMethod.GET)
	public ModelAndView board_viewPage(Model model,
			@RequestParam(value = "b_seq", required = true) Integer b_seq,
			@ModelAttribute("sessionUser") UserVO userVO) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("commentVO", new CommentVO());
		if(!boardService.findPostBySeq(b_seq, map)) {
			logger.debug("viewpage 로딩 실패");
		}
		HashMap<String, Object> map_minmax = boardService.findMINMAXseq();
		map.put("pageMin", map_minmax.get("min"));
		map.put("pageMax", map_minmax.get("max"));
		
		return new ModelAndView("board/board_view", map);
	}

	@RequestMapping(value = "/board/write.do", method = RequestMethod.GET)
	public ModelAndView board_writePage() {
		return new ModelAndView("board/board_write", "boardVO", new BoardVO());
	}
	@RequestMapping(value = "/board/move_modify.do", method = RequestMethod.GET)
	public ModelAndView board_moveModify(Model model, @RequestParam(value = "seq", required = true) Integer seq) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!boardService.findPostBySeq(seq, map)) {
			logger.debug("수정페이지 이동 실패");	
		}
		return new ModelAndView("board/board_write", map);
	}
	@RequestMapping(value = "/board/modify.do", method = RequestMethod.POST)
	public String board_modify(@ModelAttribute("boardVO") BoardVO boardVO) {
		Integer state = boardService.updatePost(boardVO);
		if(state > 0) {
			logger.debug("수정 성공");
		}
		return "redirect:/board/list.do";
	}
	@RequestMapping(value = "/board/delete.do", method = RequestMethod.GET)
	public String board_delete(@RequestParam(value = "b_seq", required = true) Integer seq,
							   @RequestParam(value = "b_writer", required = true) String writer) {
		Integer state = boardService.deletePost(seq, writer);
		if(state > 0) {
			logger.debug("게시글 삭제 성공.");
		}
		return "redirect:/board/list.do";
	}
	@RequestMapping(value = "/board/create.do", method = RequestMethod.POST)
	public String createPost(Model model, @ModelAttribute("boardVO") BoardVO boardVO) {
		//System.out.println(boardVO.toString());
		boardService.createPost(boardVO);
		return "redirect:/board/list.do";
	}
}
