package com.joshrincon.springyummly.controller;

import com.joshrincon.springyummly.service.YummlyService;
import com.joshrincon.springyummly.yummlywrapper.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class YummlyController {

    private YummlyService yummlyService;

    @Autowired
    public void setYummlyService(YummlyService yummlyService) {
        this.yummlyService = yummlyService;
    }

    @RequestMapping("/search")
    public String printWelcome(ModelMap model) {

        return "searchyummly";
    }

    @RequestMapping("/showRecipes")
    public String showRecipes(ModelMap model) {

        Recipe recipe;

        try {
            recipe = yummlyService.getRecipe("Hot-Turkey-Salad-Sandwiches-Allrecipes");
            model.addAttribute("recipe", recipe);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "recipes";
    }
}