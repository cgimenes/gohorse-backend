package com.xgh.xgh.laboratory.infra;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.xgh.infra.PostgresRepository;
import com.xgh.valueobjects.EntityId;
import com.xgh.xgh.laboratory.querymodel.Laboratory;

@Component
public final class LaboratoryQueryRepository extends PostgresRepository {
    public List<Laboratory> findAll() {
        return connection.query(
        		"select id, company_name, phone from laboratory", 
        		new LaboratoryRowMapper());
    }

	public Laboratory findById(EntityId id) {
        return connection.queryForObject(
        		"select id, company_name, phone from laboratory where id = ?", 
        		new Object[] { id.toString() },
        		new LaboratoryRowMapper());
	}

	private final class LaboratoryRowMapper implements RowMapper<Laboratory> {
    	@Override
    	public Laboratory mapRow(ResultSet rs, int rowNum) throws SQLException {
    		return new Laboratory(
    			UUID.fromString(rs.getString("id")),
    			rs.getString("company_name"),
    			rs.getString("phone")
    		);
    	}
    }
}