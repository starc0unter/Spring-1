package com.chentsov.hw4.repositories;

import com.chentsov.hw4.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findOneByName(String theRoleName);
}
