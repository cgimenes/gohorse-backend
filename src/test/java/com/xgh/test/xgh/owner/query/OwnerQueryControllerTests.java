package com.xgh.test.xgh.owner.query;

import static org.junit.Assert.*;

import java.io.IOException;
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
import com.xgh.valueobjects.Cpf;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.owner.command.Owner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class OwnerQueryControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	protected JdbcTemplate connection;

	@Autowired
	private PostgresEventStore eventStore;

	@Before
	public void before() {
		connection.update("truncate table owner");
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void findById() {
		UUID ownerId = createSampleEntity();
		
		ResponseEntity<Owner> response = restTemplate.getForEntity("/owners/{id}", Owner.class,
				ownerId);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ownerId, response.getBody().getId());		
		assertEquals("Dono Master", response.getBody().getName().toString());
		assertEquals("044313371337", response.getBody().getPhone().toString());
		assertEquals("09450600929", response.getBody().getCpf().toString());
		assertEquals(new Date("01/01/2050"), response.getBody().getBirthDate());
	}
	
	@Test
	public void findAllWithOnePage() throws JsonParseException, JsonMappingException, IOException {
		List<UUID> owners = new ArrayList<>(); 
		for (int i = 0; i < 5; i++) {
			owners.add(createSampleEntity());
		}

		ResponseEntity<String> responseEntity = restTemplate.getForEntity("/owners", String.class);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
		Page<Owner> response = new ObjectMapper().readValue(responseEntity.getBody(), new TypeReference<Page<Owner>>() {});		
		for (int i = 0; i < 5; i++) {
			assertEquals(owners.get(i), response.getContent().get(i).getId());
		}
	}
	
	@SuppressWarnings("deprecation")
	private UUID createSampleEntity() {
		com.xgh.xgh.owner.command.Owner owner = new com.xgh.xgh.owner.command.Owner();
		owner.register(new com.xgh.xgh.owner.command.OwnerId(), new Name("Dono Master"), new Phone("044313371337"), new Cpf("09450600929"), new Date("01/01/2050"));
		eventStore.push(owner);
		return owner.getId().getValue();
	}
	
}
