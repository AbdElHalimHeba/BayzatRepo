package com.bayzdelivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.bayzdelivery.entity.Customer;

@RepositoryRestResource(exported=false)
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
