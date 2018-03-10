package com.xdg.buildingblocks;

abstract public class Entity<IdType> {

    private IdType id;

    public IdType getId() {
        return id;
    }

    public Entity(IdType id) {
        this.id = id;
    }

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
        Entity other = (Entity) obj;
        return id.equals(other.getId());
    }
}
