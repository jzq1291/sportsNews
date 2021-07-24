package com.jzq.model;


import java.util.Date;


/**
 * 新闻详情实体类
 * @author bill
 * @date   2018年5月10日 上午10:31:38
 */
public class News{

	private Integer newsId;
	private Integer sport_id;
	private Integer source_id;
	private String title;
	private String url;
	private String img_url;
	private String author;
	private Date date;
	private String content;
	
	public News() {
		super();
		// TODO Auto-generated constructor stub
	}

	public News(Integer newsId, Integer sport_id, Integer source_id,
			String title, String url, String img_url, String author, Date date,
			String content) {
		super();
		this.newsId = newsId;
		this.sport_id = sport_id;
		this.source_id = source_id;
		this.title = title;
		this.url = url;
		this.img_url = img_url;
		this.author = author;
		this.date = date;
		this.content = content;
	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public Integer getSport_id() {
		return sport_id;
	}

	public void setSport_id(Integer sport_id) {
		this.sport_id = sport_id;
	}

	public Integer getSource_id() {
		return source_id;
	}

	public void setSource_id(Integer source_id) {
		this.source_id = source_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "News [newsId=" + newsId + ", sport_id=" + sport_id
				+ ", source_id=" + source_id + ", title=" + title + "]";
	}
	
}
