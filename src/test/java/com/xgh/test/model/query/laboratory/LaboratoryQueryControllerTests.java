package com.xgh.test.model.query.laboratory;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.xgh.model.command.laboratory.LaboratoryId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.query.laboratory.Laboratory;
import com.xgh.model.command.valueobjects.Address;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Phone;
import com.xgh.model.command.valueobjects.PostalCode;

// TODO: criar teste de falha de bad request e entity not found
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class LaboratoryQueryControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	protected JdbcTemplate connection;

	@Autowired
	private PostgresEventStore eventStore;

	@Before
	public void before() {
		connection.update("truncate table laboratory");
	}

	@Test
	public void findById() {
		UUID laboratoryId = createSampleEntity();

		ResponseEntity<Laboratory> response = restTemplate.getForEntity("/laboratories/{id}", Laboratory.class,
				laboratoryId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(laboratoryId, response.getBody().getId());
		assertEquals("Laboratório dos Hackers", response.getBody().getCompanyName());
		assertEquals("44313371337", response.getBody().getPhone());
	}

	// TODO corrigir
	@Test
	public void findAllWithOnePage() throws IOException {
		List<UUID> laboratories = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			laboratories.add(createSampleEntity());
		}

		ResponseEntity<String> responseEntity = restTemplate.getForEntity("/laboratories", String.class);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		Page<Laboratory> response = new ObjectMapper().readValue(responseEntity.getBody(),
				new TypeReference<Page<Laboratory>>() {
				});
		for (int i = 0; i < 5; i++) {
			assertEquals(laboratories.get(i), response.getContent().get(i).getId());
		}
	}

	@Test
	public void findAllWithManyPages() {
		// TODO criar
	}

	private UUID createSampleEntity() {
		com.xgh.model.command.laboratory.Laboratory laboratory = new com.xgh.model.command.laboratory.Laboratory();
		laboratory.register(new LaboratoryId(), new Name("Laboratório dos Hackers"),
				new Phone("44313371337"), new Address(new PostalCode("87020-025", "Avenida", "Avenida Tiradentes",
						"Centro", "Maringá", "PR", "Brasil"), 587, null));
		eventStore.push(laboratory);
		return laboratory.getId().getValue();
	}
}