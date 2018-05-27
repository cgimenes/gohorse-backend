package com.xgh.test.model.query.bed;

import com.xgh.model.query.bed.Bed;
import com.xgh.model.query.bed.BedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("QueryBedSampleData")
public class BedSampleData {
    private final BedRepository repository;

    @Autowired
    protected BedSampleData(BedRepository bedRepository) {
        this.repository = bedRepository;
    }

    public Bed getSample() {
        Bed bed = new Bed(UUID.randomUUID(), "021", true, false);
        repository.save(bed);
        return bed;
    }
}
