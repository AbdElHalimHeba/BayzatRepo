package com.bayzdelivery.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.bayzdelivery.entity.Order;

@RepositoryRestResource(exported=false)
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("""
		    SELECT o
		    FROM Order o
		    JOIN FETCH o.customer c JOIN FETCH c.address ca
		    JOIN FETCH o.seller s JOIN FETCH s.address sa
		    WHERE o.id = :id
		""")
	public Optional<Order> findByIdWithCustomerAndSellerAddress(@Param("id") Long id);
}
