package com.xgh.test.model.command.operational.enumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xgh.buildingblocks.EventStore;
import com.xgh.model.command.operational.enumerator.Enumerator;
import com.xgh.model.command.operational.enumerator.EnumeratorId;
import com.xgh.model.command.operational.valueobjects.Description;
import com.xgh.model.command.operational.valueobjects.Name;

@Component("CommandBedSampleData")
public class BedSampleData {
    @Autowired
    private EventStore eventStore;

    public Enumerator getSample() {
        Enumerator enumerator = new Enumerator();
        enumerator.register(
                new EnumeratorId(),
                new Name("Leito"),
                new Description("ABC1234"));
        eventStore.push(enumerator);
        return enumerator;
    }
}
