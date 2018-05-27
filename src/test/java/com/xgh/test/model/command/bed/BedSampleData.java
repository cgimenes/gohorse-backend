package com.xgh.test.model.command.bed;

import com.xgh.buildingblocks.EventStore;
import com.xgh.model.command.bed.Bed;
import com.xgh.model.command.bed.BedId;
import com.xgh.model.command.valueobjects.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BedSampleData {
    @Autowired
    private EventStore eventStore;

    public Bed getSample() {
        Bed bed = new Bed();
        bed.register(new BedId(), new Code("123"), false);
        eventStore.push(bed);
        return bed;
    }
}
