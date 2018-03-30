package com.xgh.test.xgh.laboratory.query;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xgh.infra.repository.PagedResult;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.laboratory.query.Laboratory;

// TODO: criar teste de falha de bad request e entity not found
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class LaboratoryQueryControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private PostgresEventStore<com.xgh.xgh.laboratory.command.Laboratory, com.xgh.xgh.laboratory.command.LaboratoryId> eventStore; 

	@Autowired
	protected JdbcTemplate connection;

	@Before
	public void before() {
		connection.update("truncate table laboratory");
	}

	@Test
	public void findById() {
		Laboratory laboratory = createSampleLaboratory();

		ResponseEntity<Laboratory> response = restTemplate.getForEntity("/laboratories/{id}", Laboratory.class,
				laboratory.getId());
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(laboratory.getId(), response.getBody().getId());		
		assertEquals(laboratory.getCompanyName().toString(), response.getBody().getCompanyName().toString());
		assertEquals(laboratory.getPhone().toString(), response.getBody().getPhone().toString());
	}

	@Test
	public void findAllWithOnePage() throws JsonParseException, JsonMappingException, IOException {
		List<Laboratory> laboratories = new ArrayList<>(); 
		for (int i = 0; i < 5; i++) {
			laboratories.add(createSampleLaboratory());
		}

		ResponseEntity<String> responseEntity = restTemplate.getForEntity("/laboratories", String.class);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
		PagedResult<Laboratory> response = new ObjectMapper().readValue(responseEntity.getBody(), new TypeReference<PagedResult<Laboratory>>() {});		
		for (int i = 0; i < 5; i++) {
			assertEquals(laboratories.get(i).getId(), response.getItems().get(i).getId());
		}
	}

	@Test
	public void findAllWithManyPages() {

	}
	
	private Laboratory createSampleLaboratory() {
		com.xgh.xgh.laboratory.command.Laboratory laboratory = new com.xgh.xgh.laboratory.command.Laboratory();
		laboratory.register(new com.xgh.xgh.laboratory.command.LaboratoryId(), new Name("Laboratório dos Hackers"), new Phone("044313371337"));
		eventStore.push(laboratory);
		return new Laboratory(laboratory.getId().getValue(), laboratory.getCompanyName().getValue(), laboratory.getPhone().getValue());
	}
}
