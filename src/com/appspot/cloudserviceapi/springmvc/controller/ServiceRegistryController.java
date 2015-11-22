package com.appspot.cloudserviceapi.springmvc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tapp.model.ServiceRegistry;

import com.appspot.cloudserviceapi.data.EMF;
import com.appspot.cloudserviceapi.sci.dao.ServiceRegistryRepository;

@Controller
@RequestMapping(value = "/fusr", headers="Accept=*/*")
@Secured("ROLE_USER")
@Api(value = "api", tags = "Service Registry")
public class ServiceRegistryController {
	@Autowired
    ServiceRegistryRepository repository;

    public ServiceRegistryController() {
    	JpaRepositoryFactory jpaRepositoryFactory = new JpaRepositoryFactory(EMF.get().createEntityManager());

    	repository = jpaRepositoryFactory.getRepository(ServiceRegistryRepository.class);
	}

	@ApiOperation(httpMethod = "GET", value = "Resource to get all Items", nickname="fusr")
    @ApiImplicitParams({
	    	@ApiImplicitParam(name = "pagaSize", defaultValue = "6", value = "Max item per page", required = true, dataType = "integer", paramType = "query"),
	    	@ApiImplicitParam(name = "pageNumber", defaultValue = "0", value = "Current page number (start from 0)", required = false, dataType = "integer", paramType = "query")
    	}
    )
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Page<ServiceRegistry> getAllPlayers(Pageable pageable) {
        return repository.findAll(pageable);
    }

	@ApiOperation(httpMethod = "GET", value = "Resource to get an Item" , nickname="fusr.id")
    @ApiImplicitParams({
	    	@ApiImplicitParam(name = "id", value = "Item unique id", required = true, dataType = "integer", paramType = "path")
    	}
    )
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ServiceRegistry getPlayer(@PathVariable("id") Long id) {
        return repository.findOne(id);
    }

	@ApiOperation(httpMethod = "POST", value = "Resource to create/change an item" , nickname="fusr.save")
	@ApiImplicitParams({
	    	@ApiImplicitParam(name = "sr", defaultValue = "", value = "Service Registry JSON object", required = true, dataType = "tapp.model.ServiceRegistry", paramType = "body")
		}
	)
    @RequestMapping(value= "/save", method = RequestMethod.POST)
	@Secured("ROLE_ADMIN")
    public ResponseEntity<ServiceRegistry> createOrUpdate(@RequestBody ServiceRegistry sr){
        try{
            if (sr != null){
            	repository.save(sr);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<ServiceRegistry>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<ServiceRegistry>(HttpStatus.OK);
    }

	@ApiOperation(httpMethod = "POST", value = "Resource to delete an Item" , nickname="fusr.delete.id")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "id", value = "Item unique id", required = true, dataType = "integer", paramType = "path")
    	}
    )
    @RequestMapping(value= "/delete/{id}", method = RequestMethod.POST)
	@Secured("ROLE_ADMIN")
    public ResponseEntity<ServiceRegistry> delete(@PathVariable Long id) {
        System.out.println("REST request to delete ServiceRegistry: " + id);
        try{
            if (id > -1){
                repository.delete(id);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<ServiceRegistry>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<ServiceRegistry>(HttpStatus.OK);
    }
}