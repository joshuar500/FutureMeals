package com.joshrincon.springyummly.service;

import com.joshrincon.springyummly.yummlywrapper.Yummly;
import com.joshrincon.springyummly.yummlywrapper.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service("yummlyService")
public class YummlyService {

    private Yummly yummly;

    @Autowired
    public void setYummly(Yummly yummly) {
        this.yummly = yummly;
    }

    public Recipe getRecipe(String recipeName) throws IOException {
        return yummly.getRecipe(recipeName);
    }

}
