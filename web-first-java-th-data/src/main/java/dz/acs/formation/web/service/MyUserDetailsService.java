package dz.acs.formation.web.service;

import org.springframework.beans.factory.annotation.Autowired;
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
        User user = userRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }
}