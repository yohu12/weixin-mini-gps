package com.github.binarywang.demo.wechat.json;

import com.github.binarywang.demo.wechat.beans.Gps;

import java.util.List;

/**
 * @author huyong
 * @since 2018/3/23
 */
public class GPSTrackResponse {

    public String distance;

    public List<Gps> gpsList;

    public List<Gps> getGpsList() {
        return gpsList;
    }

    public void setGpsList(List<Gps> gpsList) {
        this.gpsList = gpsList;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
