package com.jello.zero;

import java.io.Serializable;

/**
 * Created by hoangphat1908 on 3/5/2017.
 */
@SuppressWarnings("serial")
public class Alert implements Serializable{
    public String name;
    public String category;
    public String location;
    public double latitude;
    public double longitude;
    private String distance;
    private String key;
    public Alert(){};
    public Alert(String name, String category, String location, double latitude, double longitude){
        this.name = name;
        this.category = category;
        this.location = location;
        if(latitude != -100 && longitude != -200) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }
    public Alert(String name, String category, String location, String latitude, String longitude){
            this(name, category, location, latitude != null? Double.valueOf(latitude): 0, longitude != null? Double.valueOf(longitude) : 0);

    }
    public void setKey(String key)
    {
        this.key = key;
    }
    public String getKey()
    {
        return key;
    }
    public void setDistance(String distance)
    {
        this.distance = distance;
    }
    public String getLoc()
    {
        return distance;
    }
    @Override
    public String toString()
    {
        return name + "\n" + category + "\nLocation: " + location + "\nCoordinates: " + latitude + ", " + longitude + "\n" + distance;
    }
}
