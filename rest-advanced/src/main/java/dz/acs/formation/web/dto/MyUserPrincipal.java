package dz.acs.formation.web.dto;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import dz.acs.formation.web.model.User;
/**
 * MyUserPrincipal
 * @author ataibi
 *
 */
public class MyUserPrincipal implements UserDetails {
    /**
	 * 
	 */
	private static final long serialVersionUID = 439079845838220637L;
	private User user;

    public MyUserPrincipal(User user) {
        this.user = user;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> list = Arrays.asList(user.getProfils().split(","))
				.stream()
				.map(s->new SimpleGrantedAuthority("ROLE_"+s.toUpperCase().trim()))
				.collect(Collectors.toList());
		return list;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}