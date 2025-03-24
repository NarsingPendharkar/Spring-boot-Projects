package test.jwtAuthProject.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import test.jwtAuthProject.Model.Person;
import test.jwtAuthProject.Util.MethodsOfJwt;


@Controller
public class Homecontrol {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private MethodsOfJwt methodsOfJwt;

	
	 @GetMapping("/hello")
	    @ResponseBody
	    public String sayHello(){
	        return "Hello";
	    }
	    @GetMapping("/")
	    public String loginHello(){
	        return "login";
	    }


	    
	    @PreAuthorize("hasRole('USER')")
	    @GetMapping("/user")
	    @ResponseBody
	    public String userEndpoint(){
	        return "Hello, User!";
	    }

	    @PreAuthorize("hasRole('ADMIN')")
	    @GetMapping("/admin")
	    @ResponseBody
	    public String adminEndpoint(){
	        return "Hello, Admin!";
	    }
	    
	    // validate user
	    @PostMapping("/authuser")
	    public ModelAndView validateUser(@ModelAttribute Person person) {
	        Authentication auth;
	        try {
	            String username = person.getUsername();
	            String password = person.getPassword();
	            auth = authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(username, password));
	        } catch (Exception e) {
	            ModelAndView errorView = new ModelAndView("errorPage"); // Return errorPage.jsp
	            errorView.addObject("message", "Bad credentials");
	            errorView.addObject("status", false);
	            return errorView;
	        }

	        SecurityContextHolder.getContext().setAuthentication(auth);

	        UserDetails userDetails = (UserDetails) auth.getPrincipal();
	        String jwtToken = methodsOfJwt.generateTokenFromUsername(userDetails);

	        List<String> roles = userDetails.getAuthorities().stream()
	                .map(item -> item.getAuthority())
	                .collect(Collectors.toList());

	        ModelAndView mv = new ModelAndView("responsePage"); // Return responsePage.jsp
	        mv.addObject("username", userDetails.getUsername());
	        mv.addObject("roles", roles);
	        mv.addObject("token", jwtToken);
	        return mv;
	    }

	    
	    


}
