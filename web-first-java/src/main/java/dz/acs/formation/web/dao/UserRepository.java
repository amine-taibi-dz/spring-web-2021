package dz.acs.formation.web.dao;

import dz.acs.formation.web.model.User;

public interface UserRepository {
	public User findByUsername(String username);
}
