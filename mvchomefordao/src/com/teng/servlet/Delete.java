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
 * delete data
 * @author liten
 *
 */
@WebServlet(urlPatterns={"/delete"})
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao=Factory.getUserDaoImpl();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		int i=userDao.delete(name);
		if(i>0){
			List<User> list=userDao.findAll();
			request.setAttribute("list", list);
			request.getRequestDispatcher("/seeAll.jsp").forward(request, response);
		}
		request.getRequestDispatcher("/error.jsp").forward(request, response);
	}

}
