package com.teng.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teng.bean.User;
import com.teng.dao.UserDao;
import com.teng.factory.Factory;


@WebServlet(urlPatterns={"/*.udo"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao=Factory.getUserDaoImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//在这个方法里处理增删查改，登录
				//设置字符集，防止中文乱码
				req.setCharacterEncoding("UTF-8");
				resp.setCharacterEncoding("UTF-8");
				
				String a=req.getServletPath();//获取servlet路径，如：/query.udo
				String b=a.substring(1, a.length()-4);//获取.udo的前缀，如：query
				try {
					//根据b值自动调用下面与b名一样的方法
					Method mothod=this.getClass().getDeclaredMethod(b,HttpServletRequest.class,HttpServletResponse.class);
					mothod.invoke(this,req,resp);
				} catch (Exception e) {
					e.printStackTrace();
				} 
	}
	/**
	 * 添加一个数据
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		User user=new User();
		user.setName(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
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
		int i=userDao.save(user);
		if(i>0){
			List<User> list=userDao.findAll();
			request.setAttribute("list", list);
			request.getRequestDispatcher("/seeAll.jsp").forward(request, response);
		}
		request.getRequestDispatcher("/error.jsp").forward(request, response);
	}
	/**
	 * 删除一个数据
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String name=request.getParameter("name");
		int i=userDao.delete(name);
		if(i>0){
			List<User> list=userDao.findAll();
			request.setAttribute("list", list);
			request.getRequestDispatcher("/seeAll.jsp").forward(request, response);
		}
		request.getRequestDispatcher("/error.jsp").forward(request, response);
	}
	/**
	 * 登录	
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		User user=userDao.login(username,password);
		if(user.getName()!=null){
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}else{
			request.getSession().setAttribute("note", "用户名或密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
	/**
	 *模糊查询
	 */
	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String key=request.getParameter("key");
		String value=request.getParameter("value");
		List<User> list=userDao.findClass(key, value);
		if(list!=null&& !list.isEmpty()){
			request.setAttribute("list", list);
			request.getRequestDispatcher("/seeAll.jsp").forward(request, response);
		}
		request.getRequestDispatcher("/error.jsp").forward(request, response);
	}
	/**
	 * 修改一个数据
	 *
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
	/**
	 *查找一个数据
	 */
	private void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String name=request.getParameter("name");
		User user=userDao.findOne(name);
		if(user!=null){
			request.setAttribute("user", user);
			request.getRequestDispatcher("/update.jsp").forward(request, response);
		}
		request.getRequestDispatcher("/error.jsp").forward(request, response);
	}
		
}
