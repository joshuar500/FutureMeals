package com.joshrincon.springyummly.yummlywrapper.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Parameter {

    public String key;
    public String value;

    public Parameter() {
        super();
    }

    public Parameter(String key, String value) {
        this.key = key;
        this.value = value;
    }

}
