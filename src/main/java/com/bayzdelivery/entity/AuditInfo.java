package com.bayzdelivery.entity;

import java.time.Instant;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EntityListeners;
import lombok.Data;

@Data
@Embeddable
@EntityListeners(AuditingEntityListener.class)
public class AuditInfo {

	@CreatedDate
	@Column(updatable = false)
	private Instant createdAt;
	
	@LastModifiedDate
	private Instant updatedAt;
	
	@CreatedBy
	@Column(updatable = false)
	private String createdBy;
	
	@LastModifiedBy
	private String updatedBy;
	
	@Version
	private Long version;
	
}
