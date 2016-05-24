package com.sky;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thandomafela on 15/05/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserListWrapper {

    private List<UserWrapper> userList = new ArrayList<UserWrapper>();

    public List<UserWrapper> getUserList() {
        return userList;
    }

    public void setUserList(List<UserWrapper> userList) {
        this.userList = userList;
    }
}
