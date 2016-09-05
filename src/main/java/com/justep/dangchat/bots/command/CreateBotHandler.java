package com.justep.dangchat.bots.command;

import java.util.List;

import com.justep.dangchat.bots.core.DangchatBot;
import com.justep.dangchat.bots.util.CommandParser;

/**
 * 创建机器人命令处理类
 * 
 * @author Lining
 * @date 2016/9/2
 *
 */
public final class CreateBotHandler extends CommandHandler {

	private String command; // bot接收到的命令

	/**
	 * 默认构造函数
	 * 
	 * @param command
	 *            bot接收到的命令
	 */
	public CreateBotHandler(String command) {
		this.command = command;
	}

	/**
	 * 处理命令
	 * 
	 * @param bot
	 *            接收到命令的bot类的实例
	 * @throws CommandParameterException
	 */
	@Override
	public void handleCommand(DangchatBot bot) throws CommandParameterException {
		// CreateBot（创建机器人）命令的格式为：createbot "机器人名称" "机器人昵称"
		// 得到命令参数，list[0]机器人名称，list[1]为机器人昵称
		List<CommandParameter> paramList = CommandParser.getParameters(this.command);
		if (paramList.size() != 2) {
			throw new CommandParameterException("命令格式不正确");
		}

		bot.createBot(paramList.get(1).getValue(), paramList.get(0).getValue());
	}

}
