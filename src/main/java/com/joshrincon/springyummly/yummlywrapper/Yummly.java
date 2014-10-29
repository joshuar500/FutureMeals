package com.joshrincon.springyummly.yummlywrapper;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joshrincon.springyummly.yummlywrapper.model.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

@Component("yummly")
@Scope("prototype")
public class Yummly {
    private static final String BASE_URL = "http://api.yummly.com/v1/api/";

    // TODO: REMOVE THESE KEYS
    private String mAppId  = "";
    private String mAppKey = "";

    public SearchResult search(String query) throws IOException {
        return search(query, false, null, null, -1, null, null, false, false, null, 0);
    }
    /**
     * Searches for recipes matching a given query.
     *
     * @param query
     * The query to search for.
     * @param requirePictures
     * If true, only recipes with pictures are returned.
     * @return The result of the search. Be aware that the the returned recipes
     * are sparse. Use the {@link #getRecipe(String)} method to receive
     * all available information.
     * @throws IOException
     * Thrown if network or parsing errors occur.
     */
    public SearchResult search(String query, boolean requirePictures)
            throws IOException {
        return search(query, requirePictures, null, null, -1, null, null, false, false, null, 0);
    }

    public SearchResult search(String query, boolean requirePictures, int maxResult)
            throws IOException {
        return search(query, requirePictures, null, null, -1, null, null, false, false, null, maxResult);
    }
    /**
     * Searches for recipes matching a given query.
     *
     * @param query
     * The query to search for.
     * @param requirePictures
     * If true, only recipes with pictures are returned.
     * @param requiredIngredients
     * A list of required ingredient names in the recipes.
     * @param excludedIngredients
     * A list of excluded ingredient names in the recipes.
     * @param maxTotalTimeInSeconds
     * The maximum total preparetion and cooking time in seconds.
     * @param minFlavors
     * The minimum flavor levels.
     * @param maxFlavors
     * The maximum flavor levels.
     * @param ingredientFacetField
     * If true, the result will contain the total count of each
     * ingredient in the matched recipes.
     * @param dietFacetField
     * If true, the result will contain the total count of each
     * diet in the matched recipes.
     * @param maxResult
     * The maxResult and start parameters allow pagination
     * and # of results control
     * @return The result of the search. Be aware that the the returned recipes
     * are sparse. Use the {@link #getRecipe(String)} method to receive
     * all available information.
     * @throws IOException
     * Thrown if network or parsing errors occur.
     */
    public SearchResult search(String query, boolean requirePictures,
                               ArrayList<String> requiredIngredients,
                               ArrayList<String> excludedIngredients, int maxTotalTimeInSeconds,
                               Flavors minFlavors, Flavors maxFlavors,
                               boolean ingredientFacetField, boolean dietFacetField,
                               ArrayList<NutritionRange> nutritionRanges,
                               int maxResult)
            throws IOException {
// Set parameters.
        ArrayList<Parameter> params = new ArrayList<Parameter>();
        params.add(new Parameter("q", URLEncoder.encode(query, "utf8")));
        params.add(new Parameter("requirePictures", requirePictures ? "true"
                : "false"));
        if (requiredIngredients != null) {
            for (String ingredient : requiredIngredients) {
                params.add(new Parameter(URLEncoder.encode(
                        "requiredIngredient[]", "utf8"), URLEncoder.encode(
                        ingredient, "utf8")));
            }
        }
        if (excludedIngredients != null) {
            for (String ingredient : excludedIngredients) {
                params.add(new Parameter(URLEncoder.encode(
                        "excludedIngredient[]", "utf8"), URLEncoder.encode(
                        ingredient, "utf8")));
            }
        }
        if (ingredientFacetField) {
            params.add(new Parameter(URLEncoder.encode("facetField[]", "utf8"),
                    URLEncoder.encode("ingredient", "utf8")));
        }
        if (dietFacetField) {
            params.add(new Parameter(URLEncoder.encode("facetField[]", "utf8"),
                    URLEncoder.encode("diet", "utf8")));
        }
        if (maxTotalTimeInSeconds > 0) {
            params.add(new Parameter("maxTotalTimeInSeconds", Integer
                    .toString(maxTotalTimeInSeconds)));
        }
        if (minFlavors != null) {
            if (minFlavors.getSweet() != null) {
                params.add(new Parameter("flavor.sweet.min", minFlavors
                        .getSweet().toString()));
            }
            if (minFlavors.getBitter() != null) {
                params.add(new Parameter("flavor.bitter.min", minFlavors
                        .getBitter().toString()));
            }
            if (minFlavors.getMeaty() != null) {
                params.add(new Parameter("flavor.meaty.min", minFlavors
                        .getMeaty().toString()));
            }
            if (minFlavors.getPiquant() != null) {
                params.add(new Parameter("flavor.piquant.min", minFlavors
                        .getPiquant().toString()));
            }
            if (minFlavors.getSalty() != null) {
                params.add(new Parameter("flavor.salty.min", minFlavors
                        .getSalty().toString()));
            }
            if (minFlavors.getSour() != null) {
                params.add(new Parameter("flavor.sour.min", minFlavors
                        .getSour().toString()));
            }
        }
        if (maxFlavors != null) {
            if (maxFlavors.getSweet() != null) {
                params.add(new Parameter("flavor.sweet.max", maxFlavors
                        .getSweet().toString()));
            }
            if (maxFlavors.getBitter() != null) {
                params.add(new Parameter("flavor.bitter.max", maxFlavors
                        .getBitter().toString()));
            }
            if (maxFlavors.getMeaty() != null) {
                params.add(new Parameter("flavor.meaty.max", maxFlavors
                        .getMeaty().toString()));
            }
            if (maxFlavors.getPiquant() != null) {
                params.add(new Parameter("flavor.piquant.max", maxFlavors
                        .getPiquant().toString()));
            }
            if (maxFlavors.getSalty() != null) {
                params.add(new Parameter("flavor.salty.max", maxFlavors
                        .getSalty().toString()));
            }
            if (maxFlavors.getSour() != null) {
                params.add(new Parameter("flavor.sour.max", maxFlavors
                        .getSour().toString()));
            }
        }
        if (nutritionRanges != null) {
            for (NutritionRange range : nutritionRanges) {
                params.add(new Parameter(URLEncoder.encode(
                        String.format("nutrition.%s.max", range.getNutrition().toString()), "utf8"), range.getMax().toString()));
                params.add(new Parameter(URLEncoder.encode(
                        String.format("nutrition.%s.min", range.getNutrition().toString()), "utf8"), range.getMin().toString()));
            }
        }
        if (maxResult > 0) {
            params.add(new Parameter("maxResult", Integer
                    .toString(maxResult)));
        }
// Perform search request.
        InputStream in = performRequest("recipes", params);
// Parse json.
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        SearchResult result = mapper.readValue(in, SearchResult.class);
        in.close();
        return result;
    }
    /**
     * Requests information about a recipe with the given id.
     *
     * @param recipeId
     * The id of the recipe.
     * @return The requested recipe.
     * @throws IOException
     * Thrown if network errors or parsing errors occur.
     */
    public Recipe getRecipe(String recipeId) throws IOException {
// Perform recipe request.
        InputStream in = performRequest(
                String.format("recipe/%s", URLEncoder.encode(recipeId, "utf8")),
                null);
// Parse json.
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        Recipe result = mapper.readValue(in, Recipe.class);
        in.close();
        return result;
    }
    /**
     * Performs the actual HTTP-request.
     *
     * @param appendedUrl
     * The specific URL-extension of the api.
     * @param parameters
     * The url parameters.
     * @return An inputstream of the response.
     * @throws IOException
     * Thrown if network errors occur.
     */
    private InputStream performRequest(String appendedUrl,
                                       ArrayList<Parameter> parameters) throws IOException {
        StringBuilder paramString = new StringBuilder();
// Set parameters.
        if (parameters != null) {
            boolean firstLoop = true;
            for (Parameter param : parameters) {
                if (firstLoop) {
                    paramString.append("?");
                    firstLoop = false;
                } else {
                    paramString.append("&");
                }
                paramString.append(param.key);
                paramString.append("=");
                paramString.append(param.value);
            }
        }
        URL endpoint = new URL(BASE_URL + appendedUrl + paramString);
        System.out.println(endpoint);
        URLConnection urlCon = endpoint.openConnection();
        // Add header fields.
        urlCon.setRequestProperty("X-Yummly-App-ID", mAppId);
        urlCon.setRequestProperty("X-Yummly-App-Key", mAppKey);
        return urlCon.getInputStream();
    }
}
