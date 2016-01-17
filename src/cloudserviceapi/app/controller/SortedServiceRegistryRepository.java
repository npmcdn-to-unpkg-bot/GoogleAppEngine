package cloudserviceapi.app.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tapp.model.ServiceRegistry;

public interface SortedServiceRegistryRepository {
	    public List<ServiceRegistry> findAll();

		public Page<ServiceRegistry> findAll(Pageable arg0);
}