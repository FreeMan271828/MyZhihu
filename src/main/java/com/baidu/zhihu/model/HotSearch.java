package com.baidu.zhihu.model;

public class HotSearch {

    private String id;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HotSearch(String title) {
        this.title = title;
    }
}
