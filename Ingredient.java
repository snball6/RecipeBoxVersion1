
import java.io.Serializable;

/**
 *
 * @author Sarah Ball
 */
public class Ingredient implements Serializable, Comparable<Ingredient> {

    private String amount; //currently stored as string so that 1/4 tsp or 1 1/2 cup 
    //will display as it is used by human-user. Storing & management of measures
    //with float values to allow "combined" grocery list measurements is a feature 
    //I hope to add in future versions.
    private String measure, item, prep;

    /*
    * For full detailed ingredient addition
     */
    public Ingredient(String amount, String measure, String item, String prep) {
        this.amount = amount;
        this.measure = measure;
        this.item = item;
        this.prep = prep;
    }

    public String getAmount() {
        return amount;
    }

    public String getMeasure() {
        return measure;
    }

    public String getItem() {
        return item;
    }

    public String getPrep() {
        return prep;
    }

    /*
    * For full detailed ingredient addition
     */
    public Ingredient(String amount, String measure, String item) {
        this.amount = amount;
        this.measure = measure;
        this.item = item;
        this.prep = "";
    }

    /*
    * For basic, undivided version of ingredient item
     */
    public Ingredient(String item) {
        this.amount = "";
        this.measure = "";
        this.item = item;
        this.prep = "";
    }

    public String toString() {
        //Assemble string based on available data to avoid extra white space
        //For instance "2 eggs" does not need measurement
        //or "Salt and Pepper, to taste" does not need amount or measure
        String temp = "";
        if (!amount.equals("")) {
            temp += amount + " ";
        }
        if (!measure.equals("")) {
            temp += measure + " ";
        }
        if (!item.equals("")) {
            temp += item;
        }
        if (!prep.equals("")) {
            temp += ", " + prep;
        }

        return temp;
    }

    @Override
    public int compareTo(Ingredient in) {
        //sort alphabetically by item - i.e. "1/2 cup milk" sort by "milk"
        return item.toLowerCase().compareTo(in.getItem().toLowerCase());
    }
}
