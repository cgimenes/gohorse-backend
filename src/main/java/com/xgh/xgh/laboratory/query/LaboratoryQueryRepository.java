package com.xgh.xgh.laboratory.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.xgh.infra.repository.PagedResult;
import com.xgh.infra.repository.PostgresRepository;
import com.xgh.valueobjects.EntityId;

@Repository
public class LaboratoryQueryRepository extends PostgresRepository {
	private LaboratoryRowMapper laboratoryRowMapper = new LaboratoryRowMapper();
	
    public PagedResult<Laboratory> findAll(int page) {
        return new PagedResult<Laboratory>(connection.query(
        		"select id, company_name, phone from laboratory ", 
        		laboratoryRowMapper));
    }

	public Laboratory findById(EntityId id) {
        return connection.queryForObject(
        		"select id, company_name, phone from laboratory where id = ?", 
        		new Object[] { id.toString() },
        		laboratoryRowMapper);
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