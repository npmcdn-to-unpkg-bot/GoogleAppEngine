package com.aurifa.struts2.tutorial.model;

import java.io.Serializable;

public class AccessControl implements Serializable {
	private Integer accessControlId;

	private String advertising;
	private String statement;
	private String viewChart;
	private String viewIP;
	private String viewPoints;
	private String editPoints;
	private String viewPersonInCharge;
	private String addPersonInCharge;
	private String viewCashier;
	private String addCashier;
	private String viewMember;
	private String addMember;
	private String settingBusinessHour;
	private String givePoints;
	private String receivedPoints;
	private String replaceCard;
	
	public Integer getAccessControlId() {
		return accessControlId;
	}
	public void setAccessControlId(Integer accessControlId) {
		this.accessControlId = accessControlId;
	}
	public String getAdvertising() {
		return advertising;
	}
	public void setAdvertising(String advertising) {
		this.advertising = advertising;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public String getViewChart() {
		return viewChart;
	}
	public void setViewChart(String viewChart) {
		this.viewChart = viewChart;
	}
	public String getViewIP() {
		return viewIP;
	}
	public void setViewIP(String viewIP) {
		this.viewIP = viewIP;
	}
	public String getViewPoints() {
		return viewPoints;
	}
	public void setViewPoints(String viewPoints) {
		this.viewPoints = viewPoints;
	}
	public String getEditPoints() {
		return editPoints;
	}
	public void setEditPoints(String editPoints) {
		this.editPoints = editPoints;
	}
	public String getViewPersonInCharge() {
		return viewPersonInCharge;
	}
	public void setViewPersonInCharge(String viewPersonInCharge) {
		this.viewPersonInCharge = viewPersonInCharge;
	}
	public String getAddPersonInCharge() {
		return addPersonInCharge;
	}
	public void setAddPersonInCharge(String addPersonInCharge) {
		this.addPersonInCharge = addPersonInCharge;
	}
	public String getViewCashier() {
		return viewCashier;
	}
	public void setViewCashier(String viewCashier) {
		this.viewCashier = viewCashier;
	}
	public String getAddCashier() {
		return addCashier;
	}
	public void setAddCashier(String addCashier) {
		this.addCashier = addCashier;
	}
	public String getViewMember() {
		return viewMember;
	}
	public void setViewMember(String viewMember) {
		this.viewMember = viewMember;
	}
	public String getAddMember() {
		return addMember;
	}
	public void setAddMember(String addMember) {
		this.addMember = addMember;
	}
	public String getSettingBusinessHour() {
		return settingBusinessHour;
	}
	public void setSettingBusinessHour(String settingBusinessHour) {
		this.settingBusinessHour = settingBusinessHour;
	}
	public String getGivePoints() {
		return givePoints;
	}
	public void setGivePoints(String givePoints) {
		this.givePoints = givePoints;
	}
	public String getReceivedPoints() {
		return receivedPoints;
	}
	public void setReceivedPoints(String receivedPoints) {
		this.receivedPoints = receivedPoints;
	}
	public String getReplaceCard() {
		return replaceCard;
	}
	public void setReplaceCard(String replaceCard) {
		this.replaceCard = replaceCard;
	}
	
}
