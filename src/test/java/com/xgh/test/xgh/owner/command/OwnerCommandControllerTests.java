package com.xgh.test.xgh.owner.command;

import static org.junit.Assert.*;

import java.net.URI;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.valueobjects.Cpf;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.owner.command.Owner;
import com.xgh.xgh.owner.command.OwnerId;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class OwnerCommandControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private PostgresEventStore eventStore; 

	@Before
	public void before() {
	}

	@Test
	public void register() {
		Owner owner = new Owner();
		Date data = new Date();
		owner.register(new OwnerId(), new Name("Dono Master"), new Phone("044313371337"), new Cpf("09450600929"), data);

		ResponseEntity<Void> response = restTemplate.postForEntity("/owners", owner, Void.class);

		Owner ownerFromStore = eventStore.pull(Owner.class, owner.getId());
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("/owners/" + owner.getId(), response.getHeaders().getLocation().getPath());
		assertTrue(owner.equals(ownerFromStore));		
		assertEquals("Dono Master", ownerFromStore.getName().toString());
		assertEquals("044313371337", ownerFromStore.getPhone().toString());
		assertEquals("09450600929", ownerFromStore.getCpf().toString());
		assertEquals(data, ownerFromStore.getBirthDate());
		assertEquals("1", ownerFromStore.getVersion().toString());
	}

	@Test
	public void update() {
		Owner owner = new Owner();
		Date data = new Date();
		owner.register(new OwnerId(), new Name("Dono Master"), new Phone("044313371337"), new Cpf("09450600929"), data);
		eventStore.push(owner);
		
		owner.update(new Name("Dono Master"), new Phone("044000000000"), new Cpf("09450600929"), data);
		
		RequestEntity<Owner> request = RequestEntity.put(URI.create("/owners")).body(owner);
		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		Owner ownerFromStore = eventStore.pull(Owner.class, owner.getId());
		
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		assertTrue(owner.equals(ownerFromStore));		
		assertEquals("Dono Master", ownerFromStore.getName().toString());
		assertEquals("044000000000", ownerFromStore.getPhone().toString());
		assertEquals("09450600929", ownerFromStore.getCpf().toString());
		assertEquals(data, ownerFromStore.getBirthDate());
		assertEquals("2", ownerFromStore.getVersion().toString());
	}
	
	@Test
	public void deleteWithSuccess() {
		Owner owner = new Owner();
		Date data = new Date();
		owner.register(new OwnerId(), new Name("Dono Master"), new Phone("044313371337"), new Cpf("09450600929"), data);
		eventStore.push(owner);

		HttpEntity<Owner> requestEntity = new HttpEntity<Owner>(owner);

		ResponseEntity<Void> responseEntity = restTemplate.exchange(URI.create("/owners"), HttpMethod.DELETE,
				requestEntity, Void.class);

		assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

		Owner entityFromStore = eventStore.pull(Owner.class, owner.getId());
		
		assertTrue(entityFromStore.isDeleted());
	}

}
