package com.sky;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by tm1c14 on 13/05/2016.
 */
@JsonIgnoreProperties({"id"})
public class BeanWithIgnore {

    private int id = 45;
    private String name = "Kerrie Channer";

    @JsonIgnore
    private String eyeColor = "Blue eyes";

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

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }
}
