package com.xgh.model.command.laboratory;

import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.exceptions.NullMandatoryArgumentException;
import com.xgh.model.command.laboratory.events.LaboratoryWasDeleted;
import com.xgh.model.command.laboratory.events.LaboratoryWasRegistered;
import com.xgh.model.command.laboratory.events.LaboratoryWasUpdated;
import com.xgh.model.command.valueobjects.Address;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Phone;

public final class Laboratory extends AggregateRoot<LaboratoryId> {
    private static final long serialVersionUID = 7031562150242723400L;

    private Name companyName;
    private Phone phone;
    private Address address;

    public void register(LaboratoryId id, Name companyName, Phone phone, Address address) {
        if (id == null) {
            throw new NullMandatoryArgumentException("id");
        }

        if (companyName == null) {
            throw new NullMandatoryArgumentException("nome");
        }

        if (phone == null) {
            throw new NullMandatoryArgumentException("telefone");
        }

        if (address == null) {
            throw new NullMandatoryArgumentException("endereço");
        }

        recordAndApply(new LaboratoryWasRegistered(id, companyName, phone, address, this.nextVersion()));
    }

    public void update(Name companyName, Phone phone, Address address) {
        recordAndApply(new LaboratoryWasUpdated(this.id, companyName, phone, address, this.nextVersion()));
    }
    
    public void delete() {
        recordAndApply(new LaboratoryWasDeleted(this.id, this.nextVersion()));
    }

    protected void when(LaboratoryWasRegistered event) {
        this.companyName = event.getCompanyName();
        this.phone = event.getPhone();
        this.address = event.getAddress();
    }

    protected void when(LaboratoryWasUpdated event) {
        this.companyName = event.getCompanyName();
        this.phone = event.getPhone();
        this.address = event.getAddress();
    }
    
    protected void when(LaboratoryWasDeleted event) {
        this.markDeleted();
    }

    public Name getCompanyName() {
        return companyName;
    }

    public Phone getPhone() {
        return phone;
    }

    public Address getAddress() {
        return address;
    }
}
