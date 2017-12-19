package com.me.njerucyrus.uberjob;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by njerucyrus on 12/19/17.
 * tes
 */

public class Job {
    private String title;
    private String category;
    private String description;
    private Date postedAt;
    private String postedBy;
    private Double price;
    private Double lat;
    private Double lng;
    private String namedAddress;
    private List <Job> jobs = new ArrayList<Job>();
    public Job(){}

    public Job(String title, String category, String description,
               Date postedAt, String postedBy, Double price,
               Double lat, Double lng, String namedAddress
    ){
        this.title = title;
        this.category = category;
        this.description = description;
        this.postedAt = postedAt;
        this.postedBy = postedBy;
        this.price = price;
        this.lat = lat;
        this.lng = lng;
        this.namedAddress = namedAddress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getNamedAddress() {
        return namedAddress;
    }

    public void setNamedAddress(String namedAddress) {
        this.namedAddress = namedAddress;
    }

    public List getJobs() {
        return jobs;
    }

    public void setJobs(List jobs) {
        this.jobs = jobs;
    }

    public void test(){

    }


    public  void willtest(){}

}