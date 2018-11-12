package com.teng.factory;

import com.teng.dao.UserDao;
import com.teng.dao.impl.UserDaoImpl;

public class Factory {
	
	public static UserDao getUserDaoImpl(){
		return new UserDaoImpl();
	}
}
