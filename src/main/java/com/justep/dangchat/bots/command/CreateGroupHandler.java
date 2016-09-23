package com.justep.dangchat.bots.command;

import java.util.ArrayList;
import java.util.List;

import com.justep.dangchat.bots.core.DangchatBot;
import com.justep.dangchat.bots.util.CommandParser;

import im.actor.bots.BotMessages;
import im.actor.bots.BotMessages.ResponseCreateGroup;

import com.justep.dangchat.bots.persist.*;

/**
 * 创建讨论组命令处理类
 * 
 * @author Lizengqiang
 * @date 2016/9/20
 *
 */
public final class CreateGroupHandler extends CommandHandler {

	private String command; // bot接收到的命令

	/**
	 * 默认构造函数
	 * 
	 * @param command
	 *            bot接收到的命令
	 */
	public CreateGroupHandler(String command) {
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
		// CreateGroup（创建讨论组）命令的格式为：creategroup "讨论组名称,唯一标示" "userID1,userID2...userIDN"
		// 得到命令参数，list[0]讨论组名称, list[1]讨论组成员
		List<CommandParameter> paramList = CommandParser.getParameters(this.command);
		if (paramList.size() != 2) {
			throw new CommandParameterException("命令格式不正确");
		}
		
		ResponseCreateGroup group = bot.createGroup(paramList.get(0).getValue());
		BotMessages.GroupOutPeer groupOutPeer = group.peer();
		int groupID = groupOutPeer.id();
		try {
			BotRepositoryFactory.newInstance().addGroup(groupID+"");
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 得到所有参与人
//		ArrayList<BotMessages.User> userList = new ArrayList<BotMessages.User>();
		String[] userIds = paramList.get(1).getValue().split(",");
		for (String userId : userIds) {
			BotMessages.User user = bot.findUser(userId);
			BotMessages.UserOutPeer userOutPeer = new BotMessages.UserOutPeer(user.id(), user.accessHash());
			if (user != null) {
//				userList.add(user);
				bot.inviteUserToGroup(groupOutPeer, userOutPeer);
			}
		}
	}

}
