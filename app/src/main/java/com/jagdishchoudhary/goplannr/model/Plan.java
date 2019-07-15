package com.jagdishchoudhary.goplannr.model;

import java.util.ArrayList;

public class Plan {
    private String name;
    private Integer minAge;
    private Integer maxAge;
    private Integer minSalary;
    private Integer maxSalary;
    private ArrayList<String> availableHospitals;
    private ArrayList<String> features;
    private String cover;
    private Integer price;

    public Plan(){}

    public Plan(String name, Integer minAge, Integer maxAge, Integer minSalary, Integer maxSalary, ArrayList<String> availableHospitals, ArrayList<String> features, String cover, Integer price
    ) {
        this.name = name;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.availableHospitals = availableHospitals;
        this.features = features;
        this.cover = cover;
        this.price = price;

    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public Integer getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Integer minSalary) {
        this.minSalary = minSalary;
    }

    public Integer getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Integer maxSalary) {
        this.maxSalary = maxSalary;
    }

    public ArrayList<String> getAvailableHospitals() {
        return availableHospitals;
    }

    public void setAvailableHospitals(ArrayList<String> availableHospitals) {
        this.availableHospitals = availableHospitals;
    }

    public ArrayList<String> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<String> features) {
        this.features = features;
    }
}
