package com.joshrincon.springyummly.controller;

import com.joshrincon.springyummly.SearchTerm;
import com.joshrincon.springyummly.dao.WeeklyRecipe;
import com.joshrincon.springyummly.service.WeeklyRecipeService;
import com.joshrincon.springyummly.service.YummlyService;
import com.joshrincon.springyummly.yummlywrapper.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class YummlyController {

    private YummlyService yummlyService;
    private WeeklyRecipeService weeklyRecipeService;

    @Autowired
    public void setYummlyService(YummlyService yummlyService) {
        this.yummlyService = yummlyService;
    }

    @Autowired
    public void setWeeklyRecipeService(WeeklyRecipeService weeklyRecipeService) {
        this.weeklyRecipeService = weeklyRecipeService;
    }

    @RequestMapping("/")
    //@RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {

        // create an object with an attribute "weeklyRecipe" for
        // the form / commandName

        model.addAttribute("weeklyRecipe", new WeeklyRecipe());

        List<WeeklyRecipe> weeklyRecipeList = weeklyRecipeService.getAllWeeks();

        model.addAttribute("weeklyRecipeList", weeklyRecipeList);

        ArrayList<Recipe> popularRecipes;

        popularRecipes = yummlyService.search("popular");

        model.addAttribute("popularRecipes", popularRecipes);

        return "home";
    }

    @RequestMapping("/search")
    public String searchYummly(ModelMap model) {

        model.addAttribute("search", new SearchTerm());

        return "search";
    }

    @RequestMapping(value = "/results", method = RequestMethod.POST)
    public String results(ModelMap model, SearchTerm term) {

        ArrayList<Recipe> results;

        results = yummlyService.search(term.getTerm());

        model.addAttribute("results", results);

        return "results";
    }

    @RequestMapping("/showrecipes")
    public String showRecipes(ModelMap model) {

        Recipe recipe;

        recipe = yummlyService.getRecipe("Hot-Turkey-Salad-Sandwiches-Allrecipes");

        model.addAttribute("recipe", recipe);

        return "recipes";
    }


    /*****
    @RequestMapping(value = "/savecollection", method = RequestMethod.POST)
    public String saveCollection(Model model, @Valid WeeklyRecipe weeklyRecipe, BindingResult result) {

        if(result.hasErrors()) {
            return "home";
        }

        return "collectionsaved";
    }
    *****/

    @RequestMapping(value = "/savecollection", method = RequestMethod.POST)
    public String getRecipeIngredients(Model model, @Valid WeeklyRecipe weeklyRecipe, BindingResult result) {

        // what if the recipe doesn't have ingredients?
        // ie what if it's an advertisement?

        if(result.hasErrors()) {
            return "home";
        }

        // save collection names to the database
        weeklyRecipeService.create(weeklyRecipe);

        // regex all periods and commas. search for recipes and get ingredients
        String pattern = "([./,])";

        String monRecipe = weeklyRecipe.getId_mon().replaceAll(pattern, "");

        System.out.println("monRecipe: " + yummlyService.getIngredients(monRecipe));

        // return grocery list
        return "grocerylist";

    }

    @RequestMapping("/test")
    public String showTest() {
        return "test";
    }
}