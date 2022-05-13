package com.uos.intgtask.repository.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Order implements Serializable {

	private static final long serialVersionUID = 5528359196574265061L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long order_id;
	@Column(nullable = false)
	private Long customer_id;
	private LocalDate date;
	private Double amount;
}
