package com.xgh.test.xgh.veterinary.query;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.valueobjects.Crmv;
import com.xgh.valueobjects.Email;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.veterinary.query.Veterinary;

// TODO: criar teste de falha de bad request e entity not found
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class VeterinaryQueryControllerTests {
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private PostgresEventStore eventStore;

	@Autowired
	protected JdbcTemplate connection;

	@Before
	public void before() {
		connection.update("truncate table veterinary");
	}

	@Test
	public void findById() throws ParseException {
		UUID veterinaryId = createSampleEntity();

		ResponseEntity<Veterinary> response = restTemplate.getForEntity("/veterinarians/{id}", Veterinary.class,
				veterinaryId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(veterinaryId, response.getBody().getId());
		assertEquals("Ricardo Requena", response.getBody().getName().toString());
		assertEquals("044998015821", response.getBody().getPhone().toString());
		assertEquals("9375", response.getBody().getCrmv().toString());
		assertEquals("espacoanimal.vet@hotmail.com", response.getBody().getEmail().toString());
		assertEquals("1986-10-03", response.getBody().getBirthDate().toString());
	}

	// TODO corrigir
	@Test
	public void findAllWithOnePage() throws JsonParseException, JsonMappingException, IOException, ParseException {
		List<UUID> veterinarians = new ArrayList<>(); 
		for (int i = 0; i < 5; i++) {
			veterinarians.add(createSampleEntity());
		}

		ResponseEntity<String> responseEntity = restTemplate.getForEntity("/veterinarians", String.class);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
		Page<Veterinary> response = new ObjectMapper().readValue(responseEntity.getBody(), new TypeReference<Page<Veterinary>>() {});		
		for (int i = 0; i < 5; i++) {
			assertEquals(veterinarians.get(i), response.getContent().get(i).getId());
		}
	}

	@Test
	public void findAllWithManyPages() throws ParseException {
		// TODO criar
	}

	private UUID createSampleEntity() throws ParseException {
		com.xgh.xgh.veterinary.command.Veterinary veterinary = new com.xgh.xgh.veterinary.command.Veterinary();
		veterinary.register(new com.xgh.xgh.veterinary.command.VeterinaryId(), new Name("Ricardo Requena"),
				new Phone("044998015821"), new Crmv("9375"), new Email("espacoanimal.vet@hotmail.com"),
				new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1986-10-03").getTime()));
		eventStore.push(veterinary);
		return veterinary.getId().getValue();
	}
}