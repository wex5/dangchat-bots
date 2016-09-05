package com.justep.dangchat.bots;

import java.util.List;

import com.justep.dangchat.bots.core.BotManager;
import com.justep.dangchat.bots.util.ConfigFileReader;
import com.justep.dangchat.bots.model.Bot;
import com.justep.dangchat.bots.persist.*;

/**
 * dangchat bot服务程序入口
 * 
 * @author Lining
 * @date 2016/9/1
 *
 */
public class App {
	public static void main(String[] args) {
		try {
			String endpoint = ConfigFileReader.getValue("/config.xml", "endpoint");
			BotManager bm = new BotManager(endpoint);
			List<Bot> botList = BotRepositoryFactory.newInstance().findAll();
			for (Bot bot : botList) {
				// 多线程方式启动bot
				BotThread thread = new BotThread(bot.getId().toString(), bot.getToken(), bm);
				new Thread(thread).start();
			}
		} catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
}

/**
 * 机器人线程类
 * 
 * @author User
 * @date 2016/9/5
 *
 */
class BotThread implements Runnable {

	private String botId;
	private String token;
	private BotManager botManager;

	/**
	 * 默认构造函数
	 * 
	 * @param botId
	 *            机器人Id
	 * @param token
	 *            机器人token
	 * @param botManager
	 *            BotManager类的实例
	 */
	public BotThread(String botId, String token, BotManager botManager) {
		this.botId = botId;
		this.token = token;
		this.botManager = botManager;
	}

	/**
	 * 实现Runnable接口方法
	 */
	public void run() {
		this.botManager.runBot(this.botId, this.token);
	}

}
