package com.xgh.test.xgh.veterinary.query;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	private PostgresEventStore<com.xgh.xgh.veterinary.command.Veterinary, com.xgh.xgh.veterinary.command.VeterinaryId> eventStore;

	@Autowired
	protected JdbcTemplate connection;

	@Before
	public void before() {
		connection.update("truncate table veterinary");
	}

	@Test
	public void findById() throws ParseException {
		Veterinary veterinary = createSampleVeterinary();

		ResponseEntity<Veterinary> response = restTemplate.getForEntity("/veterinarians/{id}", Veterinary.class,
				veterinary.getId());

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(veterinary.getId(), response.getBody().getId());
		assertEquals(veterinary.getName().toString(), response.getBody().getName().toString());
		assertEquals(veterinary.getPhone().toString(), response.getBody().getPhone().toString());
		assertEquals(veterinary.getCrmv().toString(), response.getBody().getCrmv().toString());
		assertEquals(veterinary.getEmail().toString(), response.getBody().getEmail().toString());
		assertEquals(veterinary.getBirthDate().toString(), response.getBody().getBirthDate().toString());
	}
	
	@Test
	public void findAllWithManyPages() throws ParseException {
		
	}

	private Veterinary createSampleVeterinary() throws ParseException {
		com.xgh.xgh.veterinary.command.Veterinary veterinary = new com.xgh.xgh.veterinary.command.Veterinary();
		veterinary.register(new com.xgh.xgh.veterinary.command.VeterinaryId(), new Name("Ricardo Requena"),
				new Phone("044998015821"), new Crmv("9375"), new Email("espacoanimal.vet@hotmail.com"),
				new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1986-10-03").getTime()));
		eventStore.push(veterinary);
		return new Veterinary(veterinary.getId().getValue(), veterinary.getName().getValue(),
				veterinary.getPhone().getValue(), veterinary.getCrmv().getValue(), veterinary.getEmail().getValue(),
				veterinary.getBirthDate().toString());
	}
}