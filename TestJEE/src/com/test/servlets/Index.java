package com.test.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index")
public class Index extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	public static final String VUE = "/WEB-INF/index.jsp";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ip = req.getHeader("x-forwarded-for");
		req.setAttribute("ip", ip);
		this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
	}
}
