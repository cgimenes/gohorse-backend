package com.xgh.buildingblocks;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

import com.xgh.valueobjects.EntityId;

@MappedSuperclass
public abstract class DomainEntity<IdType extends EntityId> {
    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "id"))
    protected IdType id;

    public IdType getId() {
        return id;
    }

    @SuppressWarnings("unchecked")
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if (id == null)
            return false;
        DomainEntity<IdType> other = (DomainEntity<IdType>) obj;
        return id.equals(other.getId());
    }

	public String getType() {
		return this.getClass().getName();
	}
}
