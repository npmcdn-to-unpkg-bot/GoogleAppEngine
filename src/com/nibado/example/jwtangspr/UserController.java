package com.nibado.example.jwtangspr;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.common.ParseHelper;
import app.common.SecurityUtils;

import com.appspot.cloudserviceapi.common.Constants;
import com.appspot.cloudserviceapi.security.spring.GaeUserDetails;
import com.appspot.cloudserviceapi.security.spring.UserSecurityDAO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/user")
public class UserController {

    private final Map<String, List<String>> userDb = new HashMap<>();

    public UserController() {
//        userDb.put("tom", Arrays.asList("user"));
//        userDb.put("sally", Arrays.asList("user", "admin"));
//        userDb.put("foo", Arrays.asList("user", "admin", "foo"));
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody final UserLogin login)
        throws ServletException {
    	System.out.println("name [" + login.name + "] password [" + login.password + "]");
		boolean authenticated = false;
		if(SecurityUtils.isParseLogin(login.password) && ParseHelper.isSessionValid(login.name, login.password)) {
			authenticated = true;
		}
		else {
			GaeUserDetails usr = (new UserSecurityDAO()).getGaeUserDetails(login.name);
			if(usr != null && usr.getPassword() != null && usr.getPassword().equals(login.password)) {
				authenticated = true;
			}
		}

        if (//login.name == null || !userDb.containsKey(login.name)
        	!authenticated
        	) {
            throw new ServletException("Invalid login");
        } else {
            userDb.put(login.name, Arrays.asList("user"));
        }
        return new LoginResponse(Jwts.builder().setSubject(login.name)
            .claim("roles", userDb.get(login.name)).setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256, Constants.JWT_SECRET_KEY).compact());
    }

    @SuppressWarnings("unused")
    private static class UserLogin {
        public String name;
        public String password;
    }

    @SuppressWarnings("unused")
    private static class LoginResponse {
        public String token;

        public LoginResponse(final String token) {
            this.token = token;
        }
    }
}
