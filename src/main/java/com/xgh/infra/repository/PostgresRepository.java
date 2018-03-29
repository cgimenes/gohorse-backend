package com.xgh.infra.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class PostgresRepository {
	@Autowired
	protected JdbcTemplate connection;
}
