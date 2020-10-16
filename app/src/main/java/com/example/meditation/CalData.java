package com.example.meditation;

import java.util.ArrayList;
import java.util.Calendar;

public class CalData {

    ArrayList<CalData> arrayList = new ArrayList<>();

    Calendar calendar;
    int year;
    int month;
    int day;
    int dayofweek;
    long time;
    int img;
    String title;
    String content;

    public CalData(int d, int h, int y, int m) {
        day = d;
        dayofweek = h;
        year = y;
        month = m;
    }

    public CalData(int y, int m, int d,int img,String title, String content) {
        year = y;
        month = m;
        day = d;
        this.img = img;
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public int getImg() {
        return img;
    }

    public String getContent() {
        return content;
    }

    public ArrayList<CalData> getArrayList() {
        return arrayList;
    }

    public int getDay() {
        return day;
    }

    public int getDayofweek() {
        return dayofweek;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public long getTime() {
        return time;
    }
}