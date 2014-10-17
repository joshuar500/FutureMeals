package com.joshrincon.springyummly.yummlywrapper.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class Criteria {

    private ArrayList<String> allowedIngredients;
    private ArrayList<String> excludedIngredients;
    private ArrayList<String> allowedAttributes;
    private ArrayList<String> excludedAttributes;
    private ArrayList<String> facetFields;
    private int maxResults;
    private boolean requirePictures;
    private int resultsToSkip;
    private ArrayList<String> terms;
    HashMap<String, NutritionRange> nutritionRestrictions;
    public HashMap<String, NutritionRange> getNutritionRestrictions() {
        return nutritionRestrictions;
    }
    public void setNutritionRestrictions(
            HashMap<String, NutritionRange> nutritionRestrictions) {
        this.nutritionRestrictions = nutritionRestrictions;
    }
    private Object attributeRanges;
    public void setAttributeRanges(Object attributeRanges) {
        this.attributeRanges = attributeRanges;
    }
    public ArrayList<String> getAllowedIngredients() {
        return allowedIngredients;
    }
    public void setAllowedIngredients(ArrayList<String> allowedIngredients) {
        this.allowedIngredients = allowedIngredients;
    }
    public ArrayList<String> getExcludedIngredients() {
        return excludedIngredients;
    }
    public void setExcludedIngredients(ArrayList<String> excludedIngredients) {
        this.excludedIngredients = excludedIngredients;
    }
    public ArrayList<String> getAllowedAttributes() {
        return allowedAttributes;
    }
    public void setAllowedAttributes(ArrayList<String> allowedAttributes) {
        this.allowedAttributes = allowedAttributes;
    }
    public ArrayList<String> getExcludedAttributes() {
        return excludedAttributes;
    }
    public void setExcludedAttributes(ArrayList<String> excludedAttributes) {
        this.excludedAttributes = excludedAttributes;
    }
    public ArrayList<String> getFacetFields() {
        return facetFields;
    }
    public void setFacetFields(ArrayList<String> facetFields) {
        this.facetFields = facetFields;
    }
    public int getMaxResults() {
        return maxResults;
    }
    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }
    public boolean isRequirePictures() {
        return requirePictures;
    }
    public void setRequirePictures(boolean requirePictures) {
        this.requirePictures = requirePictures;
    }
    public int getResultsToSkip() {
        return resultsToSkip;
    }
    public void setResultsToSkip(int resultsToSkip) {
        this.resultsToSkip = resultsToSkip;
    }
    public ArrayList<String> getTerms() {
        return terms;
    }
    public void setTerms(ArrayList<String> terms) {
        this.terms = terms;
    }
    public Object getAttributeRanges() {
        return attributeRanges;
    }

}
