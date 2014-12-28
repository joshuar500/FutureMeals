package com.joshrincon.springyummly.service;


import com.joshrincon.springyummly.yummlywrapper.Yummly;
import com.joshrincon.springyummly.yummlywrapper.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("yummlyService")
public class YummlyService {

    private Yummly yummly;

    @Autowired
    public void setYummly(Yummly yummly) {
        this.yummly = yummly;
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

    public ArrayList<Recipe> search(String keyword) {
        ArrayList<Recipe> popularRecipes;
        try {

            popularRecipes = yummly.search(keyword, true, 12).getMatches();

            // ****** BELOW COMMENT IS NO LONGER POSSIBLE ******* //
            // in future create a new pojo that gets medium/large images.
            // the way it is currently only shows small thumbnails when
            // using search API. the recipes API has all the bigger images.
            // also probably not a good idea to make too many api calls.
            /*
            List<String> recipeImages = new ArrayList<String>();

            int count = 0;
            for(Recipe recipe : popularRecipes) {
                recipeImages.add(recipe.getImages().get(count).getHostedMediumUrl());
            }
            */
            return popularRecipes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        // TODO: DON'T RETURN NULL?
        return null;
    }

    public List<String> getIngredients(String term) throws IOException{
        Recipe recipe;

        recipe = yummly.getRecipe(term);
        return recipe.getIngredientLines();
    }

}
