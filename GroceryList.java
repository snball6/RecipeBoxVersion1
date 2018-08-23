
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Sarah Ball
 */
public class GroceryList {

    ArrayList<Recipe> recipes;//eventually also save and allow for optional 
    //display of the "source" recipe of all ingredients
    ArrayList<Ingredient> groceries;

    public GroceryList() {
        groceries = new ArrayList<>();
        recipes = new ArrayList<>();
    }

    public void addToList(Recipe r) {
        recipes.add(r);
        groceries.addAll(r.getIngredients());
    }

    public void viewList() {
        new GroceryGUI(this);
    }
   
    public String toString(){
        Collections.sort(groceries); //alphabetical by item name
        String temp = "";
        for(Ingredient i: groceries){
            temp+=i+"\n";
        }
        return temp;
    }

}
