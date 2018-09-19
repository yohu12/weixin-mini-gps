package com.github.binarywang.demo.wechat.beans;

/**
 * @author huyong
 * @since 2018/2/11
 */
public class Gps {
    private String lon;
    private String lat;
    private int startFlag;
    private String createDate;

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public int getStartFlag() {
        return startFlag;
    }

    public void setStartFlag(int startFlag) {
        this.startFlag = startFlag;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
