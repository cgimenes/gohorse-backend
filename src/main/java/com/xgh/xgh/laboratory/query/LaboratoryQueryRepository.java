package com.xgh.xgh.laboratory.query;

import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.xgh.infra.repository.PagedResult;
import com.xgh.infra.repository.PostgresRepository;

@Repository
public class LaboratoryQueryRepository extends PostgresRepository {
	private final RowMapper<Laboratory> laboratoryRowMapper = (rs, rowNum) -> {
		return new Laboratory(
			UUID.fromString(rs.getString("id")),
			rs.getString("company_name"),
			rs.getString("phone")
		);
	};
	
    public PagedResult<Laboratory> findAll(int page) {
        return new PagedResult<Laboratory>(connection.query(
    		"select id, company_name, phone from laboratory limit ? offset ?", 
    		new Object[] { PagedResult.PAGE_SIZE, page * PagedResult.PAGE_SIZE },
    		laboratoryRowMapper));
    }

	public Laboratory findById(UUID id) {
        return connection.queryForObject(
    		"select id, company_name, phone from laboratory where id = ?", 
    		new Object[] { id },
    		laboratoryRowMapper);
	}
}