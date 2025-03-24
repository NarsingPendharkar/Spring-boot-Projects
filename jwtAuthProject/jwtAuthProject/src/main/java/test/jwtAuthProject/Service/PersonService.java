package test.jwtAuthProject.Service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import test.jwtAuthProject.Model.Person;

@Service
public class PersonService  implements  UserDetailsService{

	@Autowired
	private PersonRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Person databasePerson = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
	SimpleGrantedAuthority userRole = new SimpleGrantedAuthority("ROLE_" + databasePerson.getRole());

	User user = new User(databasePerson.getUsername(), databasePerson.getPassword(), 
			Collections.singletonList(userRole));
	return user;
	}
	public void save(Person users) {
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		userRepository.save(users);
		
	}

}
