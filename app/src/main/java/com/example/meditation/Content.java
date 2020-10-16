package com.example.meditation;

public class Content {
    String title;
    String content;
    int img;
    String img_url;
    int music;
    String music_url;

    public Content (String title,String content,String img_url,String music_url){
        this.title = title;
        this.content = content;
        this.img_url = img_url;
        this.music_url = music_url;
    }

    public Content (String title,String content,int img,int music){
        this.title = title;
        this.content = content;
        this.img = img;
        this.music = music;
    }

    public int getImg() {
        return img;
    }

    public int getMusic() {
        return music;
    }

    public String getContent() {
        return content;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getMusic_url() {
        return music_url;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setMusic(int music) {
        this.music = music;
    }

    public void setMusic_url(String music_url) {
        this.music_url = music_url;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
