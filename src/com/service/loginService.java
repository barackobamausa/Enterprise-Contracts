package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;


import com.dao.DB;
import com.orm.Thetong;
import com.orm.TAdmin;

public class loginService
{
	
	
	
	public String login(String userName,String userPw,int userType)
	{
		System.out.println("userType"+userType);
		try
		{
			Thread.sleep(700);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String result="no";
		
		if(userType==0)//系统管理员登陆
		{
			String sql="select * from t_admin where userName=? and userPw=?";
			Object[] params={userName,userPw};
			DB mydb=new DB();
			mydb.doPstm(sql, params);
			try 
			{
				ResultSet rs=mydb.getRs();
				boolean mark=(rs==null||!rs.next()?false:true);
				if(mark==false)
				{
					 result="no";
				}
				else
				{
					 result="yes";
					 TAdmin admin=new TAdmin();
					 admin.setUserId(rs.getInt("userId"));
					 admin.setUserName(rs.getString("userName"));
					 admin.setUserPw(rs.getString("userPw"));
					 WebContext ctx = WebContextFactory.get(); 
					 HttpSession session=ctx.getSession(); 
					 session.setAttribute("userType", 0);
		             session.setAttribute("admin", admin);
				}
				rs.close();
			} 
			catch (SQLException e)
			{
				System.out.println("登录失败！");
				e.printStackTrace();
			}
			finally
			{
				mydb.closed();
			}
			
		}
		
		
		if(userType==1)
		{
			
		}
		if(userType==2)
		{
			
		}
		return result;
	}

    public String adminPwEdit(String userPwNew)
    {
		System.out.println("DDDD");
    	try 
		{
			Thread.sleep(700);
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebContext ctx = WebContextFactory.get(); 
		HttpSession session=ctx.getSession(); 
		TAdmin admin=(TAdmin)session.getAttribute("admin");
		
		String sql="update t_admin set userPw=? where userId=?";
		Object[] params={userPwNew,admin.getUserId()};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		
		return "yes";
    }
    
    
    public List hetongSelect()
    {
    	try 
		{
			Thread.sleep(700);
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
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
		return hetongList;
    }
}
