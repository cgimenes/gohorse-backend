package com.xgh.xgh.laboratory.query;

import java.util.UUID;

public final class Laboratory {
    private String companyName;
	private String phone;
	private UUID id;

    public Laboratory(UUID id, String companyName, String phone) {
        this.id = id;
        this.companyName = companyName;
        this.phone = phone;
    }
    
    public String getCompanyName() {
		return companyName;
	}

	public String getPhone() {
		return phone;
	}
	
    public UUID getId() {
        return id;
    }    
}
