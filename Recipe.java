
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Sarah Ball
 */
public class Recipe implements Serializable {

    static int IDCounter = 0;
    private int id;
    private String title, instructions, notes;
    private ArrayList<Ingredient> ingredients;

    public Recipe(String title, ArrayList<Ingredient> ingredients, String instructions, String notes) {
        id = IDCounter++;
        this.title = title;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.notes = notes;

//        ingDetails = new ArrayList<>();
//        parseIngredients();
    }

    //----------getters----------------//
    public String getTitle() {
        return title;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
    
    public String getIngredientsString(){
        String ingString = "";
        for (Ingredient i : ingredients) {
            ingString += i + "\n";
        }
        return ingString;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getNotes() {
        return notes;
    }

    //---------------setters------------//
    public void setTitle(String s) {
        title = s;
    }

    public void setIngredients(ArrayList<Ingredient> s) {
        ingredients = s;
    }

    public void setInstructions(String s) {
        instructions = s;
    }

    public void setNotes(String s) {
        notes = s;
    }

    //string representation for console output debugging
    public String toString() {
        String temp = title + "\n\n"
                + "Ingredients: \n";
        for (Ingredient i : ingredients) {
            temp += i + "\n";
        }
        temp += "Instructions: \n"
                + instructions + "\n"
                + notes;
        return temp;
    }

}
