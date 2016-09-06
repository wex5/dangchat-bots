package com.justep.dangchat.bots.command;

/**
 * 命令参数类
 * 
 * @author Lining
 * @date 2016/9/2
 *
 */
public final class CommandParameter {

	private String value; // 参数值
	private int endPosition; // 参数值的结束位置

	public CommandParameter(String value, int endPosition) {
		this.value = value;
		this.endPosition = endPosition;
	}

	public String getValue() {
		return this.value;
	}

	public int getEndPosition() {
		return this.endPosition;
	}

}
