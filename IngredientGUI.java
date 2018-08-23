
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Sarah Ball
 */
public class IngredientGUI extends JFrame {

    private JTextField[][] entries;
    final static int AMOUNT = 0;
    final static int MEASURE = 1;
    final static int ITEM = 2;
    final static int PREP = 3;
    RecipeGUI rGUI;

    String measure, item, prep;

    public IngredientGUI(RecipeGUI rGUI, int ingrNum) {
        this.rGUI = rGUI;
        entries = new JTextField[ingrNum][4];
        initGUI();
    }

    private void initGUI() {
        //frame setup
        setTitle(rGUI.getTitle());
        //close window, but not whole program
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //setup panels
        JPanel fullContainer = new JPanel();
        fullContainer.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //shared setting variables
        Insets margins = new Insets(5, 5, 5, 5);
        Font mainTextFont = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
        int amountSize = 3, measureSize = 6, itemSize = 15, prepSize = 6;

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = margins;
        c.gridx = 0;
        c.gridy = 0;

        c.anchor = GridBagConstraints.CENTER;
        //header row
        fullContainer.add(new JLabel("Amount"), c);
        c.gridx = 1;
        fullContainer.add(new JLabel("Measure"), c);
        c.gridx = 2;
        fullContainer.add(new JLabel("Item"), c);
        c.gridx = 3;
        fullContainer.add(new JLabel("Preperation(optional)"), c);
        c.gridy = 1;
        for (JTextField[] fieldGroup : entries) {
            //specific setup
            fieldGroup[AMOUNT] = new JTextField(amountSize);
            fieldGroup[MEASURE] = new JTextField(measureSize);
            fieldGroup[ITEM] = new JTextField(itemSize);
            fieldGroup[PREP] = new JTextField(prepSize);
            //shared setup
            for (JTextField field : fieldGroup) {
                field.setFont(mainTextFont);
            }

            c.gridx = 0;
            fullContainer.add(fieldGroup[AMOUNT], c);
            c.gridx = 1;
            fullContainer.add(fieldGroup[MEASURE], c);
            c.gridx = 2;
            fullContainer.add(fieldGroup[ITEM], c);
            c.gridx = 3;
            fullContainer.add(fieldGroup[PREP], c);
            c.gridy++;
        }
        //----------------Save and Delete Buttons--------------------//
        JButton save = new JButton("Save Ingredients");
        save.addActionListener(e -> saveIngredients());
        fullContainer.add(save, c);

        //add vertial scroll around whole panel
        JScrollPane scrl = new JScrollPane(fullContainer,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrl);
        setVisible(true);
        pack();
    }

    private void saveIngredients() {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        for (JTextField[] fieldGroup : entries) {
            ingredients.add(new Ingredient(fieldGroup[AMOUNT].getText(),
                    fieldGroup[MEASURE].getText(),
                    fieldGroup[ITEM].getText(),
                    fieldGroup[PREP].getText())
            );
        }

        rGUI.updateIngredients(ingredients);
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));//close window
    }
}
