package com.orm;

public class Ttixing
{
	private int id;
	private int hetong_id;
	private String shoukuanshijian;
	private int shoukuanjine;
	
	private Thetong hetong;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getHetong_id()
	{
		return hetong_id;
	}
	public void setHetong_id(int hetong_id)
	{
		this.hetong_id = hetong_id;
	}
	public String getShoukuanshijian()
	{
		return shoukuanshijian;
	}
	public void setShoukuanshijian(String shoukuanshijian)
	{
		this.shoukuanshijian = shoukuanshijian;
	}
	public int getShoukuanjine()
	{
		return shoukuanjine;
	}
	public void setShoukuanjine(int shoukuanjine)
	{
		this.shoukuanjine = shoukuanjine;
	}
	public Thetong getHetong()
	{
		return hetong;
	}
	public void setHetong(Thetong hetong)
	{
		this.hetong = hetong;
	}
	
}
