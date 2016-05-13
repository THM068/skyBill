package com.sky;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by tm1c14 on 13/05/2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BeanWithJsonInclude {

    private int id;
    private String name;

    public BeanWithJsonInclude(int id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
