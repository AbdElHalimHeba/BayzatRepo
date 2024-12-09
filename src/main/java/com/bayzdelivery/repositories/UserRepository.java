package com.bayzdelivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.bayzdelivery.entity.User;

@RepositoryRestResource(exported=false)
public interface UserRepository extends JpaRepository<User, Long> {

	public boolean existsByUsername(String username);

}
