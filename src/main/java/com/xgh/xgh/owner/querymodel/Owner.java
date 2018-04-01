package com.xgh.xgh.owner.querymodel;

import java.util.UUID;

public final class Owner {
	private String name;
	private String phone;
	private String cpf;
	private UUID id;
	
	public Owner(UUID id, String name, String cpf, String phone) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getCpf() {
		return cpf;
	}

	public UUID getId() {
		return id;
	}

}
