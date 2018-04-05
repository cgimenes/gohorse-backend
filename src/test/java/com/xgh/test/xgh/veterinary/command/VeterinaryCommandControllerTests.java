package com.xgh.test.xgh.veterinary.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URI;
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
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.valueobjects.Crmv;
import com.xgh.valueobjects.Mail;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.veterinary.command.Veterinary;
import com.xgh.xgh.veterinary.command.VeterinaryId;

// TODO: criar teste de falha de bad request e entity not found
// TODO: verificar se os snapshots estão sendo salvos/deletados corretamente
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")

public class VeterinaryCommandControllerTests {
	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private PostgresEventStore<Veterinary, VeterinaryId> eventStore;

	@Before
	private void before() {

	}

	@Test
	private void registerWithSuccess() throws ParseException {
		Veterinary veterinary = new Veterinary();
		veterinary.register(new VeterinaryId(), new Name("Ricardo Requena"), new Phone("044998015821"),
				new Crmv("9375"), new Mail("espacoanimal.vet@hotmail.com"),
				new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1986-10-03").getTime()), true);

		ResponseEntity<Void> response = restTemplate.postForEntity("/veterinarians", veterinary, Void.class);

		Veterinary veterinaryFromStore = eventStore.pull(Veterinary.class, veterinary.getId());

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertTrue(veterinary.equals(veterinaryFromStore));
		assertTrue(veterinaryFromStore.isActive());
		assertEquals("Ricardo Requena", veterinaryFromStore.getName().toString());
		assertEquals("044998015821", veterinaryFromStore.getPhone().toString());
		assertEquals("9375", veterinaryFromStore.getCrmv().toString());
		assertEquals("espacoanimal.vet@hotmail.com", veterinaryFromStore.getMail().toString());
		assertEquals("1", veterinaryFromStore.getVersion().toString());
		assertEquals("/veterinarians/" + veterinary.getId(), response.getHeaders().getLocation().getPath());
	}

	@Test
	private void updateWithSuccess() throws ParseException {
		Veterinary veterinary = new Veterinary();
		veterinary.register(new VeterinaryId(), new Name("Ricardo Requena"), new Phone("044998015821"),
				new Crmv("9375"), new Mail("espacoanimal.vet@hotmail.com"),
				new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1986-10-03").getTime()), true);
		eventStore.push(veterinary);

		veterinary.update(veterinary.getName(), new Phone("044998731154"), veterinary.getCrmv(),
				new Mail("ricardo.requena@hotmail.com"),
				new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1986-10-03").getTime()), true);
		
		RequestEntity<Veterinary> request = RequestEntity.put(URI.create("/veterinarians")).body(veterinary);
		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);
		
		Veterinary veterinaryFromStore = eventStore.pull(Veterinary.class, veterinary.getId());
		
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		assertTrue(veterinary.equals(veterinaryFromStore));
		assertEquals("Ricardo Requena", veterinaryFromStore.getName().toString());
		assertEquals("044998731154", veterinaryFromStore.getPhone().toString());
		assertEquals("9375", veterinaryFromStore.getCrmv().toString());
		assertEquals("ricardo.requena@hotmail.com", veterinaryFromStore.getMail().toString());
		assertEquals("2", veterinaryFromStore.getVersion().toString());

	}

	// @Test
	// private void registerBadRequest() throws ParseException {
	// Veterinary veterinary = new Veterinary();
	// veterinary.register(new VeterinaryId(), new Name("Ricardo Requena"), new
	// Phone("044998015821"),
	// new Crmv("9375"), new Mail("espacoanimal.vet@hotmail.com"),
	// new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1986-10-03").getTime()),
	// true);
	//
	// ResponseEntity<Void> response = restTemplate.postForEntity("/veterinarians",
	// veterinary, Void.class);
	//
	// Veterinary veterinaryFromStore = eventStore.pull(Veterinary.class,
	// veterinary.getId());
	//
	// assertEquals(HttpStatus.CREATED, response.getStatusCode());
	// assertTrue(veterinary.equals(veterinaryFromStore));
	// assertEquals("Ricardo Requena", veterinaryFromStore.getName().toString());
	// assertEquals("044998015821", veterinaryFromStore.getPhone().toString());
	// assertEquals("9375", veterinaryFromStore.getCrmv().toString());
	// assertEquals("espacoanimal.vet@hotmail.com",
	// veterinaryFromStore.getMail().toString());
	// assertTrue(veterinaryFromStore.isActive());
	// assertEquals("1", veterinaryFromStore.getVersion().toString());
	// assertEquals("/veterinarians/" + veterinary.getId(),
	// response.getHeaders().getLocation().getPath());
	// }

}