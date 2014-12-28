package com.joshrincon.springyummly.recipeParser.dictionary;

import org.springframework.stereotype.Component;

@Component
public class VocabType {
	private String category;
	private String type;

    public VocabType() {
    }

    public VocabType (String categoryType, String category){
		this.setCategory(category);
		this.setType(categoryType);
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return category + "-" + type;
	}
}
