package com.myway.myboard.dao;

import java.util.List;
import java.util.Map;

import com.myway.myboard.model.UserVO;

public interface UserDAO {
	List<UserVO> findAllUser();
	Integer createUser(UserVO userVO);
	UserVO selectLogin(UserVO userVO);
	Integer idCheck(String id);
	Integer updateUser(UserVO userVO);
	Integer updateAuthStatus(Map<String, Object> map);
	Integer deleteUser(Map<String, Object> map);
}
