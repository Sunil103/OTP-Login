package com.example.task;

public class ScreenItems {
    String title,desc;
    int screenImg;

    public ScreenItems(String title, String desc, int screenImg) {
        this.title = title;
        this.desc = desc;
        this.screenImg = screenImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getScreenImg() {
        return screenImg;
    }

    public void setScreenImg(int screenImg) {
        this.screenImg = screenImg;
    }
}
