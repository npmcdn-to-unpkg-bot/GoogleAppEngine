package com.appspot.cloudserviceapi.sgc.services.manager;

import java.util.List;

import tapp.model.sgc.Employee;

public interface EmployeeManager {

        public abstract List<Employee> getEmployees();

        public abstract void setEmployees(List<Employee> myBeans);
        
        public void delete(Long id);
        
        public abstract void save(Employee myBean);
        
        public Employee getEmployee(Long id);       

}