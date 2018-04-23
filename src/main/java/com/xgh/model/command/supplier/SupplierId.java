package com.xgh.model.command.supplier;

import com.xgh.buildingblocks.entity.EntityId;

import java.util.UUID;

public class SupplierId extends EntityId {
<<<<<<< HEAD

	private static final long serialVersionUID = 1610905777342790995L;

	public SupplierId() {
        super();
    }

    public SupplierId(String value) {
        super(value);
    }

    public SupplierId(UUID value) {
        super(value);
    }
    
}



=======
    private static final long serialVersionUID = 1610905777342790995L;

    public SupplierId(UUID id) {
        super(id);
    }

    public SupplierId(String id) {
        super(id);
    }

    public SupplierId() {
        super();
    }
}
>>>>>>> origin/master
