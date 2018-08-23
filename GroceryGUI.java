
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Sarah Ball
 */
public class GroceryGUI extends JFrame {

    JTextArea displayArea;
    GroceryList list;

    public GroceryGUI(GroceryList list) {
        this.list = list;
        initGUI();

    }

    private void initGUI() {
        //frame setup
        setTitle("Grocery List");
        //close window, but not whole program
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //setup panels
        JPanel fullContainer = new JPanel();
        fullContainer.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //shared setting variables
        Insets margins = new Insets(5, 5, 5, 5);
        Font mainTextFont = new Font(Font.SANS_SERIF, Font.PLAIN, 16);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = margins;

        displayArea = new JTextArea();
        displayArea.setText(list.toString());
        displayArea.setLineWrap(true);
        displayArea.setWrapStyleWord(true);
        displayArea.setMargin(margins);
        displayArea.setFont(mainTextFont);
        displayArea.setEditable(false); //ingredients cannot be update at this time
        JScrollPane scrl1 = new JScrollPane(displayArea) {
            public Dimension getPreferredSize() {
                return new Dimension(400, 500);
            }
        };
        c.gridx = 0;
        c.gridy = 0;
        fullContainer.add(scrl1, c);

        //add vertial scroll around whole panel
        JScrollPane scrl = new JScrollPane(fullContainer,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrl);
        setVisible(true);
        pack();
    }
}
