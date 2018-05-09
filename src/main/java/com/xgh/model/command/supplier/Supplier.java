package com.xgh.model.command.supplier;

import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.exceptions.NullMandatoryArgumentException;
import com.xgh.model.command.supplier.events.SupplierWasUpdated;
import com.xgh.model.command.supplier.events.SupplierWasDeleted;
import com.xgh.model.command.supplier.events.SupplierWasRegistered;
import com.xgh.model.command.valueobjects.*;

public class Supplier extends AggregateRoot<SupplierId> {

	private static final long serialVersionUID = -1219322620120223404L;

	private Name name;
	private Phone phone;
	private CpfCnpj cpfCnpj;
	private Address address;
	private DistributionType distributionType;

	public void register(SupplierId id, Name name, Phone phone, CpfCnpj cpfCnpj, Address address,
			DistributionType distributionType) {
		if (id == null) {
			throw new NullMandatoryArgumentException("ID");
		}

		if (name == null) {
			throw new NullMandatoryArgumentException("Nome");
		}

		if (phone == null) {
			throw new NullMandatoryArgumentException("Telefone");
		}

		if (cpfCnpj == null) {
			throw new NullMandatoryArgumentException("CPF_CNPJ");
		}

		if (address == null) {
			throw new NullMandatoryArgumentException("Endereço");
		}

		if (distributionType == null) {
			throw new NullMandatoryArgumentException("Tipo de distribuição");
		}

		recordAndApply(
				new SupplierWasRegistered(id, name, phone, cpfCnpj, address, distributionType, this.nextVersion()));
	}

	public void update(Name name, Phone phone, CpfCnpj cpfCnpj, Address address, DistributionType distributionType) {
		recordAndApply(
				new SupplierWasUpdated(this.id, name, phone, cpfCnpj, address, distributionType, this.nextVersion()));
	}

	public void delete() {
		recordAndApply(new SupplierWasDeleted(this.id, this.nextVersion()));
	}

	protected void when(SupplierWasRegistered event) {
		this.id = event.getEntityId();
		this.name = event.getName();
		this.cpfCnpj = event.getCpfCnpj();
		this.phone = event.getPhone();
		this.address = event.getAddress();
		this.distributionType = event.getDistributionType();
	}

	protected void when(SupplierWasUpdated event) {
		this.name = event.getName();
		this.cpfCnpj = event.getCpfCnpj();
		this.phone = event.getPhone();
		this.address = event.getAddress();
		this.distributionType = event.getDistributionType();
	}

	protected void when(SupplierWasDeleted event) {
		this.markDeleted();
	}

	public Name getName() {
		return this.name;
	}

	public CpfCnpj getCpfCnpj() {
		return this.cpfCnpj;
	}

	public Phone getPhone() {
		return this.phone;
	}

	public DistributionType getDistributionType() {
		return this.distributionType;
	}

	public Address getAddress() {
		return this.address;
	}
}
