package vaannila;

import java.util.ArrayList;
import java.util.Arrays;

import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport {
	
	private String password;
	
	private String gender;
	
	private String about;
	
	private String country;
	
	private ArrayList<Country> countryList;
	
	private String[] community;
	
	private ArrayList<String> communityList;
	
	private Boolean  mailingList;

	public String populate() {

		countryList = new ArrayList<Country>();
		countryList.add(new Country(1, "Malaysia"));
		countryList.add(new Country(2, "Singapore"));
		countryList.add(new Country(3, "USA"));
		countryList.add(new Country(3, "Any Other Country"));
		
		communityList = new ArrayList<String>();
		communityList.add("Business Owner");
		communityList.add("Personal");
		
		community = new String[]{"Business","User"};
		mailingList = true;
		
		return "populate";
	}

	public String home() {
		
		return "home";
	}
	
	public String execute() {
		System.out.println("RegisterAction: " + this.toString());
		return SUCCESS;
	}

//	public String getUserName() {
//		return first;
//	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public ArrayList<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(ArrayList<Country> countryList) {
		this.countryList = countryList;
	}

	public String[] getCommunity() {
		return community;
	}

	public void setCommunity(String[] community) {
		this.community = community;
	}

	public ArrayList<String> getCommunityList() {
		return communityList;
	}

	public void setCommunityList(ArrayList<String> communityList) {
		this.communityList = communityList;
	}

	public Boolean getMailingList() {
		return mailingList;
	}

	public void setMailingList(Boolean mailingList) {
		this.mailingList = mailingList;
	}

}