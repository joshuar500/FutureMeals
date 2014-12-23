package com.joshrincon.springyummly.service;

import com.joshrincon.springyummly.dao.WeeklyRecipe;
import com.joshrincon.springyummly.dao.WeeklyRecipeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("weeklyRecipeService")
public class WeeklyRecipeService {

    private WeeklyRecipeDAO weeklyRecipeDAO;

    @Autowired
    public void setWeeklyRecipeDAO(WeeklyRecipeDAO weeklyRecipeDAO) {
        this.weeklyRecipeDAO = weeklyRecipeDAO;
    }

    public List<WeeklyRecipe> getAllWeeks() {
        return weeklyRecipeDAO.getWeeklyRecipes();
    }

    public void create(WeeklyRecipe weeklyRecipe) {
        weeklyRecipeDAO.create(weeklyRecipe);
    }

}
