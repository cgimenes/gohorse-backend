package com.xgh.xgh.laboratory.command;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.xgh.buildingblocks.EntityVersion;
import com.xgh.buildingblocks.EventStore;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;

@Component
public class LaboratoryCommandRepository extends EventStore<LaboratoryId, Laboratory> {
    @Override
    public Laboratory pull(LaboratoryId id) {
    	try {
            return connection.queryForObject(
            		"select id, entity_version, company_name, phone from laboratory where id = ?", 
            		new Object[] { id.toString() },
            		new LaboratoryRowMapper());
    	} catch (Exception ex) {
    		return null;
    	}
    }

    @Override
    protected void saveSnapshot(Laboratory laboratory) {
    	if (this.snapshotExists(laboratory.getId())) {
        	connection.update(
    		    "update laboratory set company_name = ?, phone = ?, entity_version = ? where id = ?",
    		    laboratory.getCompanyName().toString(), 
    		    laboratory.getPhone().toString(), 
    		    laboratory.getVersion().getValue(),
    		    laboratory.getId().toString()
    		);
        	return;
    	}
    	
    	connection.update(
		    "insert into laboratory (id, company_name, phone, entity_version) VALUES (?, ?, ?, ?)",
		    laboratory.getId().toString(), 
		    laboratory.getCompanyName().toString(), 
		    laboratory.getPhone().toString(),
		    laboratory.getVersion().getValue()
		);
	}

	private final class LaboratoryRowMapper implements RowMapper<Laboratory> {
    	@Override
    	public Laboratory mapRow(ResultSet rs, int rowNum) throws SQLException {
    		return new Laboratory(
    			new LaboratoryId(rs.getString("id")),
    			new EntityVersion(rs.getInt("entity_version")),
    			new Name(rs.getString("company_name")),
    			new Phone(rs.getString("phone"))
    		);
    	}
    }

	@Override
	protected String getSnapshotTableName() {
		return "laboratory";
	}
}
