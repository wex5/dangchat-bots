package com.justep.dangchat.bots.command;

import java.util.*;

import im.actor.bots.BotMessages;

import com.justep.dangchat.bots.core.DangchatBot;
import com.justep.dangchat.bots.util.CommandParser;
import com.justep.dangchat.bots.core.NotificationOverlord.DoNotification;

/**
 * 通知处理类
 * 
 * @author Lining
 * @date 2016/9/2
 *
 */
public final class NotificationHandler extends CommandHandler {

	private String command; // bot接收到的命令

	/**
	 * 默认构造函数
	 * 
	 * @param command
	 *            bot接收到的命令
	 */
	public NotificationHandler(String command) {
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
		// Notification（通知）类命令的格式为：sendtext "通知内容" "接收人1Id，接收人2,...接收人nId"
		// 得到命令参数，list[0]为通知内容，list[1]为接收人编号Ids
		List<CommandParameter> paramList = CommandParser.getParameters(this.command);
		if (paramList.size() != 2) {
			throw new CommandParameterException("命令格式不正确");
		}

		// 得到所有接收人
		ArrayList<BotMessages.User> userList = new ArrayList<BotMessages.User>();
		String[] userIds = paramList.get(1).getValue().split(",");
		for (String userId : userIds) {
			BotMessages.User user = bot.findUser(userId);
			if (user != null) {
				userList.add(user);
			}
		}

		// 发送通知
		bot.sendToOverlord(new DoNotification(paramList.get(0).getValue(), userList));
	}

}
