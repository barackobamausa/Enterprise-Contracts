package com.service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dao.DB;
import com.orm.Thetong;


public class liuService
{
	
	
	public static Thetong get_hetong(int id)
	{
		Thetong hetong=new Thetong();
		String sql="select * from t_hetong where id=?";
		Object[] params={id};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				hetong.setId(rs.getInt("id"));
				hetong.setMingcheng(rs.getString("mingcheng"));
				hetong.setJiafangfuzeren(rs.getString("jiafangfuzeren"));
				hetong.setYifangfuzeren(rs.getString("yifangfuzeren"));
				
				hetong.setKaishishijian(rs.getString("kaishishijian"));
				hetong.setJieshushijian(rs.getString("jieshushijian"));
				hetong.setJine(rs.getInt("jine"));
				hetong.setBeizhu(rs.getString("beizhu"));
				
				hetong.setZt(rs.getString("zt"));
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		return hetong;
	}
}
