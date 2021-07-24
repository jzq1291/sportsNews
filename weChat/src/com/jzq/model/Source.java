package com.jzq.model;



/**
 * 新闻来源实体
 * @author bill
 * @date   2018年5月10日 上午10:55:31
 */
public class Source{

	private int sourceId;
	private String sourceName;
	private String homePage;

	public Source() {
	}

	public Source(int sourceId) {
		this.sourceId = sourceId;
	}

	public Source(int sourceId, String sourceName, String homePage) {
		this.sourceId = sourceId;
		this.sourceName = sourceName;
		this.homePage = homePage;
	}

	public int getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceName() {
		return this.sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getHomePage() {
		return this.homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	@Override
	public String toString() {
		return "Source [sourceId=" + sourceId + ", sourceName=" + sourceName
				+ ", homePage=" + homePage + "]";
	}
	
}
