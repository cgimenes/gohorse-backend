package com.xgh.xgh.owner.infra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.xgh.infra.PostgresEventStore;
import com.xgh.valueobjects.Cpf;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.owner.commandmodel.OwnerId;
import com.xgh.xgh.owner.commandmodel.Owner;

// TODO: criar teste de falha de bad request e entity not found
// TODO: verificar se os snapshots estão sendo salvos corretamente
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ComponentScan("com.xgh")
public class OwnerCommandControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private PostgresEventStore<Owner, OwnerId> eventStore; 

	@Before
	public void before() {
	}

	@Test
	public void register() {
		Owner owner = new Owner();
		owner.register(new OwnerId(), new Name("Dono Master"), new Phone("044313371337"), new Cpf("09450600929"));

		ResponseEntity<Owner> response = restTemplate.postForEntity("/owners", owner, Owner.class);

		Owner ownerFromStore = eventStore.pull(Owner.class, owner.getId());
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("/owners/" + owner.getId(), response.getHeaders().getLocation().getPath());
		assertTrue(owner.equals(ownerFromStore));		
		assertEquals("Dono Master", ownerFromStore.getName().toString());
		assertEquals("044313371337", ownerFromStore.getPhone().toString());
		assertEquals("09450600929", ownerFromStore.getCpf().toString());
		assertEquals("1", ownerFromStore.getVersion().toString());
	}

	@Test
	public void update() {
		Owner owner = new Owner();
		owner.register(new OwnerId(), new Name("Dono Master"), new Phone("044313371337"), new Cpf("09450600929"));
		eventStore.push(owner);
		
		owner.update(new Name("Dono Master"), new Phone("044000000000"), new Cpf("09450600929"));
		
		RequestEntity<Owner> request = RequestEntity.put(URI.create("/owners")).body(owner);
		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		Owner ownerFromStore = eventStore.pull(Owner.class, owner.getId());
		
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		assertTrue(owner.equals(ownerFromStore));		
		assertEquals("Dono Master", ownerFromStore.getName().toString());
		assertEquals("044000000000", ownerFromStore.getPhone().toString());
		assertEquals("09450600929", ownerFromStore.getCpf().toString());
		assertEquals("2", ownerFromStore.getVersion().toString());
	}
}
