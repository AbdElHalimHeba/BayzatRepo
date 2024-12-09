package com.bayzdelivery.entity;

import java.math.BigDecimal;
import java.time.Instant;

import com.bayzdelivery.common.enums.DeliveryStatus;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Entity
public class Delivery {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @NotNull
  @Enumerated(EnumType.STRING)
  private DeliveryStatus status;
  
  @NotNull
  private Instant startTime;
  
  private Instant endTime;

  @Positive
  private Double distance;

  @PositiveOrZero
  private BigDecimal commission;

  @JoinColumn(name = "order_id", unique = true)
  @OneToOne(fetch = FetchType.LAZY)
  private Order order;

  @JoinColumn(name = "courier_id")
  @ManyToOne(fetch = FetchType.LAZY)
  private Courier courier;
  
  @Embedded
  private AuditInfo auditInfo;

}
