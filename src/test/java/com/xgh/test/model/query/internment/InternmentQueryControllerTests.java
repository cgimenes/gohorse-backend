package com.xgh.test.model.query.internment;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.animal.AnimalId;
import com.xgh.model.command.bed.BedId;
import com.xgh.model.command.internment.InternmentId;
import com.xgh.model.command.valueobjects.Date;
import com.xgh.model.query.internment.Internment;

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
		UUID internmentId = createSampleEntity();
		ResponseEntity<Internment> response = restTemplate.getForEntity("/internments/{id}", Internment.class,
				internmentId);

		com.xgh.model.command.internment.Internment internmentFromStore = eventStore
				.pull(com.xgh.model.command.internment.Internment.class, new InternmentId(internmentId));
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
//		assertEquals(internmentFromStore.getAnimalId(), response.getBody().getAnimalId());
//		assertEquals(internmentFromStore.getBedId(), response.getBody().getBedId());
//		assertEquals(internmentFromStore.getBusyAt(), response.getBody().getBusyAt());
//		assertEquals(internmentFromStore.getBusyUntil(), response.getBody().getBusyUntil());
	}

	private UUID createSampleEntity() {
		com.xgh.model.command.internment.Internment internment = new com.xgh.model.command.internment.Internment();
		internment.register(new InternmentId(), new BedId(), new AnimalId(), new Date("2018-04-20"),
				new Date("2018-04-20"));
		eventStore.push(internment);
		return internment.getId().getValue();
	}
}
