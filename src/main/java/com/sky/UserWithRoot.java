package com.sky;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Created by thandomafela on 11/05/2016.
 */
@JsonRootName(value = "user")
public class UserWithRoot {

    private String title = "Come get your love";
    private String music = "Rock Music";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }
}
