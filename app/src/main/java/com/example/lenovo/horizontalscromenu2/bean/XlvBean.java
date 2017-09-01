package com.example.lenovo.horizontalscromenu2.bean;

/**
 * Created by lenovo on 2017/8/31.
 */

public class XlvBean {
    private String title;
    private String pic;

    @Override
    public String toString() {
        return "XlvBean{" +
                "title='" + title + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public XlvBean(String title, String pic) {

        this.title = title;
        this.pic = pic;
    }
}
