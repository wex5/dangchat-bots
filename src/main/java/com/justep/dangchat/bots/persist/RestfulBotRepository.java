package com.justep.dangchat.bots.persist;

import java.util.List;
import java.util.ArrayList;

import org.json.JSONArray;

import com.justep.dangchat.bots.model.Bot;
import com.justep.dangchat.bots.util.ConfigFileReader;
import com.justep.dangchat.bots.util.HttpClient;

/**
 * Restful机器人仓储实现类
 * 
 * @author Lining
 * @date 2016/9/2
 *
 */
final class RestfulBotRepository implements BotRepository {

	/**
	 * 得到全部机器人
	 * 
	 * @return 包含机器人实例的List
	 * @throws RepositoryException
	 */
	@Override
	public List<Bot> findAll() throws RepositoryException {
		String restUrl = ConfigFileReader.getValue("/config.xml", "getBotListUrl");
		if (restUrl != null || "".equals(restUrl)) {
			try {
				JSONArray array = HttpClient.getJsonArray(restUrl);
				List<Bot> botList = new ArrayList<Bot>();
				for (int i = 0; i < array.length(); i++) {
					botList.add(new Bot(array.getJSONObject(i).getInt("id"), array.getJSONObject(i).getString("name"),
							array.getJSONObject(i).getString("token")));
				}
				return botList;
			} catch (Exception e) {
				throw new RepositoryException("获取bot列表失败，" + e.getMessage());
			}
		} else {
			throw new RepositoryException("配置文件不存在或没有设置 getBotListUrl节点值");
		}
	}

	@Override
	public boolean addGroup(String groupID) throws RepositoryException {
		// TODO Auto-generated method stub
		boolean flag = false;
		String restUrl = ConfigFileReader.getValue("/config.xml", "botGroup");
		if (restUrl != null || "".equals(restUrl)) {
			try {
				JSONArray array = HttpClient.addBotGroup(restUrl, groupID);
				if(array.length()>0) {
					flag = true;
				}
				return flag;
			} catch (Exception e) {
				throw new RepositoryException("保存botGroupID失败，" + e.getMessage());
			}
		} else {
			throw new RepositoryException("配置文件不存在或没有设置 botGroup节点值");
		}
	}

}
