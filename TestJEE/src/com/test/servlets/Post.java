package com.test.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@SuppressWarnings("serial")
@WebServlet("/post")
public class Post extends HttpServlet {
	public static int nextId = 1;
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int add = (Math.random() > 0.5) ? 1 : 0;
			TimeUnit.SECONDS.sleep(1 + add);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Retrieve the body of the request
		String body = req.getReader().lines()
			    .reduce("", (accumulator, actual) -> accumulator + actual);
		
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		
		try {
			jsonObject = (JSONObject) jsonParser.parse(body);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject jsonResp = new JSONObject();
		jsonResp.put("itemType", (String) jsonObject.get("itemType"));
		jsonResp.put("id", new Integer(nextId++));
		
		
		PrintWriter writer = resp.getWriter();
		writer.println(jsonResp.toJSONString());
		
		
		//resp.setStatus(202);
	}
}
