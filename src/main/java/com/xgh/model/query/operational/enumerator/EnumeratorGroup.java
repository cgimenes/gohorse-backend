package com.xgh.model.query.operational.enumerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public final class EnumeratorGroup {

    private String name;

    private ArrayList<Enumerator> enumerators = new ArrayList<Enumerator>();
    
    @JsonIgnore
    private Boolean deleted = false;

    protected EnumeratorGroup() {
    }

    public EnumeratorGroup(String name, Enumerator enumerator) {
    	this.name = name;
    	this.enumerators.add(enumerator);    	
    }
    
    public void addEnumerator( Enumerator enumerator ) {
    	enumerators.add(enumerator);
    }

    public ArrayList<Enumerator> getEnumerators() {
    	return this.enumerators;
    }

    public String getName() {
        return this.name;
    }
}
