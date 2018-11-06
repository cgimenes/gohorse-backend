package com.xgh.test.model.command.operational.owner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.operational.owner.Owner;
import com.xgh.model.command.operational.owner.OwnerId;
import com.xgh.model.command.operational.valueobjects.Address;
import com.xgh.model.command.operational.valueobjects.Cpf;
import com.xgh.model.command.operational.valueobjects.Email;
import com.xgh.model.command.operational.valueobjects.Name;
import com.xgh.model.command.operational.valueobjects.Phone;
import com.xgh.model.command.operational.valueobjects.PostalCode;
import java.net.URI;
import java.time.LocalDate;
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
public class OwnerCommandControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostgresEventStore eventStore;

    @Test
    public void register() {
        Owner owner = new Owner();
        Address address = new Address(new PostalCode("87024-360", "Rua", "Garimpo", "Jardim Diamante", "Maringá", "PR", "Brasil"),
                389, null);

        owner.register(new OwnerId(), new Name("Dono Master"), new Phone("44313371337"), new Cpf("00000000191"),
                LocalDate.of(1001, 01, 01), address, new Email("requena.re@hotmail.com"));

        ResponseEntity<Void> response = restTemplate.postForEntity("/owners", owner, Void.class);

        Owner ownerFromStore = eventStore.pull(Owner.class, owner.getId());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("/owners/" + owner.getId(), response.getHeaders().getLocation().getPath());
        assertTrue(owner.equals(ownerFromStore));
        assertEquals("Dono Master", ownerFromStore.getName().toString());
        assertEquals("44313371337", ownerFromStore.getPhone().toString());
        assertEquals("00000000191", ownerFromStore.getDocument().toString());
        assertEquals(LocalDate.of(1001, 01, 01), ownerFromStore.getBirthDate());
        assertEquals("1", ownerFromStore.getVersion().toString());
    }

    @Test
    public void update() {
        Owner owner = new Owner();
        Email ownerEmail = new Email("requena.re@hotmail.com");
        Address address = new Address(new PostalCode("87024-360", "Rua", "Das gaivotas", "Jardim dos Passaros", "Maringá", "PR", "Brasil"),
                389, null);

        owner.register(new OwnerId(), new Name("Dono Master"), new Phone("44313371337"), new Cpf("09090385975"),
                LocalDate.of(1001, 01, 01), address, ownerEmail);
        eventStore.push(owner);

        owner.update(new Name("Dono Master"), new Phone("44000000000"), new Cpf("00000000191"), LocalDate.of(1002, 02, 02), address, ownerEmail);

        RequestEntity<Owner> request = RequestEntity.put(URI.create("/owners")).body(owner);
        ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

        Owner ownerFromStore = eventStore.pull(Owner.class, owner.getId());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertTrue(owner.equals(ownerFromStore));
        assertEquals("Dono Master", ownerFromStore.getName().toString());
        assertEquals("44000000000", ownerFromStore.getPhone().toString());
        assertEquals("00000000191", ownerFromStore.getDocument().toString());
        assertEquals(LocalDate.of(1002, 02, 02), ownerFromStore.getBirthDate());
        assertEquals("2", ownerFromStore.getVersion().toString());
    }

    @Test
    public void deleteWithSuccess() {
        Owner owner = new Owner();
        LocalDate data = LocalDate.of(1802, 02, 02);
        Address address = new Address(new PostalCode("11111-222", "Rua", "Das gaivotas", "Jardim dos Passaros", "Maringá", "PR", "Brasil"),
                389, null);

        owner.register(new OwnerId(), new Name("Dono Master"), new Phone("44313371337"), new Cpf("00000000191"), data,
                address, new Email("requena.re@hotmail.com"));
        eventStore.push(owner);

        HttpEntity<Owner> requestEntity = new HttpEntity<>(owner);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(URI.create("/owners"), HttpMethod.DELETE,
                requestEntity, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        Owner entityFromStore = eventStore.pull(Owner.class, owner.getId());

        assertTrue(entityFromStore.isDeleted());
    }

}
