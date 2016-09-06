package com.justep.dangchat.bots.persist;

/**
 * bot仓储工厂类
 * @author Lining
 * @date 2016/9/2
 *
 */
public class BotRepositoryFactory {

	/**
	 * 创建Bot仓储类的实例
	 * 
	 * @return Bot仓储类的实例
	 */
	public static BotRepository newInstance() {
		return new RestfulBotRepository();
	}

}
