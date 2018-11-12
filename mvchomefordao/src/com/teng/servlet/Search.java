package com.teng.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teng.bean.User;
import com.teng.dao.UserDao;
import com.teng.factory.Factory;

/**
 *模糊查询
 */
@WebServlet(urlPatterns={"/search"})
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao=Factory.getUserDaoImpl();   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String key=request.getParameter("key");
		String value=request.getParameter("value");
		List<User> list=userDao.findClass(key, value);
		if(list!=null&& !list.isEmpty()){
			request.setAttribute("list", list);
			request.getRequestDispatcher("/seeAll.jsp").forward(request, response);
		}
		request.getRequestDispatcher("/error.jsp").forward(request, response);
	}

}
