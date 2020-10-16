package com.example.meditation;

import java.util.ArrayList;

public class User {
    String ID;
    String Password;
    String img_uri_profile;
    String introduce;
    ArrayList<MeditationContents> play_list;

    public User(String ID, String password){
        this.ID = ID;
        this.Password = password;
    }

    public String getID() {
        return ID;
    }

    public ArrayList<MeditationContents> getPlay_list() {
        return play_list;
    }

    public String getImg_uri_profile() {
        return img_uri_profile;
    }

    public String getIntroduce() {
        return introduce;
    }

    public boolean UserCheck(String ID, String password){
        if(true){
            return true;
        }else {
            return false;
        }
    }

}
