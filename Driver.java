
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 *
 * @author Sarah Ball
 */
public class Driver extends JFrame {

    public static RecipeBox box;
    public static GroceryList list;
    public static JButton addRecipe;
    public static JButton seeList;
    public static JPanel recipePanel;
    public static GridBagConstraints c;

    public static void main(String[] args) {
        box = new RecipeBox();//recipe box currently automatically loads previous recipes
        //Future setup can allow file selection choice of saved recipe box with
        //possible access to previous saved archives
        list = new GroceryList();
        Driver d = new Driver();

        d.mainGUI();
    }

    private void mainGUI() {
        //frame setup
        setTitle("Recipe Box");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //on close first save object state
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                box.saveRecipes();
            }
        });
        setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.insets = new Insets(3, 3, 3, 3);

        //Recipe display panel
        recipePanel = new JPanel();
        updateRecipeDisplay();

        //Grocery list
        seeList = new JButton("See Grocery List");
        seeList.addActionListener(e -> list.viewList());
        c.gridx = 0;
        c.gridy = 0;
        add(seeList, c);

        //add recipe button
        addRecipe = new JButton("Add Recipe");
        addRecipe.addActionListener(e -> addRecipe());
        c.gridx = 0;
        c.gridy = 1;
        add(addRecipe, c);

        c.gridy = 2;
        add(recipePanel, c);

        setSize(500, 500);
        setVisible(true);
    }

    private void updateRecipeDisplay() {
        remove(recipePanel);
        recipePanel = new JPanel();
        recipePanel.setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        //add row for each recipe + button
        for (Recipe r : box.recipes) {
            recipePanel.add(new JLabel(r.getTitle()), c);

            JButton openB = new JButton("Open Recipe");
            //add listener to open and 
            //another to monitor when that window closes for updates
            openB.addActionListener(e -> {
                RecipeGUI temp = new RecipeGUI(r, box);
                temp.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        updateRecipeDisplay();
                    }
                });
            });
            c.gridx = 2;
            recipePanel.add(openB, c);
            JButton addB = new JButton("Add to List");
            addB.addActionListener(e -> addToList(r));
            c.gridx = 3;
            recipePanel.add(addB, c);
            c.gridx = 0; //reset x
            c.gridy++; //move down a row for next loop
        }
        //add to main frame
        c.gridx = 0;
        c.gridy = 2;
        add(recipePanel, c);
        repaint();
        revalidate();
        // pack(); //will shrink window, still working on how to adjust size properly
        //for new additions

    }

    private void addToList(Recipe r) {
        list.addToList(r);
        //get ingredients

    }

    private void addRecipe() {
        RecipeGUI recipeInput = new RecipeGUI(box);
        //listen for window close
        recipeInput.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                updateRecipeDisplay();
            }
        });
    }
}
