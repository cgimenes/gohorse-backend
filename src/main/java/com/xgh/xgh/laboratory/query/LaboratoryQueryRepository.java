package com.xgh.xgh.laboratory.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.xgh.valueobjects.Id;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;

@Component
public final class LaboratoryQueryRepository {
	@Autowired
	private JdbcTemplate connection;
	
    public List<Laboratory> findAll() {
        return connection.query(
        		"select id, company_name, phone from laboratory", 
        		new LaboratoryRowMapper());
    }

	public Laboratory findById(Id id) {
        return connection.queryForObject(
        		"select id, company_name, phone from laboratory where id = ?", 
        		new Object[] { id.toString() },
        		new LaboratoryRowMapper());
	}

	private final class LaboratoryRowMapper implements RowMapper<Laboratory> {
    	@Override
    	public Laboratory mapRow(ResultSet rs, int rowNum) throws SQLException {
    		return new Laboratory(
    			new Id(rs.getString("id")),
    			new Name(rs.getString("company_name")),
    			new Phone(rs.getString("phone"))
    		);
    	}
    }
}