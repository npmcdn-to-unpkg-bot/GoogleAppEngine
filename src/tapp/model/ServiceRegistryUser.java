package tapp.model;

import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class ServiceRegistryUser {
	@Persistent(primaryKey = "true", valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private List<ServiceRegistry> serviceRegistry;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ServiceRegistry> getServiceRegistry() {
		return serviceRegistry;
	}

	public void setServiceRegistry(List<ServiceRegistry> serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

}
