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
import com.orm.Ttixing;
import com.service.liuService;

public class tixing_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
        if(type.endsWith("tixingAdd"))
		{
			tixingAdd(req, res);
		}
        if(type.endsWith("tixingMana"))
		{
			tixingMana(req, res);
		}
		if(type.endsWith("tixingDel"))
		{
			tixingDel(req, res);
		}
		
	}
	
	public void tixingAdd(HttpServletRequest req,HttpServletResponse res)
	{
		int hetong_id=Integer.parseInt(req.getParameter("hetong_id"));
		String shoukuanshijian=req.getParameter("shoukuanshijian");
		int shoukuanjine=Integer.parseInt(req.getParameter("shoukuanjine"));
		
		String sql="insert into t_tixing values(?,?,?)";
		Object[] params={hetong_id,shoukuanshijian,shoukuanjine};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "信息添加完毕");
        String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}
	
	public void tixingMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List tixingList=new ArrayList();
		String sql="select * from t_tixing order by hetong_id";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Ttixing tixing=new Ttixing();
				
				tixing.setId(rs.getInt("id"));
				tixing.setHetong_id(rs.getInt("hetong_id"));
				tixing.setShoukuanshijian(rs.getString("shoukuanshijian"));
				tixing.setShoukuanjine(rs.getInt("shoukuanjine"));
				
				tixing.setHetong(liuService.get_hetong(rs.getInt("hetong_id")));
				
				tixingList.add(tixing);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("tixingList", tixingList);
		req.getRequestDispatcher("admin/tixing/tixingMana.jsp").forward(req, res);
	}
	
	
	
	
	public void tixingDel(HttpServletRequest req,HttpServletResponse res)
	{
		int id=Integer.parseInt(req.getParameter("id"));
		
		String sql="delete from t_tixing where id=?";
		Object[] params={id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "信息删除完毕");
        String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
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
