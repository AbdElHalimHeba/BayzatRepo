package com.bayzdelivery.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Customer extends User {

  private String name;
  
  @JoinColumn(name = "address_id")
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Address address;
  
  @Embedded
  private AuditInfo auditInfo;

}
