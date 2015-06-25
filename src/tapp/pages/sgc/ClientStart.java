package tapp.pages.sgc;

import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

import tapp.model.sgc.Client;
import tapp.model.sgc.WorkOrder;
import com.appspot.cloudserviceapi.sgc.services.manager.ClientManager;
import com.appspot.cloudserviceapi.sgc.services.manager.OrderManager;

public class ClientStart {

	@Inject
	private ClientManager beanManager;

	private Client myBean;

    @Inject
    private BeanModelSource beanModelSource;
    
	public Client getClient() {
		return myBean;
	}

	public void setClient(Client Client) {
		this.myBean = Client;
	}

	public List<Client> getClients() {
		return beanManager.getClients();
	}

	public boolean isEmptyList() {
		return beanManager.getClients().isEmpty();
	}

	public void onActionFromDelete(Long id) {
		beanManager.delete(id);
	}
}
