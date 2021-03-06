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
 * 修改一个数据
 * @author liten
 *
 */
@WebServlet(urlPatterns={"/update"})
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao=Factory.getUserDaoImpl();   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		User user=new User();
		String username=request.getParameter("nameid");//需修改对象
		user=userDao.findOne(username);
		user.setName(request.getParameter("username"));
		user.setUserclass(request.getParameter("userclass"));
		user.setSex(request.getParameter("sex"));
		user.setEmail(request.getParameter("email"));
		user.setOther(request.getParameter("textarea"));
		String[] hobbies=request.getParameterValues("hobby");
		String hobby="";
		for(int i=0;i<hobbies.length;i++){
			hobby=hobby+" "+hobbies[i];
		}
		user.setHobby(hobby);
		user.setDate(request.getParameter("date"));
		int i=userDao.update(user, username);
		if(i>0){
			List<User> list=userDao.findAll();
			request.setAttribute("list", list);
			request.getRequestDispatcher("/seeAll.jsp").forward(request, response);
		}
		request.getRequestDispatcher("/error.jsp").forward(request, response);
		
	}

}
