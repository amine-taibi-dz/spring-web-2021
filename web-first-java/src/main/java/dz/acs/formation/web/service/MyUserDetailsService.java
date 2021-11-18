package dz.acs.formation.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dz.acs.formation.web.dao.UserRepository;
import dz.acs.formation.web.dto.MyUserPrincipal;
import dz.acs.formation.web.model.User;
import lombok.extern.log4j.Log4j2;
/**
 * MyUserDetailsService
 * @author ataibi
 *
 */
@Log4j2
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
    	log.info("loadUserByUsername"+username);
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        //LDAP
//        List<String>list = List.of("ADMIN", "IT"); 
//        SimpleGrantedAuthority s0 = new SimpleGrantedAuthority(list.get(0));
//        SimpleGrantedAuthority s1 = new SimpleGrantedAuthority(list.get(0));
//        
//        org.springframework.security.core.userdetails.User princ = 
//        		new org.springframework.security.core.userdetails.User("root", "root", List.of(s0,s1));    
//        return princ;
        return new MyUserPrincipal(user);
    }
}