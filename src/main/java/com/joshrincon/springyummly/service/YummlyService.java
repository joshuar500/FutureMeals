package com.joshrincon.springyummly.service;


import com.joshrincon.springyummly.yummlywrapper.Yummly;
import com.joshrincon.springyummly.yummlywrapper.model.Criteria;
import com.joshrincon.springyummly.yummlywrapper.model.Recipe;
import com.joshrincon.springyummly.yummlywrapper.model.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("yummlyService")
public class YummlyService {

    private Yummly yummly;
    private Criteria criteria;

    @Autowired
    public void setYummly(Yummly yummly) {
        this.yummly = yummly;
    }

    @Autowired
    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public Recipe getRecipe(String recipeName) {
        try {
            return yummly.getRecipe(recipeName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // TODO: DON'T RETURN NULL?
        return null;
    }

    public ArrayList<Recipe> getSearchRecipes(String keyword) {
        try {
            criteria.setMaxResults(12);
            SearchResult results = yummly.search(keyword, true);
            return results.getMatches();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // TODO: DON'T RETURN NULL?
        return null;
    }

}
