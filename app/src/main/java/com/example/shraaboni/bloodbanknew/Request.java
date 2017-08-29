package com.example.shraaboni.bloodbanknew;

/**
 * Created by shraaboni on 8/3/2017.
 */

public class Request {
    private String request;
    private String bags;
    private  String area;

    public Request() {
    }

    public Request(String request, String bags, String area) {
        this.request = request;
        this.bags = bags;
        this.area = area;
    }

    public String getRequest() {
        return request;
    }

    public String getBags() {
        return bags;
    }

    public String getArea() {
        return area;
    }
}
