package com.github.binarywang.demo.wechat.controller;


import com.github.binarywang.demo.wechat.beans.Gps;
import com.github.binarywang.demo.wechat.dao.GpsRecordDao;
import com.github.binarywang.demo.wechat.json.GPSTrackResponse;
import com.github.binarywang.demo.wechat.json.RequestDate;
import com.github.binarywang.demo.wechat.utils.DateUtils;
import com.github.binarywang.demo.wechat.utils.GpsTransfer;
import com.github.binarywang.demo.wechat.utils.LocationUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author huyong
 * @since 2018/2/16
 */
@RestController
@RequestMapping("/gps")
public class GpsController {

    @Autowired
    GpsRecordDao gpsRecordDao;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/create")
    public String testCreate() {
        Gps gps = new Gps();
        gps.setLon("1000");
        gps.setLat("200");
        gpsRecordDao.insert(gps);
        return "success";
    }

    @RequestMapping("/getGpsList")
    public GPSTrackResponse getGpsList(@RequestBody RequestDate requestDate) {
        logger.debug("startDate:" + requestDate.getStartDate());
//        logger.debug("endDate:" + requestDate.getEndDate());
        List<Gps> orginalGps = gpsRecordDao.getByDate(requestDate.getStartDate(), requestDate.getStartDate());
//        return null;
        List<Gps> resultList = new ArrayList<>();
        double distance = 0;
        double lastLat = 0;
        double lastLon = 0;
        Date lastPointDate = null;
        for (Gps gps : orginalGps) {
            Gps newGps = new Gps();

            double[] gpss = GpsTransfer.wgs2gcj(Double.parseDouble(gps.getLon()), Double.parseDouble(gps.getLat()));
            newGps.setLat(String.valueOf(gpss[0]));
            newGps.setLon(String.valueOf(gpss[1]));
            newGps.setStartFlag(gps.getStartFlag());
            if (gps.getStartFlag() == 1) {
                lastLat = 0;
                lastLon = 0;
                lastPointDate = null;
            }
            double distanceTwoPoint = LocationUtils.getDistance(lastLat, lastLon, gpss[0], gpss[1]);
            System.out.println(distanceTwoPoint + " " + gps.getCreateDate());
            //过滤10秒内大于100M的点
            if (lastPointDate != null && gps.getStartFlag() != 1
                    && (lastPointDate.getTime() - DateUtils.convertStr2Date(gps.getCreateDate()).getTime()) / 1000 < 10
                    && distanceTwoPoint > 100) {
                System.out.println("---------" + distanceTwoPoint + " " + gps.getCreateDate());
                continue;

            }
            if (lastLat != 0 && lastLon != 0 && lastPointDate != null) {
                distance = distance + distanceTwoPoint;
            }

            lastLat = gpss[0];
            lastLon = gpss[1];
            lastPointDate = DateUtils.convertStr2Date(gps.getCreateDate());

            resultList.add(newGps);
        }
        GPSTrackResponse response = new GPSTrackResponse();
        response.setDistance(new DecimalFormat("#.00").format(distance / 1000.00));
        response.setGpsList(resultList);
        logger.debug("response:" + response);
        return response;

    }

    @RequestMapping("/getCurrentGps")
    public Gps getCurrentGps() {
        Gps orginalGps = gpsRecordDao.getCurrentGps();
        Gps newGps = new Gps();
        double[] gpss = GpsTransfer.wgs2gcj(Double.parseDouble(orginalGps.getLon()), Double.parseDouble(orginalGps.getLat()));
        newGps.setLat(String.valueOf(gpss[0]));
        newGps.setLon(String.valueOf(gpss[1]));
        newGps.setStartFlag(orginalGps.getStartFlag());
        logger.debug("current gps:" + newGps.getLat() + " " + newGps.getLon());
        return newGps;

    }
}
