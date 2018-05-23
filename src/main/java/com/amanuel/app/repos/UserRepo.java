package com.amanuel.app.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.amanuel.app.models.User;
@Repository
public interface UserRepo extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
