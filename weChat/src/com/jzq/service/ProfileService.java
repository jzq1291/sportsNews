package com.jzq.service;

import java.util.ArrayList;
import java.util.List;

import com.jzq.dao.ProfileDao;
import com.jzq.dao.ThemeDao;
import com.jzq.model.Profile;
import com.jzq.model.Sports;
import com.jzq.model.SportsItem;

/**
 * 用户喜好
 * @author bill
 * @date   2018年6月1日 下午4:46:58
 */
public class ProfileService {
	private ProfileDao profileDao = new ProfileDao();
	private ThemeDao themeDao = new ThemeDao();

	
	/**
	 * 获取用户喜好(修改用户喜好前的查询用于在修改页面显示)
	 * @author bill
	 * @date   2018年6月1日 下午5:04:32
	 * @param  openId
	 * @return
	 */

	public List<SportsItem> getProfile(String openId) {
		List<SportsItem> itenList = new ArrayList<>();
		List<Sports> list = new ArrayList<Sports>();
		list = themeDao.getThemeList();
		Profile profile = profileDao.getProfile(openId);
		List<Integer> proList = new ArrayList<>();
		if(profile.getPro1()!=null){
			proList.add(profile.getPro1());
		}
		if(profile.getPro2()!=null){
			proList.add(profile.getPro2());
		}
		if(profile.getPro3()!=null){
			proList.add(profile.getPro3());
		}
		for(Sports sport:list){
			Boolean flag = false;
			if(proList.contains(sport.getSportId())){
				flag = true;
			}
			SportsItem item = new SportsItem(flag, sport.getSportId(), sport.getSportName(),
					sport.getDescription(), sport.getDisplay());
			itenList.add(item);
		}
		return itenList;
	}
	
	
	/**
	 * 根据用户浏览日志返回用户喜好
	 * @author bill
	 * @date   2018年6月9日 下午5:50:06
	 * @param openId
	 * @return
	 */
	public List<Integer> getProfileByLog(String openId){
		int count = 0;
		List<Integer> pros = new ArrayList<>();
		List<Integer> proList = profileDao.getProfileByLog(openId);
//		不确定List中元素的数量,不能用subList(0,2)
		for(Integer sport_id:proList){
			pros.add(sport_id);
			count += 1;
			if(count ==3 ){
				break;
			}
		}
		return pros;
	}


	/**
	 * 更新用户喜好
	 * @author bill
	 * @date   2018年6月17日 下午5:19:07
	 * @param openId
	 * @param pros
	 */
	public void updateProfile(String openId, List<String> pros) {
		System.out.println(pros.size());
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < pros.size(); i++) {
			list.add(Integer.parseInt(pros.get(i)));
		}
		profileDao.updateProfile(openId, list);
	}
	
}
