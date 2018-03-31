package com.xgh.xgh.laboratory.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.URI;

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
import org.springframework.test.context.junit4.SpringRunner;

import com.xgh.exceptions.DeletedEntityException;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.laboratory.command.Laboratory;
import com.xgh.xgh.laboratory.command.LaboratoryId;

// TODO: criar teste de falha de bad request e entity not found
// TODO: verificar se os snapshots estão sendo salvos/deletados corretamente
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LaboratoryCommandControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private PostgresEventStore<Laboratory, LaboratoryId> eventStore; 

	@Before
	public void before() {
	}

	@Test
	public void register() {
		Laboratory laboratory = new Laboratory();
		laboratory.register(new LaboratoryId(), new Name("Laboratório dos Hackers"), new Phone("044313371337"));

		ResponseEntity<Laboratory> response = restTemplate.postForEntity("/laboratories", laboratory, Laboratory.class);

		Laboratory labFromStore = eventStore.pull(Laboratory.class, laboratory.getId());
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("/laboratories/" + laboratory.getId(), response.getHeaders().getLocation().getPath());
		assertTrue(laboratory.equals(labFromStore));		
		assertEquals("Laboratório dos Hackers", labFromStore.getCompanyName().toString());
		assertEquals("044313371337", labFromStore.getPhone().toString());
		assertEquals("1", labFromStore.getVersion().toString());
	}

	@Test
	public void update() {
		Laboratory laboratory = new Laboratory();
		laboratory.register(new LaboratoryId(), new Name("Laboratório dos Hackers"), new Phone("044313371337"));
		eventStore.push(laboratory);
		
		laboratory.update(new Name("Laboratório dos Noob"), new Phone("044000000000"));
		
		RequestEntity<Laboratory> request = RequestEntity.put(URI.create("/laboratories")).body(laboratory);
		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		Laboratory labFromStore = eventStore.pull(Laboratory.class, laboratory.getId());
		
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		assertTrue(laboratory.equals(labFromStore));		
		assertEquals("Laboratório dos Noob", labFromStore.getCompanyName().toString());
		assertEquals("044000000000", labFromStore.getPhone().toString());
		assertEquals("2", labFromStore.getVersion().toString());
	}

	@Test
	public void delete() {
		Laboratory laboratory = new Laboratory();
		laboratory.register(new LaboratoryId(), new Name("Laboratório dos Hackers"), new Phone("044313371337"));
		eventStore.push(laboratory);
		
        HttpEntity<Laboratory> requestEntity = new HttpEntity<Laboratory>(laboratory);
	        
        ResponseEntity<Void> responseEntity = restTemplate.exchange(URI.create("/laboratories"), HttpMethod.DELETE, requestEntity, Void.class);

        Laboratory labFromStore = null;
        try {
    		labFromStore = eventStore.pull(Laboratory.class, laboratory.getId());
    		fail();
        } catch (DeletedEntityException e) {
        }
		
		assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
		assertNull(labFromStore);
	}
}
