package com.xgh.test.model.command.enumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xgh.buildingblocks.EventStore;
import com.xgh.model.command.operational.enumerator.Enumerator;
import com.xgh.model.command.operational.enumerator.EnumeratorId;
import com.xgh.model.command.operational.valueobjects.Description;
import com.xgh.model.command.operational.valueobjects.Name;

@Component("CommandDistributionTypeSampleData")
public class DistributionTypeSampleData {
	@Autowired
    private EventStore eventStore;

    public Enumerator getSample() {
        Enumerator enumerator = new Enumerator();
        enumerator.register(
                new EnumeratorId(),
                new Name("Tipo de Distribuição"),
                new Description("Atacado"));
        eventStore.push(enumerator);
        return enumerator;
    }
}
