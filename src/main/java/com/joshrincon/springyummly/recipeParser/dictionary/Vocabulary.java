package com.joshrincon.springyummly.recipeParser.dictionary;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class Vocabulary {
	private List<VocabType> categories;
	private String name;

    public Vocabulary() {
    }

    public Vocabulary(String name, VocabType category){
		this.name = name;
		this.categories = new ArrayList<VocabType>();
		this.categories.add(category);
	}
	
	public Vocabulary(String name, List<VocabType> categories){
		this.name = name;
		this.categories = categories;
	}
	
	public List<VocabType> getCategories() {
		return categories;
	}
	public void setCategories(List<VocabType> categories) {
		this.categories = categories;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString(){
		return name + ": " + this.categories;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	public static int getHashCode(String name){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
}
@Component
class VocabularySpaceComparator implements Comparator<Vocabulary> {

    VocabularySpaceComparator() {
    }

    @Override
	public int compare(Vocabulary v1, Vocabulary v2) {
        return v1.getName().split(" ").length - v2.getName().split(" ").length;
    }
}
