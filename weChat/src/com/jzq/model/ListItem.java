package com.jzq.model;



/**
 * 列表单项
 * @author bill
 * @date   2018年5月3日 上午11:09:05
 */
public class ListItem {
	private Integer news_id;
	private Integer sport_id;
	private String img_url;
	private String title;

	public ListItem() {
		super();
	}
	
	public ListItem(Integer news_id, Integer sport_id, String img_url,
			String title) {
		super();
		this.news_id = news_id;
		this.sport_id = sport_id;
		this.img_url = img_url;
		this.title = title;
	}

	public Integer getNews_id() {
		return news_id;
	}

	public void setNews_id(Integer news_id) {
		this.news_id = news_id;
	}

	public Integer getSport_id() {
		return sport_id;
	}

	public void setSport_id(Integer sport_id) {
		this.sport_id = sport_id;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "ListItem [news_id=" + news_id + ", sport_id=" + sport_id
				+ ", img_url=" + img_url + ", title=" + title + "]";
	}

}
