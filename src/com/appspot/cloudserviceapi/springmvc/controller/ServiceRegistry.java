package com.appspot.cloudserviceapi.springmvc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appspot.cloudserviceapi.sci.dao.ServiceRegistryRepository;

@Controller
public class ServiceRegistry {
    private static Logger logger = LoggerFactory.getLogger(ServiceRegistry.class);

    @Autowired
    ServiceRegistryRepository srRepository;

	@RequestMapping(value = "/srs", method = RequestMethod.GET)
    public @ResponseBody List<tapp.model.ServiceRegistry> getAllPlayers() {
        return srRepository.findAll();
    }

    @RequestMapping(value = "/srs/{id}", method = RequestMethod.GET)
    public @ResponseBody tapp.model.ServiceRegistry getPlayer(@PathVariable("id") Long id) {
        return srRepository.findOne(id);
    }

//    @RequestMapping(value = "/initDB", method = RequestMethod.GET)
//    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
//    public ResponseEntity<string> initDB() {
//        List<ServiceRegistry> srs = new ArrayList<ServiceRegistry>();
//        srs.add(new ServiceRegistry("Snoopy", "9p"));
//        srs.add(new ServiceRegistry("Wookstock", "9p"));
//        srs.add(new ServiceRegistry("Charlie", "1d"));
//        srs.add(new ServiceRegistry("Lucy", "4d"));
//        srs.add(new ServiceRegistry("Sally", "5d"));
//        srRepository.save(srs);
//        return new ResponseEntity<string>("5 srs inserted into database", HttpStatus.OK);
//    }
}