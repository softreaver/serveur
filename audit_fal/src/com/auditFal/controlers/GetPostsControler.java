package com.auditFal.controlers;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.auditFal.beans.Post;
import com.auditFal.dao.DAOFactory;
import com.auditFal.forms.PostForm;

public class GetPostsControler {
    @SuppressWarnings("unchecked")
    public static String getPosts(HttpServletResponse resp, DAOFactory daoFactory) {
	ArrayList<Post> allPosts;
	resp.setContentType("application/json; charset=utf-8");

	try {
	    PrintWriter writer = resp.getWriter();
	    PostForm postForm = new PostForm(daoFactory);
	    allPosts = postForm.getPosts();

	    /*
	     * construct the response body (format : [{<Item>},{<Item2>},...])
	     */
	    JSONArray jsonResp = new JSONArray();

	    for (Post post : allPosts)
		jsonResp.add(Post.toJsonObject(post));

	    /* write the response into the response body */
	    writer.print(jsonResp.toJSONString());

	    return jsonResp.toJSONString();

	} catch (Exception e) {
	    e.printStackTrace();
	    resp.setStatus(500);
	    return "";
	}
    }
}
