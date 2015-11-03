package com.baguix.web.service.core.impl;


import com.baguix.web.dao.BaseDaoI;
import com.baguix.web.model.page.core.User;
import com.baguix.web.service.core.UserServiceI;
import com.baguix.web.model.db.core.TUser;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service("userService")
public class UserServiceImpl implements UserServiceI {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private BaseDaoI<TUser> userDao;

	@Override
	public User save(User user) {
		TUser t = new TUser();
		BeanUtils.copyProperties(user, t, new String[]{"pwd"});
		t.setId(UUID.randomUUID().toString());
		t.setPassword(user.getPwd());
		userDao.save(t);
		BeanUtils.copyProperties(t, user);
		return user;
	}

	@Override
	public TUser findByUidPwd(String uid, String password) {
		User user = new User();
		String hql = "from TUser as user where user.uid=:uid and user.password=:password";
		Map<String, Object> params = new HashMap<>();
		params.put("uid", uid);
		params.put("password", password);
		TUser t = userDao.getByHql(hql, params);
		return t;
	}

	@Override
	public TUser findByUid(String uid) {
		User user = new User();
		String hql = "from TUser as user where user.uid=:uid";
		Map<String, Object> params = new HashMap<>();
		params.put("uid", uid);
		TUser t = userDao.getByHql(hql, params);
		return t;
	}
}
