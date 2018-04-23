package com.xgh.test.model.command.product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URI;

import com.xgh.model.command.supplier.Supplier;
import com.xgh.model.command.supplier.SupplierId;
import com.xgh.test.model.command.supplier.SupplierSampleData;
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
import com.xgh.model.command.product.Product;
import com.xgh.model.command.product.ProductId;
import com.xgh.model.command.valueobjects.Name;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class ProductCommandControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostgresEventStore eventStore;

    @Autowired
    private SupplierSampleData supplierSampleData;

    @Test
    public void registerWithSuccess() {
        Product entity = new Product();
        Supplier supplier = supplierSampleData.getSample();
        entity.register(new ProductId(), new Name("Produto bom"), 10.50f, new Name("Marca vagabunda"), 2f, supplier.getId());

        ResponseEntity<Void> response = restTemplate.postForEntity("/products", entity, Void.class);

        Product entityFromStore = eventStore.pull(Product.class, entity.getId());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("/products/" + entity.getId(), response.getHeaders().getLocation().getPath());
        assertTrue(entity.equals(entityFromStore));
        assertEquals("Produto bom", entity.getName().toString());
        assertEquals(new Float(10.50), entity.getPrice());
        assertEquals("Marca vagabunda", entity.getBrand().toString());
        assertEquals(new Float(2), entity.getAmount());
        assertEquals(supplier.getId().getValue(), entity.getSupplierId().getValue());
        assertEquals("1", entityFromStore.getVersion().toString());
    }

    @Test
    public void updateWithSuccess() {
        Product entity = new Product();
        Supplier supplier = supplierSampleData.getSample();
        entity.register(new ProductId(), new Name("Produto bom"), 10.50f, new Name("Marca vagabunda"), 2f, supplier.getId());
        eventStore.push(entity);

        supplier = supplierSampleData.getSample();
        entity.update(new Name("Produto ruim"), 2.50f, new Name("Marca top"), supplier.getId());

        RequestEntity<Product> request = RequestEntity.put(URI.create("/products")).body(entity);
        ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

        Product entityFromStore = eventStore.pull(Product.class, entity.getId());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertTrue(entity.equals(entityFromStore));
        assertEquals("Produto ruim", entity.getName().toString());
        assertEquals(new Float(2.50), entity.getPrice());
        assertEquals("Marca top", entity.getBrand().toString());
        assertEquals(supplier.getId().getValue(), entity.getSupplierId().getValue());
        assertEquals("2", entityFromStore.getVersion().toString());
    }

    @Test
    public void deleteWithSuccess() {
        Product entity = new Product();
        entity.register(new ProductId(), new Name("Produto bom"), 10.50f, new Name("Marca vagabunda"), 2f, new SupplierId());
        eventStore.push(entity);

        HttpEntity<Product> requestEntity = new HttpEntity<>(entity);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(URI.create("/products"), HttpMethod.DELETE,
                requestEntity, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        Product entityFromStore = eventStore.pull(Product.class, entity.getId());

        assertTrue(entityFromStore.isDeleted());
    }
}
