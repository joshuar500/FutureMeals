package com.joshrincon.springyummly.controller;

import com.joshrincon.springyummly.service.YummlyService;
import com.joshrincon.springyummly.yummlywrapper.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class YummlyController {

    private YummlyService yummlyService;

    @Autowired
    public void setYummlyService(YummlyService yummlyService) {
        this.yummlyService = yummlyService;
    }

    @RequestMapping("/search")
    public String printWelcome(ModelMap model) {

        ArrayList<Recipe> searchRecipes;

        searchRecipes = yummlyService.getPopularRecipes("soups");
        model.addAttribute("searchRecipes", searchRecipes);

        return "search";
    }

    @RequestMapping("/showRecipes")
    public String showRecipes(ModelMap model) {

        Recipe recipe;

        recipe = yummlyService.getRecipe("Hot-Turkey-Salad-Sandwiches-Allrecipes");
        model.addAttribute("recipe", recipe);

        return "recipes";
    }
}