package com.teshlya.maps.data;

public class QueryParameters {

    private String key;
    private String lat;
    private String lon;

    public QueryParameters(String key, String lat, String lon) {
        this.key = key;
        this.lat = lat;
        this.lon = lon;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
