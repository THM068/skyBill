package com.sky;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

/**
 * Created by tm1c14 on 13/05/2016.
 */
public class User {

    private int id = 56;
    private Name name ;

    public User(int id, Name name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    @JsonIgnoreType
    public static class Name {

        private String firstName = "Benny";
        private String lastName  = "Ambrose";

        public Name(String name, String lastName) {
            this.firstName = name;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
}
