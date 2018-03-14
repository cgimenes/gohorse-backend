package com.xgh.buildingblocks;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ItemSerializer extends StdSerializer<SingleValueObject<?>> {
	private static final long serialVersionUID = 6979510827050691371L;

	public ItemSerializer() {
        this(null);
    }
   
    public ItemSerializer(Class<SingleValueObject<?>> t) {
        super(t);
    }
 
    @Override
    public void serialize(
	  SingleValueObject<?> value, JsonGenerator jgen, SerializerProvider provider) 
      throws IOException, JsonProcessingException {
  
        jgen.writeRawValue(value.toString());
    }
}