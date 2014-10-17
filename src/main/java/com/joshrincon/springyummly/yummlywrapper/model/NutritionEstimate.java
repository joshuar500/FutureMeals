package com.joshrincon.springyummly.yummlywrapper.model;

import org.springframework.stereotype.Component;

@Component
public class NutritionEstimate {

    private String attribute;
    private String description;
    private Unit unit;
    private Number value;
    public String getAttribute() {
        return this.attribute;
    }
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Unit getUnit() {
        return this.unit;
    }
    public void setUnit(Unit unit) {
        this.unit = unit;
    }
    public Number getValue() {
        return this.value;
    }
    public void setValue(Number value) {
        this.value = value;
    }

}
