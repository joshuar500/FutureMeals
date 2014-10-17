package com.joshrincon.springyummly.yummlywrapper.model;

import org.springframework.stereotype.Component;

@Component
public class NutritionRange {

    public enum NUTRITION {
        /**
         * Potassium (grams)
         */
        K,
        /**
         * Sodium (grams)
         */
        NA,
        /**
         * Cholesterol (grams)
         */
        CHOLE,
        /**
         * Fatty acids, total trans (grams)
         */
        FATRN,
        /**
         * Fatty acids, total saturated (grams)
         */
        FASAT,
        /**
         * Carbohydrate, by difference (grams)
         */
        CHOCDF,
        /**
         * Fiber, total dietary (grams)
         */
        FIBTG,
        /**
         * Protein (grams)
         */
        PROCNT,
        /**
         * Vitamin C, total ascorbic acid (grams)
         */
        VITC,
        /**
         * Calcium, Ca (grams)
         */
        CA,
        /**
         * Iron, Fe (grams)
         */
        FE,
        /**
         * Sugars (grams)
         */
        SUGAR,
        /**
         * Energy (kcla)
         */
        ENERC_KCAL,
        /**
         * Fat
         */
        FAT,
        /**
         * Vitamin A (IU)
         */
        VITA_IU
    }
    private NUTRITION nutrition;
    private Number min;
    private Number max;
    public NUTRITION getNutrition() {
        return nutrition;
    }
    public void setNutrition(NUTRITION nutrition) {
        this.nutrition = nutrition;
    }
    public Number getMin() {
        return min;
    }
    public void setMin(Number min) {
        this.min = min;
    }
    public Number getMax() {
        return max;
    }
    public void setMax(Number max) {
        this.max = max;
    }

}
