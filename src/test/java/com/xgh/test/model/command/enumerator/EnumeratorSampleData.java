package com.xgh.test.model.command.enumerator;

import com.xgh.buildingblocks.EventStore;
import com.xgh.model.command.operational.enumerator.Enumerator;
import com.xgh.model.command.operational.enumerator.EnumeratorId;
import com.xgh.model.command.operational.valueobjects.Name;
import com.xgh.model.command.operational.valueobjects.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("CommandEnumeratorSampleData")
public class EnumeratorSampleData {
    @Autowired
    private EventStore eventStore;

    public Enumerator getSample() {
        Enumerator enumerator = new Enumerator();
        enumerator.register(
                new EnumeratorId(),
                new Name("Raça"),
                new Description("XITZU"));
        eventStore.push(enumerator);
        return enumerator;
    }
}
