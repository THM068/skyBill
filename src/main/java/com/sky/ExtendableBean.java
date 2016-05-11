package com.sky;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by thandomafela on 10/05/2016.
 */
@JsonPropertyOrder({"myBloodType", "aae","name","age"})
public class ExtendableBean {

    private String name = "Fikile Moyo";
    private TypeEnumWithValue bloodType = TypeEnumWithValue.TYPE2;

    private UserWithRoot userWithRoot = new UserWithRoot();

    private String json = "{ \"color\": \"brow\"}";
    private int age = 34;
    private int aae = 34;

    private Map<String, String> properties = new HashMap<>();

    @JsonGetter("user")
    public UserWithRoot getUserWithRoot() {
        return this.userWithRoot;
    }

    @JsonRawValue
    public String getJson() {
        return this.json;
    }

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }

    @JsonGetter("name")
    public String getFlipinName() {
        return this.name;
    }

    @JsonGetter("myBloodType")
    public TypeEnumWithValue getBloodType() {
        return this.bloodType;
    }
    public int getAge() {
        return this.age;
    }

    public int getAae() {
        return this.aae;
    }
}
