package com.teng.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teng.bean.User;
import com.teng.dao.UserDao;
import com.teng.factory.Factory;

/**
 *查找一个数据
 */
@WebServlet(urlPatterns={"/find"})
public class FindOne extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao=Factory.getUserDaoImpl();    
   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		User user=userDao.findOne(name);
		if(user!=null){
			request.setAttribute("user", user);
			request.getRequestDispatcher("/update.jsp").forward(request, response);
		}
		request.getRequestDispatcher("/error.jsp").forward(request, response);
	}

}
