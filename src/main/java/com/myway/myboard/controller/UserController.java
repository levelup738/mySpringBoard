package com.myway.myboard.controller;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Struct;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;
import javax.validation.Valid;
import javax.xml.ws.Response;

import com.myway.myboard.common.MailUtils;
import com.myway.myboard.service.impl.UserServiceimpl;
import org.apache.ibatis.javassist.bytecode.stackmap.BasicBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.myway.myboard.common.JoinFormOption;
import com.myway.myboard.common.MessageUtils;
import com.myway.myboard.dao.BoardDAO;
import com.myway.myboard.dao.UserDAO;
import com.myway.myboard.model.BoardVO;
import com.myway.myboard.model.CommentVO;
import com.myway.myboard.model.UserVO;
import com.myway.myboard.service.UserService;

@Controller
@SessionAttributes("sessionUser")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name = "UserServiceimpl")
	private UserServiceimpl userService;
	
	@ModelAttribute
	public UserVO sessionUser() {
		return new UserVO();
	}
	
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		return new ModelAndView("login/login", "userVO", new UserVO());
	}
	
	@RequestMapping(value = "/join.do", method = RequestMethod.GET)
	public ModelAndView joinUserPage() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userVO", new UserVO());
		map.put("joinOpt", new JoinFormOption());
		return new ModelAndView("join/join", map);
	}
	@RequestMapping(value = "/idDoubleCheck.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	@ResponseBody
	public String idDoubleCheck(Model model, @RequestParam(value = "id", required = true) String id) {
		Integer result = userService.idCheck(id);
		StringBuilder stringBuilder = new StringBuilder();
		if(result == 0) {
			stringBuilder.append("yes-").append(MessageUtils.getMessage("msg.doubleCheck.success"));
			//System.out.println("successMsg");
		}else{
			stringBuilder.append("no-").append(MessageUtils.getMessage("msg.doubleCheck.fail"));
		}
		return stringBuilder.toString();
	}
	
	@RequestMapping(value = "/mypage.do", method = RequestMethod.GET)
	public ModelAndView mypagePage() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userVO", new UserVO());
		map.put("joinOpt", new JoinFormOption());
		return new ModelAndView("mypage/mypage", map);
	}
	@RequestMapping(value = "/userUpdate.do", method = RequestMethod.POST)
	public String userUpdatePage(Model model, @ModelAttribute("userVO") @Valid UserVO userVO, BindingResult bResult) {
		if(bResult.hasErrors()) {
			model.addAttribute("joinOpt", new JoinFormOption());
			return "mypage/mypage";
		}
		Integer state = userService.updateUser(userVO);
		if(state > 0) {
			model.addAttribute("sessionUser", userVO);
		}
		return "redirect:/home.do";
	}
	
	@RequestMapping(value="/add/user.do", method = RequestMethod.POST)
	public String addUser(Model model, @ModelAttribute("userVO") @Valid UserVO userVO,
						  BindingResult bResult) throws Exception {
		if(bResult.hasErrors()) {
			model.addAttribute("joinOpt", new JoinFormOption());
			return "join/join";
		}
		userService.createUser(userVO);
		return "redirect:/home.do";
	}
	@RequestMapping(value="/loginLogic.do", method = RequestMethod.POST)
	public String loginCheck(Model model, @ModelAttribute("userVO") @Valid UserVO userVO, BindingResult bResult) {
		UserVO vo = userService.loginCheck(userVO);
		if(vo == null) {
			// 유효성 검사는 만족하지만 유저정보에 없는경우(아이디, 비밀번호가 틀렸다)
			if(!userVO.getId().equals("") && !userVO.getPw().equals("")) {
				model.addAttribute("errorMsg", MessageUtils.getMessage("error.login.notMatchedInfo"));
			}
			return "login/login";
		}
		// 이메일 인증을 완료하지 않았다면
		if(vo.getAuthstatus() != 1){
			return "redirect:/home.do?emailcheck=no";
		}
		// 로그인을 성공하면 세션에 로그인유저를 추가해준다
		model.addAttribute("sessionUser", vo);
		return "redirect:/board/list.do";
	}
	@RequestMapping(value="/logoutLogic.do", method = RequestMethod.GET)
	public String logoutLogic(SessionStatus session) {
		userService.logout(session);
		return "redirect:/home.do";
	}
	@RequestMapping(value="/joinConfirm.do", method = RequestMethod.GET)
	public String joinConfirm(Model model,
							  @RequestParam(value = "userid", required = true) String userid,
							  @RequestParam(value = "email", required = true) String email,
							  @RequestParam(value = "authkey", required = true) String authkey) throws UnsupportedEncodingException {
		logger.debug(userid + ", " + email + ", " + authkey);
		StringBuilder stringBuilder = new StringBuilder();
		if(userService.updateAuthStatus(userid, authkey)){
			stringBuilder.append("?joinConfirmMsg=").append(URLEncoder.encode(MessageUtils.getMessage("msg.joinConfirm"), "UTF-8"));
		}
		return "redirect:/home.do"+stringBuilder.toString();
	}
	@RequestMapping(value="/delete/user.do", method = RequestMethod.GET)
	public String deleteUser(@RequestParam(value = "seq", required = true) Integer seq,
							 @RequestParam(value = "id", required = true) String id
	) throws UnsupportedEncodingException {
		StringBuilder stringBuilder = new StringBuilder();
		if(userService.deleteUser(seq, id)){
			stringBuilder.append("?unsubscribeMsg=").append(URLEncoder.encode(MessageUtils.getMessage("msg.unsubscribe"), "UTF-8"));
		}
		return "redirect:/home.do"+stringBuilder.toString();
	}
}
