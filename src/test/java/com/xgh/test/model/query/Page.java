package com.xgh.test.model.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Page<T> {
    private final List<T> content = new ArrayList<>();

    protected Page() {
    }

    public List<T> getContent() {
        return content;
    }
}
