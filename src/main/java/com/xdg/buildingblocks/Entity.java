package com.xdg.buildingblocks;

abstract public class Entity<IdType> {

    protected IdType id;

    public IdType getId() {
        return id;
    }

    public Entity() {
    }

    protected void dispatch(Event event) {

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
