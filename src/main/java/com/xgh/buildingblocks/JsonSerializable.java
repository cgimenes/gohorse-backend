package com.xgh.buildingblocks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xgh.ApplicationContextProvider;

public interface JsonSerializable {
    /*
     * Serializa o value object em JSON
     */
    default String toJson() {
        ObjectMapper mapper = ApplicationContextProvider.getApplicationContext().getBean(ObjectMapper.class);
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(String.format(
                    "Falha ao serializar objeto do tipo: %s", this.getClass().getName()), e);
        }
    }
}
