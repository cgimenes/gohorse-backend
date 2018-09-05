package com.xgh.test.model.query.veterinary;

import com.xgh.model.query.veterinary.Veterinary;
import com.xgh.model.query.veterinary.VeterinaryRepository;
import com.xgh.test.model.query.address.AddressSampleData;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("QueryVeterinarySampleData")
public class VeterinarySampleData {
    private final VeterinaryRepository repository;
    private final AddressSampleData addressSampleData;

    @Autowired
    protected VeterinarySampleData(VeterinaryRepository repository, AddressSampleData addressSampleData) {
        this.repository = repository;
        this.addressSampleData = addressSampleData;
    }

    public Veterinary getSample() {
        Veterinary entity = new Veterinary(
                UUID.randomUUID(),
                "Farelo",
                addressSampleData.getSample(),
                "444444444",
                "12345",
                "teste@teste.com",
                LocalDate.of(2011,10,10),
                false);
        repository.save(entity);
        return entity;
    }
}
