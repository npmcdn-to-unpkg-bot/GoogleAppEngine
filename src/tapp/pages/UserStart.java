package tapp.pages;

import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

import com.appspot.cloudserviceapi.security.spring.GaeUserDetails;
import com.appspot.cloudserviceapi.services.manager.UserManager;

import tapp.model.sgc.Client;
import tapp.model.sgc.WorkOrder;
import com.appspot.cloudserviceapi.sgc.services.manager.ClientManager;
import com.appspot.cloudserviceapi.sgc.services.manager.OrderManager;

public class UserStart {

	@Inject
	private UserManager beanManager;

	private GaeUserDetails myBean;

    @Inject
    private BeanModelSource beanModelSource;
    
	public GaeUserDetails getUser() {
		return myBean;
	}

	public void setUser(GaeUserDetails User) {
		this.myBean = User;
	}

	public List<GaeUserDetails> getUsers() {
		return beanManager.getUsers();
	}

	public boolean isEmptyList() {
		return beanManager.getUsers().isEmpty();
	}

	public void onActionFromDelete(Long id) {
		beanManager.delete(id);
	}
}
