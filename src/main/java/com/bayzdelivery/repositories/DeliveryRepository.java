package com.bayzdelivery.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.bayzdelivery.common.enums.DeliveryStatus;
import com.bayzdelivery.entity.Courier;
import com.bayzdelivery.entity.Delivery;

@RepositoryRestResource(exported=false)
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

	public List<Delivery> findByCourierAndStatusNot(Courier courier, DeliveryStatus status);
	
	@Query("""
			SELECT courier.id, SUM(commission), AVG(commission)
			FROM Delivery
			WHERE startTime >= :startTime AND endTime <= :endTime
			GROUP BY courier
			ORDER BY SUM(commission) DESC
			""")
	public Page<Object[]> findAllGroupByCourierOrderByCommission(@Param("startTime") Instant startTime, @Param("endTime") Instant endTime, 
			Pageable pageable);
	
	public List<Delivery> findByStartTimeBetweenAndStatusNot(Instant startTime1, Instant startTime2, DeliveryStatus status);

}
