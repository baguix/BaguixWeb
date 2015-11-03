package com.baguix.web.service.core;


import com.baguix.web.model.db.core.TUser;
import com.baguix.web.model.page.core.User;

public interface UserServiceI {

	User save(User user);
	TUser findByUidPwd(String uid,String password);
	TUser findByUid(String uid);
}
