package com.xgh.test.model.command.bed;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.bed.Bed;
import com.xgh.model.command.bed.BedId;
import com.xgh.model.command.valueobjects.Code;
import java.net.URI;
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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class BedCommandControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostgresEventStore eventStore;

    @Test
    public void register() {
        Bed bed = new Bed();
        bed.register(new BedId(), new Code("021"));

        ResponseEntity<Void> response = restTemplate.postForEntity("/bed", bed, Void.class);

        Bed bedFromStore = eventStore.pull(Bed.class, bed.getId());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("/bed/" + bed.getId(), response.getHeaders().getLocation().getPath());
        assertTrue(bed.equals(bedFromStore));
        assertEquals("021", bedFromStore.getCode().toString());
        assertEquals("1", bedFromStore.getVersion().toString());
    }

    @Test
    public void update() {
        Bed bed = new Bed();

        bed.register(new BedId(), new Code("021"));
        eventStore.push(bed);

        bed.update(new Code("022"));

        RequestEntity<Bed> request = RequestEntity.put(URI.create("/bed")).body(bed);
        ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

        Bed bedFromStore = eventStore.pull(Bed.class, bed.getId());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertTrue(bed.equals(bedFromStore));
        assertEquals("022", bedFromStore.getCode().toString());
        assertEquals("2", bedFromStore.getVersion().toString());
    }

    @Test
    public void delete() {
        Bed bed = new Bed();

        bed.register(new BedId(), new Code("023"));
        eventStore.push(bed);

        HttpEntity<Bed> requestEntity = new HttpEntity<Bed>(bed);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(URI.create("/bed"), HttpMethod.DELETE,
                requestEntity, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        Bed entityFromStore = eventStore.pull(Bed.class, bed.getId());

        assertTrue(entityFromStore.isDeleted());
    }
}
