package com.justep.dangchat.bots.util;

import java.util.*;

import com.justep.dangchat.bots.command.CommandParameter;

/**
 * 命令解析器类
 * 
 * @author Lining
 * @date 2016/9/2
 *
 */
public class CommandParser {

	/**
	 * 得到命令的所有参数
	 * 
	 * @param command
	 *            命令内容
	 * @return 包含命令参数的List
	 */
	public static List<CommandParameter> getParameters(String command) {
		List<CommandParameter> list = new ArrayList<CommandParameter>();
		int i = 0;
		while (i < command.length()) {
			CommandParameter param = getCommandParameter(command, i);
			if (param != null) {
				list.add(param);
				i = param.getEndPosition() + 1;
			} else {
				break;
			}
		}
		return list;
	}

	/**
	 * 得到命令参数
	 * 
	 * @param command
	 *            命令内容
	 * @param startPos
	 *            解析命令的起始位置
	 * @return
	 */
	private static CommandParameter getCommandParameter(String command, int startPos) {
		StringBuilder sb = new StringBuilder();
		int flagCount = 0; // 标记（双引号）的数量，1为参数值开始位置，2为参数值结尾位置。
		int endPos = 0; // 参数值的结尾位置
		char[] cmdChars = command.toCharArray();
		for (int i = startPos; i < cmdChars.length; i++) {
			if (cmdChars[i] == '"') {
				flagCount++;
				if (flagCount == 2) {
					endPos = i;
					break;
				}
				continue;
			}
			if (flagCount == 1) {
				sb.append(cmdChars[i]);
			}
		}
		return new CommandParameter(sb.toString(), endPos);
	}

}
