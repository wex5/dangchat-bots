package com.justep.dangchat.bots.command;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.justep.dangchat.bots.core.DangchatBot;
import com.justep.dangchat.bots.core.NotificationOverlord.DoBroadcast;
import com.justep.dangchat.bots.util.CommandParser;
import com.justep.dangchat.bots.util.ConfigFileReader;
import com.justep.dangchat.bots.util.HttpClient;

import im.actor.bots.BotMessages;

/**
 * 广播消息处理器类
 * 
 * @author Lining
 * @date 2016/9/5
 *
 */
public class BroadcastHandler extends CommandHandler {
	
	private String command; // bot接收到的命令

	/**
	 * 默认构造函数
	 * 
	 * @param command
	 *            bot接收到的命令
	 */
	public BroadcastHandler(String command) {
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
		// Broadcast（广播）类命令的格式为：broad "广播内容"
		// 得到命令参数，list[0]为广播内容
		List<CommandParameter> paramList = CommandParser.getParameters(this.command);
		if (paramList.size() != 1) {
			throw new CommandParameterException("命令格式不正确");
		}
		// 得到所有接收人
		ArrayList<BotMessages.User> userList = new ArrayList<BotMessages.User>();
		String restUrl = ConfigFileReader.getValue("/config.xml", "getUserListUrl");
		if (restUrl != null || "".equals(restUrl)) {
			try {
				JSONArray array = HttpClient.getJsonArray(restUrl);
				System.out.println("array.length:" + array.length());
				for (int i = 0; i <= array.length() - 1; i++) {
					JSONObject item = array.getJSONObject(i);
					BotMessages.User user = bot.findUser(item.getString("nickname"));
					if (user != null) {
						userList.add(user);
					}
				}
			} catch (Exception e) {
				throw new CommandParameterException("获取用户列表失败，" + e.getMessage());
			}
		} 
		// 发送广播
		bot.sendToOverlord(new DoBroadcast(paramList.get(0).getValue(), userList));
	}

}
