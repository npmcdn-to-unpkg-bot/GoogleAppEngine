package com.appspot.cloudserviceapi.web;
import com.appspot.cloudserviceapi.Service;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/services")
@Controller
@RooWebScaffold(path = "services", formBackingObject = Service.class)
public class ServiceController {
}
