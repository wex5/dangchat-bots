package com.justep.dangchat.bots.model;

/**
 * bot实体类
 * 
 * @author Lining
 * @date 2016/9/1
 *
 */
public class Bot {

	private int id;
	private String name;
	private String token;

	public Bot() {
	}

	public Bot(int id, String name, String token) {
		this.id = id;
		this.name = name;
		this.token = token;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
