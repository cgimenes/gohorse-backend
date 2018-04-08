package com.xgh.xgh.owner.infra;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.xgh.infra.PostgresRepository;
import com.xgh.valueobjects.EntityId;
import com.xgh.xgh.owner.querymodel.Owner;

@Component
public final class OwnerQueryRepository extends PostgresRepository {

	public List<Owner> findAll() {
		return connection.query(
				"select id, name, cpf, phone, birth_date, active from owner",
			    new OwnerRowMapper());
	}

	public Owner findById(EntityId id) {
		return connection.queryForObject(
				"select id, name, cpf, phone, birth_date, active from owner where id = ?",
				new Object[] {id.toString()},
				new OwnerRowMapper());
	}
	
	private final class OwnerRowMapper implements RowMapper<Owner> {
		@Override
		public Owner mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Owner(
				UUID.fromString(rs.getString("id")),
				rs.getString("name"),
				rs.getString("cpf"),
				rs.getString("phone"),
				rs.getDate("birth_date"),
				rs.getBoolean("active")
			);
		}
		
	}

}
