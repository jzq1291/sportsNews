package com.jzq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jzq.model.Sports;
import com.jzq.utils.IProcessRS;
import com.jzq.utils.JdbcUtils;

/**
 * 主题（运动种类）相关DAO
 * @author bill
 * @date   2018年5月3日 上午11:17:24
 */
public class ThemeDao {
	static JdbcUtils db = new JdbcUtils();
	
	/**
	 * 返回所有运动种类及其信息的Dao 
	 * @author bill
	 * @date   2018年5月3日 上午11:33:04
	 * @return 
	 */
	public List<Sports> getThemeList(){
		List<Sports> sports = new ArrayList<Sports>();
		String sql = "select sport_id,sport_name,description,display from sports";
		db.excute_STquery(sql, new IProcessRS() {
			@Override
			public void ProcessRS(ResultSet rs) throws SQLException {
				while(rs.next()){
					Integer sport_id = rs.getInt("sport_id");
					String sport_name = rs.getString("sport_name");
					String description = rs.getString("description");
					String display = rs.getString("display");
					Sports sport = new Sports(sport_id,sport_name,description,display);
					sports.add(sport);
				}
			}
		});
		
		return sports;
	}
}
