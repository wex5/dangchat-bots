package com.justep.dangchat.bots.persist;

/**
 * 仓储异常类
 * 
 * @author Lining
 * @date 2016/9/2
 *
 */
public class RepositoryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 默认构造函数
	 * 
	 * @param message
	 *            异常内容
	 */
	public RepositoryException(String message) {
		super(message);
	}

}
