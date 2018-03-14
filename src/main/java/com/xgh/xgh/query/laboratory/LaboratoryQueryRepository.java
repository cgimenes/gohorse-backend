package com.xgh.xgh.query.laboratory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.xgh.buildingblocks.QueryRepository;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;

@Component
public final class LaboratoryQueryRepository extends QueryRepository<LaboratoryId, Laboratory> {
    public List<Laboratory> findAll() {
        return connection.query(
        		"select id, name, phone from laboratory", 
        		new LaboratoryRowMapper());
    }

	@Override
	public Laboratory findById(LaboratoryId id) {
        return connection.queryForObject(
        		"select id, name, phone from laboratory where id = ?", 
        		new Object[] { id.toString() },
        		new LaboratoryRowMapper());
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