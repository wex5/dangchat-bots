package com.justep.dangchat.bots.command;

public class CommandParameterException extends Exception {

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
	public CommandParameterException(String message) {
		super(message);
	}

}
