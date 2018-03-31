package com.xgh.xgh.laboratory.command.commands;

import com.xgh.buildingblocks.Command;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.laboratory.command.LaboratoryId;

public final class RegisterLaboratory extends Command {
	private static final long serialVersionUID = 864620291981116409L;
	
	private final LaboratoryId id;
    private final Name companyName;
    private final Phone phone;

    public RegisterLaboratory() {
        // Gera um id caso o cliente não tenha passado na API
        this.id = new LaboratoryId();
        this.companyName = null;
        this.phone = null;
    }

    public LaboratoryId getId() {
        return id;
    }

    public Name getCompanyName() {
        return companyName;
    }

    public Phone getPhone() {
        return phone;
    }
}
