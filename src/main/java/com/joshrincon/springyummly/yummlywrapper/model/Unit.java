package com.joshrincon.springyummly.yummlywrapper.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Unit {

    private String abbreviation;
    private String id;
    private String name;
    private String plural;
    private String pluralAbbreviation;
    public String getAbbreviation() {
        return this.abbreviation;
    }
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPlural() {
        return this.plural;
    }
    public void setPlural(String plural) {
        this.plural = plural;
    }
    public String getPluralAbbreviation() {
        return this.pluralAbbreviation;
    }
    public void setPluralAbbreviation(String pluralAbbreviation) {
        this.pluralAbbreviation = pluralAbbreviation;
    }

}
