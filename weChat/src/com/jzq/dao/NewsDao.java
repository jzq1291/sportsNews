package com.jzq.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jzq.model.ListItem;
import com.jzq.model.News;
import com.jzq.utils.IProcessRS;
import com.jzq.utils.JdbcUtils;
import com.jzq.utils.PsSetValues;

/**
 * 新闻相关Dao
 * 
 * @author bill
 * @date 2018年3月29日 下午5:00:55
 */
public class NewsDao {
	static JdbcUtils db = new JdbcUtils();

	/**
	 * 通过新闻编号查询新闻详情
	 * 
	 * @author bill
	 * @date 2018年4月2日 下午12:56:51
	 * @param news_id
	 * @return News
	 */
	public News getNewsById(Integer news_id) {
		@SuppressWarnings("unused")
		List<String> list = new ArrayList<>();
		News news = new News();
		String sql = "select * from news where news_id = ?";
		db.excute_PSTquery(sql, new PsSetValues() {

			@Override
			public void setValue(PreparedStatement pst) throws SQLException {
				pst.setInt(1, news_id);
			}
		}, new IProcessRS() {

			@Override
			public void ProcessRS(ResultSet rs) throws SQLException {
				while (rs.next()) {
					news.setAuthor(rs.getString("author"));
					news.setContent(rs.getString("content"));
					news.setDate(rs.getTimestamp("date"));
					news.setImg_url(rs.getString("img_url"));
					news.setNewsId(rs.getInt("news_id"));
					news.setSource_id(rs.getInt("source_id"));
					news.setSport_id(rs.getInt("sport_id"));
					news.setTitle(rs.getString("title"));
					news.setUrl(rs.getString("url"));
				}
			}
		});
		return news;
	}

	/**
	 * 根据传入新闻种类返回此类型的新闻列表
	 * 
	 * @author bill
	 * @date 2018年4月16日 下午5:46:49
	 * @param sport_id
	 * @param page 
	 * @return List<ListItem>
	 */
	public List<ListItem> getNewsListByCategory(Integer sport_id, Integer page) {
		List<ListItem> newsList = new ArrayList<ListItem>();
		Integer first = (page-1)*10 ;
		// 数据库最新十条
		String sql = "select news_id, sport_id, title, img_url from news where sport_id = ? "
				+ "order by date desc limit ?,?";
		db.excute_PSTquery(sql, new PsSetValues() {
			@Override
			public void setValue(PreparedStatement pst) throws SQLException {
				pst.setInt(1, sport_id);
				pst.setInt(2, first);
				pst.setInt(3, 10);
			}
		}, new IProcessRS() {
			@Override
			public void ProcessRS(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Integer news_id = rs.getInt("news_id");
					String img_url = rs.getString("img_url");
					String title = rs.getString("title");
					ListItem listItem = new ListItem(news_id, sport_id,
							img_url, title);
					newsList.add(listItem);
				}
			}
		});
		return newsList;
	}

	/**
	 * 用户没有选择喜好,返回最新的新闻
	 * 
	 * @author bill
	 * @date 2018年5月7日 下午7:31:11
	 * @return List<ListItem>
	 */
	public List<ListItem> getNewsListBySysRecommend() {
		List<ListItem> newsList = new ArrayList<ListItem>();
		String sql = "select news_id, sport_id, title, img_url from news order by date desc limit 10";
		db.excute_STquery(sql, new IProcessRS() {

			@Override
			public void ProcessRS(ResultSet rs) throws SQLException {

				while (rs.next()) {
					Integer news_id = rs.getInt("news_id");
					String img_url = rs.getString("img_url");
					String title = rs.getString("title");
					ListItem listItem = new ListItem(news_id, null, img_url,
							title);
					newsList.add(listItem);
				}
			}
		});
		return newsList;
	}

	
	/**
	 * 用户选择了喜好,根据喜好推荐相应内容 1*10/ 或 2*5/ 或 3*3
	 * 
	 * @author bill
	 * @date 2018年5月7日 下午8:21:04
	 * @param pros
	 * @return List<ListItem>
	 */
	public List<ListItem> getNewsListByUserRecommend(List<Integer> pros) {
		List<ListItem> newsList = new ArrayList<ListItem>();
		int count = pros.size(); // 用户喜好的个数
		int size = 10 / count; // 没类新闻的条数

		for (int i = 0; i < count; i++) {
			int sport_id = pros.get(i);
			String sql = "select news_id, sport_id, title, img_url from news where sport_id = ?"
					+ " order by date desc limit ?";

			db.excute_PSTquery(sql, new PsSetValues() {
				@Override
				public void setValue(PreparedStatement pst) throws SQLException {
					pst.setInt(1, sport_id);
					pst.setInt(2, size);
				}
			}, new IProcessRS() {
				@Override
				public void ProcessRS(ResultSet rs) throws SQLException {
					while (rs.next()) {
						Integer news_id = rs.getInt("news_id");
						String img_url = rs.getString("img_url");
						String title = rs.getString("title");
						ListItem listItem = new ListItem(news_id, null,
								img_url, title);
						newsList.add(listItem);
					}
				}
			});
		}

		return newsList;
	}

	
	/**
	 * 查询用户浏览记录
	 * @author bill
	 * @date   2018年6月17日 下午7:59:30
	 * @param logs
	 * @return
	 */
	public List<ListItem> getNewsListByHistory(List<Integer> logs) {
		List<ListItem> newsList = new ArrayList<ListItem>();
		if(logs.size()>0){
			String arg = logs.get(0) + "";
			for(int i = 1;i<logs.size();i++){
				arg += ",";
				arg += logs.get(i);
			}
			String sql = "select news_id, sport_id, title, img_url from news "
					+ "where news_id in (" + arg + ") order by date desc limit 20";
			db.excute_STquery(sql, new IProcessRS() {
				@Override
				public void ProcessRS(ResultSet rs) throws SQLException {
					while (rs.next()) {
						Integer news_id = rs.getInt("news_id");
						String img_url = rs.getString("img_url");
						String title = rs.getString("title");
						ListItem listItem = new ListItem(news_id, null,
								img_url, title);
						newsList.add(listItem);
					}
				}
			});
		}
		return newsList;
	}

	
	/**
	 * 检索新闻
	 * @author bill
	 * @date   2018年6月19日 上午12:55:54
	 * @param title
	 * @return
	 */
	public List<ListItem> getNewsListByTitle(String title) {
		List<ListItem> newsList = new ArrayList<ListItem>();
		String sql = "select news_id, sport_id, title, img_url from news where title like ? limit 0,10";
		db.excute_PSTquery(sql, new PsSetValues() {
			
			@Override
			public void setValue(PreparedStatement pst) throws SQLException {
				pst.setString(1, '%'+title+'%');
			}
		}, new IProcessRS() {
			
			@Override
			public void ProcessRS(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Integer news_id = rs.getInt("news_id");
					String img_url = rs.getString("img_url");
					String title = rs.getString("title");
					ListItem listItem = new ListItem(news_id, null,
							img_url, title);
					newsList.add(listItem);
				}
			}
		});
		return newsList;
	}

}
