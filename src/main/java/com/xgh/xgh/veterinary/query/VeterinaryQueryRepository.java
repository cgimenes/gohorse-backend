package com.xgh.xgh.veterinary.query;

import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.xgh.exceptions.EntityNotFoundException;
import com.xgh.infra.repository.PagedResult;
import com.xgh.infra.repository.PostgresRepository;

@Repository
public class VeterinaryQueryRepository extends PostgresRepository {
	private final RowMapper<Veterinary> veterinaryRowMapper = (rs, rowNum) -> {
		return new Veterinary(UUID.fromString(rs.getString("id")), rs.getString("name"), rs.getString("phone"),
				rs.getString("crmv"), rs.getString("email"), rs.getString("birth_date"));
	};

	public PagedResult<Veterinary> findAll(int page) {
		return new PagedResult<Veterinary>(connection.query(
				"select id, name, phone, crmv, email, birth_date from veterinary limit ? offset ?",
				new Object[] { PagedResult.PAGE_SIZE, page * PagedResult.PAGE_SIZE }, veterinaryRowMapper));
	}

	public Veterinary findById(UUID id) {
		try {
			return connection.queryForObject(
					"select id, name, phone, crmv, email, birth_date from veterinary where id = ?",
					new Object[] { id }, veterinaryRowMapper);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException();
		}
	}
}
