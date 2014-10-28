package com.joshrincon.springyummly.controller;


import com.joshrincon.springyummly.service.YummlyService;
import com.joshrincon.springyummly.yummlywrapper.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class HomeController {

    private static final String charset = "UTF-8";

    YummlyService yummlyService;

    @Autowired
    public void setYummlyService(YummlyService yummlyService) {
        this.yummlyService = yummlyService;
    }

    @RequestMapping("/")
    //@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

        ArrayList<Recipe> popularRecipes;

        popularRecipes = yummlyService.getSearchRecipes("popular");

        model.addAttribute("popularRecipes", popularRecipes);

        return "home";
	}

}