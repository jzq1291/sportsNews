package com.jzq.service;

import java.util.List;

import com.jzq.dao.ThemeDao;
import com.jzq.model.Sports;


/**
 * 主题（运动种类）相关Service
 * @author bill
 * @date   2018年5月3日 上午11:16:09
 */
public class ThemeService {
	
	static ThemeDao themeDao = new ThemeDao();
	
	/**
	 * 返回所有运动种类及其信息的service
	 * @author bill
	 * @date   2018年5月3日 上午11:32:19
	 * @return
	 */
	public List<Sports> getThemeList(){
		return themeDao.getThemeList();
	}
}
