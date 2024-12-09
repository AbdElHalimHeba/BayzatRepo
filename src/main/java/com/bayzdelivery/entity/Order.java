package com.bayzdelivery.entity;

import java.math.BigDecimal;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Positive
	private BigDecimal price;
	
	@JoinColumn(name = "customer_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Customer customer;
	
	@JoinColumn(name = "seller_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Seller seller;
	
	@Embedded
	private AuditInfo auditInfo;
	
}
