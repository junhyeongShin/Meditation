package com.example.meditation;

import java.util.ArrayList;

public class MeditationContents {
    int img;
    String title;
    String text;
    User user;
    int media;
    String category;
    ArrayList<MeditationContents> arrayList = new ArrayList<>();

    public MeditationContents(int img, String title, String text, User user,
                              int media, String category)
    {
        this.img = img;
        this.title = title;
        this.text = text;
        this.user =user;
        this.media = media;
        this.category = category;
    }

    void init(){

        MeditationContents meditationContents1 = new MeditationContents(R.drawable.book,"book","book_text",
                user,R.raw.coldblue,"meditation");
        arrayList.add(meditationContents1);
        MeditationContents meditationContents2 = new MeditationContents(R.drawable.candle,"candle","candle_text",
                user,R.raw.enviroment,"meditation");
        arrayList.add(meditationContents2);
        MeditationContents meditationContents3 = new MeditationContents(R.drawable.milkyway,"milkyway","milkyway_text",
                user,R.raw.etenalgarden,"music");
        arrayList.add(meditationContents3);
        MeditationContents meditationContents4 = new MeditationContents(R.drawable.heart_empty,"heart","heart_text",
                user,R.raw.nebularfocus,"music");
        arrayList.add(meditationContents4);
        MeditationContents meditationContents5 = new MeditationContents(R.drawable.background_sky_gif,"sky","sky_text",
                user,R.raw.coldblue,"meditation");
        arrayList.add(meditationContents5);

        MeditationContents meditationContents6 = new MeditationContents(R.drawable.book,"book","book_text",
                user,R.raw.coldblue,"meditation");
        arrayList.add(meditationContents6);
        MeditationContents meditationContents7 = new MeditationContents(R.drawable.candle,"candle","candle_text",
                user,R.raw.enviroment,"meditation");
        arrayList.add(meditationContents7);
        MeditationContents meditationContents8 = new MeditationContents(R.drawable.milkyway,"milkyway","milkyway_text",
                user,R.raw.etenalgarden,"music");
        arrayList.add(meditationContents8);
        MeditationContents meditationContents9 = new MeditationContents(R.drawable.heart_empty,"heart","heart_text",
                user,R.raw.nebularfocus,"music");
        arrayList.add(meditationContents9);
        MeditationContents meditationContents10 = new MeditationContents(R.drawable.background_sky_gif,"sky","sky_text",
                user,R.raw.coldblue,"meditation");
        arrayList.add(meditationContents10);
    }


}
