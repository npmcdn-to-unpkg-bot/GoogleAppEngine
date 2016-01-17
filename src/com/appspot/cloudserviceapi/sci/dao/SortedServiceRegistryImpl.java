package com.appspot.cloudserviceapi.sci.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import tapp.model.ServiceRegistry;
import cloudserviceapi.app.controller.SortedServiceRegistryRepository;

public class SortedServiceRegistryImpl implements SortedServiceRegistryRepository {

    ServiceRegistryRepository repository;
    EntityManager entityManager;
    
    private SortedServiceRegistryImpl() {
    }

    public SortedServiceRegistryImpl(ServiceRegistryRepository repository, EntityManager entityManager) throws Exception {
    	if(repository == null || entityManager == null) throw new Exception("Repository/EntityManager is NULL or empty.");

    	this.repository = repository;
		this.entityManager = entityManager;
	}

    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.DESC, "lastUpdated");
    }

    @Override
    public List<ServiceRegistry> findAll() {
		return repository.findAll(sortByIdAsc());
	}

	@Override
	public Page<ServiceRegistry> findAll(Pageable arg0) {
		final PageRequest page2 = new PageRequest(
				arg0.getPageNumber(), arg0.getPageSize(), new Sort(
				    new Order(Direction.DESC, "lastUpdated") 
				  )
				);
		
		// Clears the cache to avoid inconsistency
		entityManager.clear();

		return repository.findAll(page2);
	}
}
