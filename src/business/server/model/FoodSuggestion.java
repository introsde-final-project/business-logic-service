package business.server.model;

import java.util.ArrayList;

/**
 * Created by bishruti on 2/5/16.
 */
public class FoodSuggestion {
    private String label;
    private ArrayList<String> ingredientLines;
    private ArrayList<String> healthLabels;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ArrayList<String> getIngredientLines() {
        return ingredientLines;
    }

    public void setIngredientLines(ArrayList<String> ingredientLines) {
        this.ingredientLines = ingredientLines;
    }

    public ArrayList<String> getHealthLabels() {
        return healthLabels;
    }

    public void setHealthLabels(ArrayList<String> healthLabels) {
        this.healthLabels = healthLabels;
    }
}
