package com.bayzdelivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.bayzdelivery.entity.Courier;

@RepositoryRestResource(exported = false)
public interface CourierRepository extends JpaRepository<Courier, Long> {
	
}
