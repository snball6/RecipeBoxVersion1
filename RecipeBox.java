
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 *
 * @author Sarah Ball
 */
public class RecipeBox {

    public ArrayList<Recipe> recipes; //ideally should be private, haven't refactored yet

    public RecipeBox() {
        recipes = new ArrayList<Recipe>();
        loadRecipes();
    }

    public void addRecipe(Recipe r) {
        recipes.add(r);
    }

    public void removeRecipe(Recipe r) {
        recipes.remove(r);
    }

    public void printRecipes() {
        for (Recipe r : recipes) {
            System.out.println(r.getTitle());
        }
    }

    //old tab delimited read in
    private void loadRecipes() {

        scanInRecipes();

    }

    //To get around unsafe "uses unchecked or unsafe operations." error due
    //To casting seralized object
    @SuppressWarnings("unchecked")
    private void scanInRecipes() {
        try {
            //input objects
            FileInputStream fileStream = new FileInputStream("RecipeBox.ser");
            ObjectInputStream os = new ObjectInputStream(fileStream);
            //read in and assign to temp variable
            Object readIn = os.readObject();
            //cast to proper variable and assign to 
            //wrapped in if to avoid unchecked or unsafe operations error
            recipes = (ArrayList<Recipe>) readIn;
            os.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Previous recipe box not found. \n"
                    + "Empty Box provided.");
        } catch (ClassNotFoundException ex2) {
            System.out.println(ex2.getMessage());
        }
    }

    public void saveRecipes() {
        try {
            FileOutputStream fileStream = new FileOutputStream("RecipeBox.ser");
            //Note to future updates, try archiving this with a date or allow
            //save options to allow for better archiving and saving of data and
            //prevent destructive overwrites
            ObjectOutputStream os = new ObjectOutputStream(fileStream);
            os.writeObject(recipes);
            os.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
