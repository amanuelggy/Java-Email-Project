package com.amanuel.app.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.amanuel.app.models.Role;
@Repository
public interface RoleRepo extends CrudRepository<Role, Long> {

}
