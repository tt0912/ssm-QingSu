package com.ssm.qs.util;

import org.junit.Test;

import java.text.DecimalFormat;

public class LocationUtils {

    private static double EARTH_RADIUS = 6371.393;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 计算两个经纬度之间的距离
     *
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static String GetDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        DecimalFormat df = new DecimalFormat("#.0");
        return df.format(s);
    }

    @Test
    public void test() {
        System.out.println(LocationUtils.GetDistance(28.1939842, 113.0108862, 22.636828, 113.814606));
    }
}
