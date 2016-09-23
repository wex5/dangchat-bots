package com.justep.dangchat.bots.command;

import com.justep.dangchat.bots.core.DangchatBot;

/**
 * 命令处理器类
 * 
 * @author User
 * @date 2016/9/2
 *
 */
public abstract class CommandHandler {

	/**
	 * 创建命令处理器
	 * 
	 * @param command
	 *            bot接收到的命令
	 * @return CommandHandler的实现类
	 * @throws CommandParameterException
	 */
	public static final CommandHandler createHandler(String command) throws CommandParameterException {
		if (command == null || "".equals(command)) {
			throw new CommandParameterException("命令不能为空 ");
		}
		if (command.toLowerCase().startsWith("sendtext")) {
			return new NotificationHandler(command);
		} else if (command.toLowerCase().startsWith("echotext")) {
			return new EchoHandler(command);
		} else if (command.toLowerCase().startsWith("createbot")) {
			return new CreateBotHandler(command);
		} else if (command.toLowerCase().startsWith("broad")) {
			return new BroadcastHandler(command);
		} else if (command.toLowerCase().startsWith("creategroup")) {
			return new CreateGroupHandler(command);
		} else {
			throw new CommandParameterException("命令格式不正确：" + command);
		}
	}

	/**
	 * 处理命令
	 * 
	 * @param bot
	 *            接收到命令的bot类的实例
	 * @throws CommandParameterException
	 */
	public abstract void handleCommand(DangchatBot bot) throws CommandParameterException;

}
