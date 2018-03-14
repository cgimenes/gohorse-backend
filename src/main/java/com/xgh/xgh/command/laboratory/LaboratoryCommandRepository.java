package com.xgh.xgh.command.laboratory;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.xgh.buildingblocks.Repository;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;

@Component
public final class LaboratoryCommandRepository extends Repository<LaboratoryId, Laboratory> {
    @Override
    public Laboratory findById(LaboratoryId id) {
        return connection.queryForObject(
        		"select id, name, phone from laboratory where id = ?", 
        		new Object[] { id.toString() },
        		new LaboratoryRowMapper());
    }

    @Override
    public void insert(Laboratory laboratory) {
    	connection.update(
		    "insert into laboratory (id, name, phone) VALUES (?, ?, ?)",
		    laboratory.getId().toString(), laboratory.getName().toString(), laboratory.getPhone().toString()
		);
    }

    @Override
    public void update(Laboratory laboratory) {
    	connection.update(
		    "update laboratory set name = ?, phone = ? where id = ?",
		    laboratory.getName().toString(), laboratory.getPhone().toString(), laboratory.getId().toString()
		);
    }

    private final class LaboratoryRowMapper implements RowMapper<Laboratory> {
    	@Override
    	public Laboratory mapRow(ResultSet rs, int rowNum) throws SQLException {
    		return new Laboratory(
    			new LaboratoryId(rs.getString("id")),
    			new Name(rs.getString("name")),
    			new Phone(rs.getString("phone"))
    		);
    	}
    }
}
