package com.joshrincon.springyummly;

public class SearchTerm {

    String term;

    public SearchTerm() {

    }

    public SearchTerm(String term) {
        this.term = term;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
