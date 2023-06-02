package com.vijay.student.common;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(value = AuditingEntityListener.class)
public class Auditable {

	@CreatedBy
	@Column(name = "created_by", updatable = false)
	protected String created_by;

	@LastModifiedBy
	protected String modified_by;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	protected Date created_at;

	@UpdateTimestamp
	protected Date modified_at;

}
