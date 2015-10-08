package vaannila;

import com.opensymphony.xwork2.ActionSupport;

public class DefaultAction {

	public String execute() {
		System.out.println("DefaultAction: " + this.toString());
		return ActionSupport.SUCCESS;
	}

}
