package com.xgh.test.model.query.internment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import com.xgh.infra.repository.PostgresEventStore;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class InternmentQueryControllerTests {
	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	protected JdbcTemplate connection;

	@Autowired
	private PostgresEventStore eventStore;

	@Before
	public void before() {
		connection.update("truncate table internment");
	}
	
	@Test
	private void findById() {
		
	}
}
