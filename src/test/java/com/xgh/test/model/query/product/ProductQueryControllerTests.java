package com.xgh.test.model.query.product;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xgh.model.query.product.Product;
import com.xgh.model.query.product.ProductRepository;
import com.xgh.test.model.query.Page;
import com.xgh.test.model.query.supplier.SupplierSampleData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@DirtiesContext(classMode=DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProductQueryControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepository repository;

    @Autowired
    private SupplierSampleData supplierSampleData;

    @Test
    public void findById() {
        UUID productId = createSampleEntity();

        ResponseEntity<Product> response = restTemplate.getForEntity("/products/{id}", Product.class,
                productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productId, response.getBody().getId());
        assertEquals("Produto top", response.getBody().getName());
        assertEquals(new Float(10.50), response.getBody().getPrice());
        assertEquals("Marca vagabunda", response.getBody().getBrand());
        assertEquals(new Float(2), response.getBody().getAmount());
    }

    @Test
    public void findAllWithOnePage() throws IOException {
        List<UUID> products = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            products.add(createSampleEntity());
        }

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/products", String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Page<Product> response = new ObjectMapper().findAndRegisterModules().readValue(
                responseEntity.getBody(), new TypeReference<Page<Product>>() {});
        for (int i = 0; i < 5; i++) {
            assertEquals(products.get(i), response.getContent().get(i).getId());
        }
    }

    private UUID createSampleEntity() {
        Product product = new Product(
                UUID.randomUUID(),
                "Produto top",
                10.50f,
                "Marca vagabunda",
                2f,
                supplierSampleData.getSample(),
                false);
        repository.save(product);
        return product.getId();
    }
}
