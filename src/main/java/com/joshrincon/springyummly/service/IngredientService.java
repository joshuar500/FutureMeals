package com.joshrincon.springyummly.service;

import com.joshrincon.springyummly.recipeParser.RecipeParser;
import com.joshrincon.springyummly.recipeParser.recipeModels.RecipeP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ingredientService")
public class IngredientService {

    private RecipeParser recipeParser;

    @Autowired
    public void setRecipeParser(RecipeParser recipeParser) {
        this.recipeParser = recipeParser;
    }

    public RecipeP getDemIngredients(List<List<String>> recipes) {
        System.out.println("Inside getDemIngredients");
        if (recipes.get(0) != null) {
            return recipeParser.parseRecipeFromList(recipes);
        } else {
            System.out.println("Fucking goddamn null");
        }
        return null;
    }

}
