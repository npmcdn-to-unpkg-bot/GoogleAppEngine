package crud;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import crud.model.StatesList;

public final class SelectStateAction extends ActionSupport implements
		SessionAware {
	private Map session;

	public void setSession(Map session) {

		this.session = session;

	}

	public String execute() {
		StatesList states = (StatesList) session.get("states");
		if (states == null) {
			states = new StatesList();
			session.put("states", states);
			session.put("currentState", states.get(0));
		}
		return SUCCESS;
	}
}