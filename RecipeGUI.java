
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Sarah Ball
 *
 * Note to self: look into to improved keyboard frame navigation for recipeGUI:
 * https://docs.oracle.com/javase/7/docs/api/java/awt/Container.html#setFocusTraversalPolicy%28java.awt.FocusTraversalPolicy%29
 */
public class RecipeGUI extends JFrame {

    //stores recipes components for later gui build
    private String title, ingredientString, instructions, notes;
    private ArrayList<Ingredient> ingredients = new ArrayList<>();

    //text areas for the display and editing content
    JTextArea titleTA, ingredientsTA, instructionsTA, notesTA;

    //allows GUI to know if it is adding a new recipe or editing an existing one
    Boolean isNew;
    //reference to RecipeBox for saving and adding
    RecipeBox box;
    Recipe recipe;

    /**
     * Constructor to create GUI off of existing recipe
     *
     * @param recipe
     * @param recipeBox
     */
    public RecipeGUI(Recipe recipe, RecipeBox recipeBox) {
        title = recipe.getTitle();
        ingredientString = recipe.getIngredientsString();
        instructions = recipe.getInstructions();
        notes = recipe.getNotes();

        initGUI();
        isNew = false;
        box = recipeBox;
        this.recipe = recipe;
    }

    /**
     * Constructor to create blank GUI for entering new recipes
     *
     * @param recipeBox
     */
    public RecipeGUI(RecipeBox recipeBox) {
        title = "New Recipe";
        ingredientString = "";
        ingredients = new ArrayList<>();
        instructions = "";
        notes = "";

        initGUI();
        isNew = true;
        box = recipeBox;
    }

    private void initGUI() {
        //frame setup
        setTitle(title);
        //close window, but not whole program
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //setup panels
        JPanel fullContainer = new JPanel();
        fullContainer.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        Insets margins = new Insets(10, 10, 10, 10);
        Font mainTextFont = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
        Font borderFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);

        //------------------Title textarea-------------------------//
        titleTA = new JTextArea();
        titleTA.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
        titleTA.setText(title);
        titleTA.setMargin(margins);

        //----------------Ingredients textarea--------------------//
        ingredientsTA = new JTextArea();
        ingredientsTA.setText(ingredientString);
        ingredientsTA.setLineWrap(true);
        ingredientsTA.setWrapStyleWord(true);
        ingredientsTA.setMargin(margins);
        ingredientsTA.setFont(mainTextFont);
        ingredientsTA.setEditable(false); //ingredients must be manually updated in form for now
        JScrollPane scrl1 = new JScrollPane(ingredientsTA) {
            public Dimension getPreferredSize() {
                return new Dimension(250, 400);
            }
        };
        // ingredient String panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        leftPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(null, "Ingredients: ",
                        TitledBorder.LEFT, TitledBorder.TOP, borderFont),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        leftPanel.add(scrl1);
        //ingredients edit button
        JButton ingEdit = new JButton("Edit Ingredients");
        ingEdit.addActionListener(e -> editIngredients());

        //----------------instructions textarea--------------------//
        instructionsTA = new JTextArea();
        instructionsTA.setText(instructions);
        instructionsTA.setLineWrap(true);
        instructionsTA.setWrapStyleWord(true);
        instructionsTA.setMargin(margins);
        instructionsTA.setFont(mainTextFont);
        JScrollPane scrl2 = new JScrollPane(instructionsTA) {
            public Dimension getPreferredSize() {
                return new Dimension(400, 400);
            }
        };
        //instructions panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        rightPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(null, "Instructions: ",
                        TitledBorder.LEFT, TitledBorder.TOP, borderFont),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        rightPanel.add(scrl2);

        //------------------notes text area------------------------//
        notesTA = new JTextArea();
        notesTA.setText(notes);
        notesTA.setLineWrap(true);
        notesTA.setWrapStyleWord(true);
        notesTA.setMargin(margins);
        notesTA.setFont(mainTextFont);
        JScrollPane scrl3 = new JScrollPane(notesTA) {
            public Dimension getPreferredSize() {
                return new Dimension(650, 100);
            }
        };
        //notes panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.PAGE_AXIS));
        bottomPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(null, "Extras: ",
                        TitledBorder.LEFT, TitledBorder.TOP, borderFont),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        bottomPanel.add(scrl3);

        //----------------Save and Delete Buttons--------------------//
        JButton save = new JButton("Save Recipe");
        save.addActionListener(e -> saveRecipe());
        JButton delete = new JButton("Delete Recipe");
        delete.addActionListener(e -> deleteRecipe());

        //----------------Assembling Components--------------------//
        //gridbag layout and constraints used to setup panel container
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = margins;
        //Top row
        c.gridy = 0;
        c.gridx = 0;
        c.gridwidth = 2;
        fullContainer.add(titleTA, c);
        //second row
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        fullContainer.add(leftPanel, c);
        c.gridx = 1;
        fullContainer.add(rightPanel, c);
        //third row
        c.gridy = 2;
        c.gridx = 0;
        fullContainer.add(ingEdit, c);
        //fourth row
        c.gridy = 3;
        c.gridwidth = 2;
        c.gridx = 0;
        fullContainer.add(bottomPanel, c);
        //fifth row
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridx = 0;
        fullContainer.add(delete, c);
        c.gridx = 1;
        fullContainer.add(save, c);

        //add vertial scroll around whole panel
        JScrollPane scrl = new JScrollPane(fullContainer,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrl);
        setVisible(true);
        pack();
    }

    private void editIngredients() {
        //data input needs parse test and error messages, 
        //but holding that off until later developement since I hope to eliminate
        //the need to declare number of ingredients up front
        int length = Integer.parseInt(
                JOptionPane.showInputDialog("How many ingredients do you need?"));
        new IngredientGUI(this, length);

    }

    private void saveRecipe() {

        if (isNew) {
            //pull text from GUI, build recipe, and add to list
            box.addRecipe(new Recipe(titleTA.getText(),
                    ingredients,
                    instructionsTA.getText(),
                    notesTA.getText()));
        } else {
            //update list
            Recipe toUpdate = box.recipes.get(box.recipes.indexOf(recipe));
            toUpdate.setTitle(titleTA.getText());
            toUpdate.setIngredients(ingredients);
            toUpdate.setInstructions(instructionsTA.getText());
            toUpdate.setNotes(notesTA.getText());
        }
    }

    private void deleteRecipe() {
        if (isNew) {
            //does nothing, wasn't saved yet
        } else {
            //remove from list
            box.removeRecipe(recipe);
        }
        //close window
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void updateIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
        String ingString = "";
        for (Ingredient i : ingredients) {
            ingString += i + "\n";
        }
        ingredientString = ingString;
        ingredientsTA.setText(ingredientString);
    }

    public String getTitle() {
        return title;
    }
}
