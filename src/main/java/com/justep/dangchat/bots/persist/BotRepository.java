package com.justep.dangchat.bots.persist;

import java.util.List;

import com.justep.dangchat.bots.model.Bot;

/**
 * 机器人仓储借口
 * 
 * @author Lining
 * @date 2016/9/2
 *
 */
public interface BotRepository {

	/**
	 * 得到全部机器人
	 * 
	 * @return 包含机器人实例的List
	 * @throws RepositoryException
	 */
	List<Bot> findAll() throws RepositoryException;

}
