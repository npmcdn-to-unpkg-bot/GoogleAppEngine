package com.appspot.cloudserviceapi.sgc.services.manager;

import java.util.List;

import tapp.model.sgc.WorkOrder;

public interface OrderManager {

        public abstract List<WorkOrder> getWorkOrders();

        public abstract void setWorkOrders(List<WorkOrder> myBeans);
        
        public void delete(Long id);
        
        public abstract void save(WorkOrder myBean);
        
        public WorkOrder getWorkOrder(Long id);       

}