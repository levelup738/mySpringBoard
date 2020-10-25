package com.myway.myboard.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;

import com.myway.myboard.common.MailUtils;
import com.myway.myboard.common.TempKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.support.SessionStatus;

import com.myway.myboard.dao.UserDAO;
import com.myway.myboard.model.UserVO;
import com.myway.myboard.service.UserService;

@Service("UserServiceimpl")
public class UserServiceimpl implements UserService{
	@Autowired
	public UserDAO userDAO;
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public List<UserVO> findAllUser() {
		return userDAO.findAllUser();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void createUser(UserVO userVO) throws Exception {
		// 임시키 값 상태를 설정 Authstatus default 0으로 해서 여기서 초기화안해줘도됨
		String authKey = new TempKey().getKey(8, false);
		userVO.setAuthkey(authKey);

		// DB에 유저 생성
		userDAO.createUser(userVO);

		// mail 작성 관련기능
		MailUtils sendMail = new MailUtils(mailSender);

		sendMail.setSubject("[SON's MyBoard] 회원가입 이메일 인증");
		sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
				.append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
				.append("<a href='http://localhost:9090/myboard/joinConfirm.do?userid=")
				.append(userVO.getId())
				.append("&email=")
				.append(userVO.getEmail())
				.append("&authkey=")
				.append(userVO.getAuthkey())
				.append("'target='_blenk'>이메일 인증 확인</a>")
				.toString());
		sendMail.setFrom("notkorea738@gmail.com ", "[SON's MyBoard] 관리자");
		sendMail.setTo(userVO.getEmail());
		sendMail.send();
	}

	@Override
	public UserVO loginCheck(UserVO userVO) {
		return userDAO.selectLogin(userVO);
	}

	@Override
	public void logout(SessionStatus session) {
		if(!session.isComplete())
			session.setComplete();
	}

	@Override
	public Integer idCheck(String id) {
		return userDAO.idCheck(id);
	}

	@Override
	public Integer updateUser(UserVO userVO) {
		return userDAO.updateUser(userVO);
	}

	@Override
	public boolean updateAuthStatus(String userid, String authkey) {
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put("id", userid);
		sqlMap.put("authkey", authkey);
		Integer state = userDAO.updateAuthStatus(sqlMap);
		if(state > 0)
			return true;
		return false;
	}

	@Override
	public boolean deleteUser(Integer seq, String id) {
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put("seq", seq);
		sqlMap.put("writer", id);
		Integer state = userDAO.deleteUser(sqlMap);
		if(state > 0)
			return true;
		return false;
	}


}
