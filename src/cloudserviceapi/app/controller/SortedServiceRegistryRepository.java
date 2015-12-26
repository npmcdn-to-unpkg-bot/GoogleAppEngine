package cloudserviceapi.app.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tapp.model.ServiceRegistry;

public interface SortedServiceRegistryRepository {
	    public List<ServiceRegistry> findAll();	// {
//	        return repository.findAll(sortByIdAsc());
//	    }

		public Page<ServiceRegistry> findAll(Pageable arg0);	// {
//			final PageRequest page2 = new PageRequest(
//					arg0.getPageNumber(), arg0.getPageSize(), new Sort(
//					    new Order(Direction.DESC, "lastUpdated") 
//					  )
//					);
//			return repository.findAll(page2);
//		}
	
}