package com.xgh.xgh;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.laboratory.commandmodel.Laboratory;
import com.xgh.xgh.laboratory.commandmodel.LaboratoryId;
import com.xgh.xgh.laboratory.commandmodel.LaboratorySnapshotStore;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LaboratoryCommandControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private LaboratorySnapshotStore repository;
	
//	@Autowired
//	private PostgresEventStore<Laboratory> eventStore;

	@Before
	public void before() {
		repository.deleteAll();
	}

	@Test
	public void register() {
		Laboratory laboratory = new Laboratory();
		laboratory.register(new LaboratoryId(), new Name("Laboratório dos Hackers"), new Phone("044313371337"));

		ResponseEntity<Laboratory> response = restTemplate.postForEntity("/laboratory", laboratory, Laboratory.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
//		assertEquals(UUID.fromString(response.getBody().getId()).toString(), response.getBody().getId());
		assertTrue(repository.existsById(laboratory.getId()));
		
	}
}
