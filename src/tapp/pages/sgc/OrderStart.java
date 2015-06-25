package tapp.pages.sgc;

import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

import tapp.model.sgc.WorkOrder;
import com.appspot.cloudserviceapi.sgc.services.manager.OrderManager;

public class OrderStart {

	@Inject
	private OrderManager beanManager;

	private WorkOrder myBean;

    @Inject
    private BeanModelSource beanModelSource;

	public WorkOrder getWorkOrder() {
		return myBean;
	}

	public void setWorkOrder(WorkOrder workOrder) {
		this.myBean = workOrder;
	}

	public List<WorkOrder> getWorkOrders() {
		return beanManager.getWorkOrders();
	}

	public boolean isEmptyList() {
		return beanManager.getWorkOrders().isEmpty();
	}

	public void onActionFromDelete(Long id) {
		beanManager.delete(id);
	}
}
