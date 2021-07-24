package com.jzq.model;

public class SportsItem extends Sports{
	private Boolean checked;

	public SportsItem(Boolean checked) {
		super();
		this.checked = checked;
	}

	public SportsItem() {
		super();
	}

	public SportsItem(Boolean checked,int sportId, String sportName, String description,
			String display) {
		super(sportId, sportName, description, display);
		this.checked = checked;
	}

	public SportsItem(int sportId) {
		super(sportId);
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
}
