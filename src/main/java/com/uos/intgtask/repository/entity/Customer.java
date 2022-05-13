package com.uos.intgtask.repository.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Customer implements Serializable {

	private static final long serialVersionUID = -8848155303891389797L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstname;
	private String surname;
	private String email;
	private String address;
	private String zip_code;
	private String region;
	private String status;
	@JoinColumn(insertable = false, updatable = false, name = "customer_id", referencedColumnName = "id")
	@OneToMany(fetch = FetchType.EAGER)
	private List<Order>	orders;
}
