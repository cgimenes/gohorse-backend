package com.xgh.model.address.query;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xgh.model.postalcode.query.PostalCode;

@Entity
@Table(name = "address")
public class Address {
	@Id
	private UUID id;

    @ManyToOne
    @Column(name = "postalcode_id")
	private PostalCode postalCode;
	
	private Integer number;
	
	private String complement;
	
	protected Address() {}
	
	public Address(PostalCode postalCode, Integer number, String complement) {
		this.postalCode = postalCode;
		this.number = number;
		this.complement = complement;
	}

	public UUID getId() {
		return id;
	}
	
	public PostalCode getPostalCode() {
		return postalCode;
	}
		
	public Integer getNumber() {
		return number;
	}
	
	public String getComplement() {
		return complement;
	}
}
