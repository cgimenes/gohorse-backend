package com.xgh.test.model.query.operational.bed;

import com.xgh.model.query.operational.bed.Bed;
import com.xgh.model.query.operational.bed.BedRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("QueryBedSampleData")
public class BedSampleData {
    private final BedRepository repository;

    @Autowired
    protected BedSampleData(BedRepository bedRepository) {
        this.repository = bedRepository;
    }

    public Bed getSample() {
        Bed bed = new Bed(UUID.randomUUID(), "021", false);
        repository.save(bed);
        return bed;
    }
}
