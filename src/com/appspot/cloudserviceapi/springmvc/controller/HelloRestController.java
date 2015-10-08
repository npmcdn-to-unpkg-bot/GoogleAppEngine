package com.appspot.cloudserviceapi.springmvc.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greet")
public class HelloRestController {
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public @ResponseBody String sayHello(@PathVariable String name) {
		String result = "{\"id\":1,\"content\":\"Hello, World + " + name + "!\"}";

		return result;
	}
}