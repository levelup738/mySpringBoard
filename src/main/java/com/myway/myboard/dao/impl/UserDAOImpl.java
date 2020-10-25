package com.myway.myboard.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myway.myboard.dao.UserDAO;
import com.myway.myboard.model.UserVO;

@Repository
public class UserDAOImpl implements UserDAO{
	@Autowired
	private SqlSession sqlSession;
	
	private static final String NAME_SPACE = "com.myway.myboard.dao.UserDAO";
	@Override
	public List<UserVO> findAllUser() {
		return sqlSession.selectList(NAME_SPACE + ".findAllUser");
	}

	@Override
	public Integer createUser(UserVO userVO) {
		return sqlSession.insert(NAME_SPACE + ".createUser", userVO);
	}

	@Override
	public UserVO selectLogin(UserVO userVO) {
		return sqlSession.selectOne(NAME_SPACE + ".selectLogin", userVO);
	}

	@Override
	public Integer idCheck(String id) {
		return sqlSession.selectOne(NAME_SPACE + ".idCheck", id);
	}

	@Override
	public Integer updateUser(UserVO userVO) {
		return sqlSession.update(NAME_SPACE + ".updateUser", userVO);
	}

	@Override
	public Integer updateAuthStatus(Map<String, Object> map) { return sqlSession.update(NAME_SPACE + ".updateAuthStatus", map); }

	@Override
	public Integer deleteUser(Map<String, Object> map) { return sqlSession.delete(NAME_SPACE + ".deleteUser", map); }

}
