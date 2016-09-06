package com.justep.dangchat.bots.command;

import java.util.List;

import com.justep.dangchat.bots.core.DangchatBot;
import com.justep.dangchat.bots.util.CommandParser;

/**
 * 回复类消息命令处理类
 * 
 * @author Lining
 * @date 2016/9/2
 * 
 */
public final class EchoHandler extends CommandHandler {

	private String command; // bot接收到的命令

	/**
	 * 默认构造函数
	 * 
	 * @param command
	 *            bot接收到的命令
	 */
	public EchoHandler(String command) {
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
		// EchoText（回复类消息）命令的格式为：echotext "请求内容"
		List<CommandParameter> paramList = CommandParser.getParameters(this.command);
		if (paramList.size() != 1) {
			throw new CommandParameterException("命令格式不正确");
		}
	}

}
