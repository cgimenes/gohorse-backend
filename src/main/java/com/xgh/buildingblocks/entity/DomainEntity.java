package com.xgh.buildingblocks.entity;

import com.xgh.buildingblocks.JsonSerializable;

public abstract class DomainEntity<IdT extends EntityId> implements JsonSerializable {
    protected IdT id;

    public IdT getId() {
        return id;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (id == null) {
            return false;
        }
        DomainEntity<IdT> other = (DomainEntity<IdT>) obj;
        return id.equals(other.getId());
    }

    public String getType() {
        return this.getClass().getName();
    }
}
