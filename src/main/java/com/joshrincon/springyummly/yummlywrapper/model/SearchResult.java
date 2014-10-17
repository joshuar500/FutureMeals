package com.joshrincon.springyummly.yummlywrapper.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class SearchResult {

    private int totalMatchCount;
    private Attribution attribution;
    private ArrayList<Recipe> matches;
    private Criteria criteria;
    private HashMap<String, HashMap<String, Number>> facetCounts;
    public void setTotalMatchCount(int totalMatchCount) {
        this.totalMatchCount = totalMatchCount;
    }
    public int getTotalMatchCount() {
        return totalMatchCount;
    }
    public void setAttribution(Attribution attribution) {
        this.attribution = attribution;
    }
    public Attribution getAttribution() {
        return attribution;
    }
    public void setMatches(ArrayList<Recipe> matches) {
        this.matches = matches;
    }
    public ArrayList<Recipe> getMatches() {
        return matches;
    }
    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }
    public Criteria getCriteria() {
        return criteria;
    }
    public void setFacetCounts(HashMap<String, HashMap<String, Number>> facetCounts) {
        this.facetCounts = facetCounts;
    }
    public HashMap<String, HashMap<String, Number>> getFacetCounts() {
        return facetCounts;
    }

}
