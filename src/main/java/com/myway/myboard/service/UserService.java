package com.myway.myboard.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.support.SessionStatus;

import com.myway.myboard.dao.UserDAO;
import com.myway.myboard.model.UserVO;

public interface UserService {
	List<UserVO> findAllUser();
	void createUser(UserVO userVO) throws Exception ;
	UserVO loginCheck(UserVO userVO);
	void logout(SessionStatus session);
	Integer idCheck(String id);
	Integer updateUser(UserVO userVO);
	boolean updateAuthStatus(String userid, String authkey);
	boolean deleteUser(Integer seq, String id);
}
