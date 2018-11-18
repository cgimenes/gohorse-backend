package com.xgh.test.model.command.operational.supplier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.operational.enumerator.Enumerator;
import com.xgh.model.command.operational.supplier.Supplier;
import com.xgh.model.command.operational.supplier.SupplierId;
import com.xgh.model.command.operational.valueobjects.Address;
import com.xgh.model.command.operational.valueobjects.Name;
import com.xgh.model.command.operational.valueobjects.Phone;
import com.xgh.model.command.operational.valueobjects.PostalCode;
import com.xgh.test.model.command.enumerator.DistributionTypeSampleData;

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

// TODO: comparar endereço
// TODO: criar teste de falha de bad request e entity not found
// TODO: verificar se os snapshots estão sendo salvos/deletados corretamente
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class SupplierCommandControllerTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostgresEventStore eventStore;
    
    @Autowired
    private DistributionTypeSampleData distTypeSampleData;
    
    @Autowired
    private SupplierSampleData supplierSampleData;
    
    @Test
    public void registerWithSuccess() {
    	Enumerator distributionType = distTypeSampleData.getSample();
    	
        Supplier entity = new Supplier();
        entity.register(new SupplierId(), new Name("Nestle"), new Phone("44998015821"), distributionType.getId(), "00000000191",
                new Address(new PostalCode("87005-140", "Rua", "Ruazera", "Barro", "Maringá", "PR", "Brasil"), 117,
                        null));

        ResponseEntity<Void> response = restTemplate.postForEntity("/suppliers", entity, Void.class);

        Supplier entityFromStore = eventStore.pull(Supplier.class, entity.getId());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(entity.equals(entityFromStore));
        assertEquals("Nestle", entityFromStore.getName().toString());
        assertEquals("44998015821", entityFromStore.getPhone().toString());
        assertEquals(distributionType.getId(), entityFromStore.getDistributionType());
        assertEquals("00000000191", entityFromStore.getDocument().toString());
        assertEquals("/suppliers/" + entity.getId(), response.getHeaders().getLocation().getPath());
        assertEquals("1", entityFromStore.getVersion().toString());
    }

    @Test
    public void updateWithSuccess() {
        Supplier entity = supplierSampleData.getSample();

        entity.update(entity.getName(), new Phone("44998731154"), entity.getDistributionType(), entity.getDocument(), entity.getAddress());

        RequestEntity<Supplier> request = RequestEntity.put(URI.create("/suppliers")).body(entity);
        ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

        Supplier entityFromStore = eventStore.pull(Supplier.class, entity.getId());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertTrue(entity.equals(entityFromStore));
        assertEquals("Mercadão de Maringá", entityFromStore.getName().toString());
        assertEquals("44998731154", entityFromStore.getPhone().toString());
        assertEquals(entity.getDistributionType(), entityFromStore.getDistributionType());
        assertEquals(entity.getDocument().toString(), entityFromStore.getDocument().toString());
        assertEquals(entity.getVersion().toString(), entityFromStore.getVersion().toString());
    }

    @Test
    public void deleteWithSuccess() {
        Supplier entity = supplierSampleData.getSample();

        eventStore.push(entity);

        HttpEntity<Supplier> requestEntity = new HttpEntity<>(entity);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(URI.create("/suppliers"), HttpMethod.DELETE,
                requestEntity, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        Supplier entityFromStore = eventStore.pull(Supplier.class, entity.getId());

        assertTrue(entityFromStore.isDeleted());
    }
}
