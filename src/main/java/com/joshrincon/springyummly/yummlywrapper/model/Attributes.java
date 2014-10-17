package com.joshrincon.springyummly.yummlywrapper.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Attributes {

    private List<String> course;
    private List<String> holiday;
    private List<String> cuisine;

    public List<String> getCourse() {
        return this.course;
    }

    public void setCourse(List<String> course) {
        this.course = course;
    }

    public List<String> getHoliday() {
        return this.holiday;
    }

    public void setHoliday(List<String> holiday) {
        this.holiday = holiday;
    }

    public List<String> getCuisine() {
        return this.cuisine;
    }

    public void setCuisine(List<String> cuisine) {
        this.cuisine = cuisine;
    }

}
