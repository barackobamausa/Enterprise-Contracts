package com.action;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DB;
import com.orm.TAdmin;
import com.orm.Thetong;

public class hetong_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
        if(type.endsWith("hetongAdd"))
		{
			hetongAdd(req, res);
		}
        if(type.endsWith("hetongMana"))
		{
			hetongMana(req, res);
		}
		if(type.endsWith("hetongDel"))
		{
			hetongDel(req, res);
		}
		
		if(type.endsWith("hetongRes"))
		{
			hetongRes(req, res);
		}
	}
	
	public void hetongAdd(HttpServletRequest req,HttpServletResponse res)
	{
		String mingcheng=req.getParameter("mingcheng");
		String jiafangfuzeren=req.getParameter("jiafangfuzeren");
		String yifangfuzeren=req.getParameter("yifangfuzeren");
		String kaishishijian=req.getParameter("kaishishijian");
		
		String jieshushijian=req.getParameter("jieshushijian");
		int jine=Integer.parseInt(req.getParameter("jine"));
		String beizhu=req.getParameter("beizhu");
		String zt="正常";
		
		String sql="insert into t_hetong values(?,?,?,?,?,?,?,?)";
		Object[] params={mingcheng,jiafangfuzeren,yifangfuzeren,kaishishijian,jieshushijian,jine,beizhu,zt};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "信息添加完毕");
        String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}
	
	public void hetongMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List hetongList=new ArrayList();
		String sql="select * from t_hetong where zt ='正常'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Thetong hetong=new Thetong();
				
				hetong.setId(rs.getInt("id"));
				hetong.setMingcheng(rs.getString("mingcheng"));
				hetong.setJiafangfuzeren(rs.getString("jiafangfuzeren"));
				hetong.setYifangfuzeren(rs.getString("yifangfuzeren"));
				
				hetong.setKaishishijian(rs.getString("kaishishijian"));
				hetong.setJieshushijian(rs.getString("jieshushijian"));
				hetong.setJine(rs.getInt("jine"));
				hetong.setBeizhu(rs.getString("beizhu"));
				
				hetong.setZt(rs.getString("zt"));
				
				hetongList.add(hetong);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("hetongList", hetongList);
		req.getRequestDispatcher("admin/hetong/hetongMana.jsp").forward(req, res);
	}
	
	
	
	
	public void hetongDel(HttpServletRequest req,HttpServletResponse res)
	{
		int id=Integer.parseInt(req.getParameter("id"));
		
		String sql="update t_hetong set zt='删除' where id=?";
		Object[] params={id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "信息删除完毕");
        String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}
	
	
	public void hetongRes(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		System.out.println(req.getParameter("mingcheng").trim()+"HH");
		
		List hetongList=new ArrayList();
		String sql="select * from t_hetong where mingcheng like '%"+req.getParameter("mingcheng")+"%'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
                Thetong hetong=new Thetong();
				
				hetong.setId(rs.getInt("id"));
				hetong.setMingcheng(rs.getString("mingcheng"));
				hetong.setJiafangfuzeren(rs.getString("jiafangfuzeren"));
				hetong.setYifangfuzeren(rs.getString("yifangfuzeren"));
				
				hetong.setKaishishijian(rs.getString("kaishishijian"));
				hetong.setJieshushijian(rs.getString("jieshushijian"));
				hetong.setJine(rs.getInt("jine"));
				hetong.setBeizhu(rs.getString("beizhu"));
				
				hetong.setZt(rs.getString("zt"));
				
				hetongList.add(hetong);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("hetongList", hetongList);
		req.getRequestDispatcher("admin/hetong/hetongMana.jsp").forward(req, res);
	}
	
	

	public void dispatch(String targetURI,HttpServletRequest request,HttpServletResponse response) 
	{
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(targetURI);
		try 
		{
		    dispatch.forward(request, response);
		    return;
		} 
		catch (ServletException e) 
		{
                    e.printStackTrace();
		} 
		catch (IOException e) 
		{
			
		    e.printStackTrace();
		}
	}
	public void init(ServletConfig config) throws ServletException 
	{
		super.init(config);
	}
	
	public void destroy() 
	{
		
	}
}
