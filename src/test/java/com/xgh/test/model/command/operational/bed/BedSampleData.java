package com.xgh.test.model.command.operational.bed;

import com.xgh.buildingblocks.EventStore;
import com.xgh.model.command.operational.bed.Bed;
import com.xgh.model.command.operational.bed.BedId;
import com.xgh.model.command.operational.valueobjects.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BedSampleData {
    @Autowired
    private EventStore eventStore;

    public Bed getSample() {
        Bed bed = new Bed();
        bed.register(new BedId(), new Code("123"));
        eventStore.push(bed);
        return bed;
    }
}
