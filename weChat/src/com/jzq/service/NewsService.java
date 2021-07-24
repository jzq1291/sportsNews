package com.jzq.service;

import java.util.ArrayList;
import java.util.List;

import com.jzq.dao.LogDao;
import com.jzq.dao.NewsDao;
import com.jzq.dao.ProfileDao;
import com.jzq.model.ListItem;
import com.jzq.model.News;
import com.jzq.model.Profile;

/**
 * 新闻相关Service
 * 
 * @author bill
 * @date 2018年4月2日 下午1:57:46
 */
public class NewsService {

	static NewsDao newsdao = new NewsDao();
	static ProfileDao profiledao = new ProfileDao();
	static LogDao logdao = new LogDao();
	static ProfileService profileservice = new ProfileService();

	/**
	 * 通过新闻编号查询新闻详情
	 * 并且插入用户浏览日志
	 * 
	 * @author bill
	 * @date 2018年4月2日 下午2:01:40
	 * @param news_id
	 * @param openid 
	 * @return News
	 */
	public News getNewsById(Integer news_id, String openid) {
		News news = newsdao.getNewsById(news_id);
//		添加用户日志
		logdao.addLog(openid, news_id);

		// 如果新闻正文中包含图片,设置图片宽度高度
		String content = news.getContent();
		if (content.contains("<img")) {
			content = content.replaceAll("<img", "<img class=\"img\"");
			news.setContent(content);
		}
		return news;
	}

	/**
	 * 根据体育类别返回新闻list
	 * 
	 * @author bill
	 * @date 2018年4月16日 下午5:41:26
	 * @param sport_id
	 * @param page 
	 * @return List<ListItem>
	 */
	public List<ListItem> getNewsListByCategory(Integer sport_id, Integer page) {
		return newsdao.getNewsListByCategory(sport_id,page);
	}

	/**
	 * 根据用户喜好（必须登录）返回需要推荐的新闻列表 用户日志量小于100时： 查询用户喜好 用户日志量大于100时： 分析用户喜好
	 * 
	 * @author bill
	 * @date 2018年4月16日 下午5:24:13
	 * @param username
	 * @return List<ListItem>
	 */
	public List<ListItem> getNewsListByRecommend(String openId) {
		// 用户选择的喜好的数量
		int count = 0;
		List<Integer> pros = new ArrayList<>();
		// 用户日志量
		int log_num = logdao.getLog_Num(openId);
		if (log_num > 100) {
			pros = profileservice.getProfileByLog(openId);
		} else {
			Profile profile = new Profile();
			// 根据用户openId获取用户喜好
			profile = profiledao.getProfile(openId);
			// 判断用户喜好返回不同数据
			if (profile != null) {
				if (profile.getPro1() != 0) {
					pros.add(profile.getPro1());
				}
				if (profile.getPro2() != 0) {
					pros.add(profile.getPro2());
				}
				if (profile.getPro3() != 0) {
					pros.add(profile.getPro3());
				}
			}

		}
		
		// 判断用户所选喜好的个数（0/1/2/3）推荐每类新闻的条数（10/n）
		count = pros.size();
		// 用户没有选择喜好的体育类型（或者没有获取到openId）,返回最近的新闻（一次十条）
		if (count == 0) {
			return newsdao.getNewsListBySysRecommend();
		} else {
			return newsdao.getNewsListByUserRecommend(pros);
		}
	}

	
	/**
	 * 历史记录
	 * @author bill
	 * @date   2018年6月17日 下午7:50:54
	 * @param openId
	 * @return
	 */
	public List<ListItem> getNewsListByHistory(String openId) {
		List<Integer> logs = logdao.getRecentlyLog(openId);
		
		return newsdao.getNewsListByHistory(logs);
	}

	/**
	 * 新闻检索
	 * @author bill
	 * @date   2018年6月19日 上午12:54:49
	 * @param title
	 * @return
	 */
	public List<ListItem> getNewsListByTitle(String title) {
		return newsdao.getNewsListByTitle(title);
	}

}
