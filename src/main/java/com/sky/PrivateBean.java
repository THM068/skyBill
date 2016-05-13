package com.sky;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * Created by tm1c14 on 13/05/2016.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PrivateBean {

    private int id;
    private String name;

    public PrivateBean(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
