package com.joshrincon.springyummly.recipeParser;

import com.joshrincon.springyummly.recipeParser.dictionary.DictionaryManager;
import com.joshrincon.springyummly.recipeParser.ingrControllers.IngredientParser;
import com.joshrincon.springyummly.recipeParser.instControllers.InstructionParser;
import com.joshrincon.springyummly.recipeParser.instControllers.InstructionSubstitution;
import com.joshrincon.springyummly.recipeParser.recipeModels.Ingredient;
import com.joshrincon.springyummly.recipeParser.recipeModels.Instruction;
import com.joshrincon.springyummly.recipeParser.recipeModels.RecipeP;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component("recipeParser")
@Scope("prototype")
public class RecipeParser {

    public RecipeP parseRecipe(String address, RecipeResourceType type ) throws IOException {
		switch(type){
		case URL:
			return this.parseRecipeFromURL(address);
		default:
			return this.parseRecipeFromFile(address);
		}
	}

    public RecipeP parseRecipeFromList(List<List<String>> recipes){
        RecipeP recipe = new RecipeP();

        StringBuilder sb = new StringBuilder();
        sb.append("MetaData:\n");
        sb.append("\n");
        sb.append("Ingredients:\n");
        for(List<String> rcp : recipes) {
            for(String ingredient : rcp) {
                sb.append(ingredient).append("\n");
            }
        }

        sb.append("Instructions:\n");
        sb.append("\n");

        System.out.println("HEY BITTTTCHHH" + sb.toString());

        DictionaryManager dm = new DictionaryManager();
        // TODO: change this to the correct directory/classpath
        try {
            dm.buildDictionary("C:\\Users\\Josh\\Documents\\GitHub\\FutureMeals\\src\\main\\webapp\\resources\\data\\");
        } catch (IOException e) {
            e.printStackTrace();
        }

        IngredientParser igdParser = new IngredientParser(dm);
        List<Ingredient> igds = new ArrayList<Ingredient>();

        InstructionParser istParser = new InstructionParser(dm, igds);
        InstructionSubstitution istSubst = new InstructionSubstitution(dm);
        List<Instruction> ists = new ArrayList<Instruction>();


        int lineIndex = 0;
        int charIndex = 0;
        String sentence = "";
        RecipePartType state = RecipePartType.PREDATA;

        String[] lines = sb.toString().split("\\n");
        for(String line : lines) {
            recipe.setFullText(recipe.getFullText() + "\n" + line);
            line=line.toLowerCase();
            lineIndex++;
            if(line.length() == 0) {
                continue;
            }
            if(state == RecipePartType.PREDATA && line.equalsIgnoreCase("MetaData:")) {
                state = RecipePartType.METADATA;
                System.out.println("\nStart Parsing Metadata...");
                continue;
            }
            if(state == RecipePartType.METADATA && line.equalsIgnoreCase("Ingredients:")) {
                state = RecipePartType.INGREDIENTS;
                System.out.println("\nStart Parsing Ingredients...");
                continue;
            }
            if(state == RecipePartType.INGREDIENTS && line.equalsIgnoreCase("Instructions:")) {
                state = RecipePartType.INSTRUCTIONS;
                System.out.println("\nStart Parsing Instructions...");
                continue;
            }

            switch(state){
                case METADATA:
                    break;
                case INGREDIENTS:
                    Ingredient igd = igdParser.parseIngredient(line, lineIndex);
                    if(igd != null){
                        igds.add(igd);
                    }
                    break;
                case INSTRUCTIONS:
                    //sentence by sentence (period = '.' followed a space or new line)
                    int periodIndex = -1;
                    int charIndexInst = -1;
                    while((periodIndex = line.indexOf('.', charIndexInst))!=-1){
                        if(periodIndex < line.length()-1){//not the end
                            if(line.charAt(periodIndex+1)!=' '&&//not a period
                                    line.charAt(periodIndex+1)!='\t'){
                                charIndexInst = periodIndex+1;
                                continue;
                            }
                        }
                        //got a sentence
                        sentence += line.substring(charIndex, periodIndex+1);
                        charIndexInst = periodIndex+1;
                        //System.out.println(sentence);
                        //parse
                        Instruction ist = istParser.parseInstuction(sentence, lineIndex, charIndexInst);
                        if(ist != null) {
                            istParser.linkPrepAction(ist, ists, istSubst);
                            ists.add(ist);
                        }
                        //start new one
                        charIndex = charIndexInst;
                        sentence = "";
                    }
                    if(charIndex < line.length()-1){
                        sentence = line.substring(charIndex);
                    }
                    charIndex=0;
                    break;
                default:
                    continue;
            }
        }

        recipe.setIngredients(igds);
        recipe.setInstructions(ists);

        System.out.println(recipe.getIngredients());

        return recipe;


    }
	
	public RecipeP parseRecipeFromForm(String metadata, String ingredients, String instructions) throws IOException {
		//write to file
		String fileName = "recipes/"+ (new Date()).getTime() +".txt";
		FileWriter fstream = new FileWriter(fileName);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write("MetaData:\n");
		out.write(metadata + "\n");
		out.write("Ingredients:\n");
		out.write(ingredients + "\n");
		out.write("Instructions:\n");
		out.write(instructions);
		//Close the output stream
		out.close();
		
		//parse
		RecipeP r = this.parseRecipeFromFile(fileName);
		
		//delete
		(new File(fileName)).delete();
		
		//return
		return r;
	}
	
	public RecipeP parseRecipeFromFile(String filePath) throws IOException {
		RecipeP recipe = new RecipeP();
		
		FileInputStream inFile = new FileInputStream(filePath);
        DataInputStream inStream = new DataInputStream(inFile);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "UTF8"));
        
        DictionaryManager dm = new DictionaryManager();
        // TODO: change this to the correct directory/classpath
		dm.buildDictionary("C:\\Users\\Josh\\Documents\\GitHub\\FutureMeals\\src\\main\\webapp\\resources\\data");
		
        IngredientParser igdParser = new IngredientParser(dm);
        List<Ingredient> igds = new ArrayList<Ingredient>();
        
        InstructionParser istParser = new InstructionParser(dm, igds);
        InstructionSubstitution istSubst = new InstructionSubstitution(dm);
        List<Instruction> ists = new ArrayList<Instruction>();
        
        String line = null;
        int lineIndex = 0;
        int charIndex = 0;
        String sentence = "";
        RecipePartType state = RecipePartType.PREDATA;
        
        while ((line = reader.readLine()) != null){
        	recipe.setFullText(recipe.getFullText() + "\n" + line);
        	line=line.toLowerCase();
        	lineIndex++;
        	if(line.length() == 0) {
        		continue;
        	}
        	if(state == RecipePartType.PREDATA && line.equalsIgnoreCase("MetaData:")) {
        		state = RecipePartType.METADATA;
        		System.out.println("\nStart Parsing Metadata...");
        		continue;
        	}
        	if(state == RecipePartType.METADATA && line.equalsIgnoreCase("Ingredients:")) {
        		state = RecipePartType.INGREDIENTS;
        		System.out.println("\nStart Parsing Ingredients...");
        		continue;
        	}
        	if(state == RecipePartType.INGREDIENTS && line.equalsIgnoreCase("Instructions:")) {
        		state = RecipePartType.INSTRUCTIONS;
        		System.out.println("\nStart Parsing Instructions...");
        		continue;
        	}
        	
        	switch(state){
        	case METADATA:
        		break;
        	case INGREDIENTS:
        		Ingredient igd = igdParser.parseIngredient(line, lineIndex);
        		if(igd != null){
        			igds.add(igd);
        		}
        		break;
        	case INSTRUCTIONS:
        		//sentence by sentence (period = '.' followed a space or new line)
        		int periodIndex = -1;
        		int charIndexInst = -1;
        		while((periodIndex = line.indexOf('.', charIndexInst))!=-1){
        			if(periodIndex < line.length()-1){//not the end
            			if(line.charAt(periodIndex+1)!=' '&&//not a period
            					line.charAt(periodIndex+1)!='\t'){
            				charIndexInst = periodIndex+1;
            				continue;
            			}
            		}
        			//got a sentence
        			sentence += line.substring(charIndex, periodIndex+1);
        			charIndexInst = periodIndex+1;
        			//System.out.println(sentence);
        			//parse
        			Instruction ist = istParser.parseInstuction(sentence, lineIndex, charIndexInst);
        			if(ist != null) {
        				istParser.linkPrepAction(ist, ists, istSubst);
        				ists.add(ist);
        			}
        			//start new one
        			charIndex = charIndexInst;
        			sentence = "";
        		}
        		if(charIndex < line.length()-1){
        			sentence = line.substring(charIndex);
        		}
        		charIndex=0;
        		break;
        	default:
        		continue;
        	}
        	
        }
        recipe.setIngredients(igds);
        recipe.setInstructions(ists);
        //igds.get(5).setName("NOR");
        reader.close();
        inStream.close();
        inFile.close();
		return recipe;
	}
	
	public RecipeP parseRecipeFromURL(String url) {
		return null;
	}
}
