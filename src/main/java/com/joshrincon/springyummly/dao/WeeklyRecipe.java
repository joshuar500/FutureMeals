package com.joshrincon.springyummly.dao;

import javax.validation.constraints.Size;

public class WeeklyRecipe {

    private int id;
    @Size(min = 5, max = 800)
    private String id_mon;
    private String id_tue;
    private String id_wed;
    private String id_thu;
    private String id_fri;
    private String id_sat;
    private String id_sun;

    public WeeklyRecipe() {
    }

    public WeeklyRecipe(int id, String id_mon, String id_tue, String id_wed, String id_thu, String id_fri, String id_sat, String id_sun) {
        this.id = id;
        this.id_mon = id_mon;
        this.id_tue = id_tue;
        this.id_wed = id_wed;
        this.id_thu = id_thu;
        this.id_fri = id_fri;
        this.id_sat = id_sat;
        this.id_sun = id_sun;
    }

    public WeeklyRecipe(String id_mon, String id_tue, String id_wed, String id_thu, String id_fri, String id_sat, String id_sun) {
        this.id_mon = id_mon;
        this.id_tue = id_tue;
        this.id_wed = id_wed;
        this.id_thu = id_thu;
        this.id_fri = id_fri;
        this.id_sat = id_sat;
        this.id_sun = id_sun;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_mon() {
        return id_mon;
    }

    public void setId_mon(String id_mon) {
        this.id_mon = id_mon;
    }

    public String getId_tue() {
        return id_tue;
    }

    public void setId_tue(String id_tue) {
        this.id_tue = id_tue;
    }

    public String getId_wed() {
        return id_wed;
    }

    public void setId_wed(String id_wed) {
        this.id_wed = id_wed;
    }

    public String getId_thu() {
        return id_thu;
    }

    public void setId_thu(String id_thu) {
        this.id_thu = id_thu;
    }

    public String getId_fri() {
        return id_fri;
    }

    public void setId_fri(String id_fri) {
        this.id_fri = id_fri;
    }

    public String getId_sat() {
        return id_sat;
    }

    public void setId_sat(String id_sat) {
        this.id_sat = id_sat;
    }

    public String getId_sun() {
        return id_sun;
    }

    public void setId_sun(String id_sun) {
        this.id_sun = id_sun;
    }

    @Override
    public String toString() {
        return "WeeklyRecipe{" +
                "id_mon='" + id_mon + '\'' +
                ", id_tue='" + id_tue + '\'' +
                ", id_wed='" + id_wed + '\'' +
                ", id_thu='" + id_thu + '\'' +
                ", id_fri='" + id_fri + '\'' +
                ", id_sat='" + id_sat + '\'' +
                ", id_sun='" + id_sun + '\'' +
                '}';
    }
}
