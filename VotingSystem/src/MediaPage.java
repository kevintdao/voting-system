import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

/**
 *
 * @version 1.0.0, Oct 2 2020
 * @see JFrame
 */
public class MediaPage extends JFrame
{
    /** JLabel to label the number entry */
    private final JLabel label1; // JLabel with just text
    /** JComboBox to select which events to print to screen */
    private final JComboBox<String> eventsJComboBox;

    /** array of strings for selecting data visualization*/
    private static final String[] graphs =
            {"Numerical Results", "Bar Graph", "Pie Chart"};


    /**
     * LabelFrame constructor that adds JLabel and JComboBox to JFrame
     */
    public MediaPage() {
        super("Media Page");
        setLayout(new FlowLayout()); // set frame layout

        eventsJComboBox = new JComboBox<String>(graphs); // set up JComboBox
        eventsJComboBox.setMaximumRowCount(3); // display three rows
        add(eventsJComboBox, BorderLayout.NORTH); // add combobox to JFrame


        // JLabel constructor with string and alignment arguments
        label1 = new JLabel("Now displaying results as Numerical Results", SwingConstants.LEFT);
        add(label1); // add label1 to JFrame



        //register handler and add listener
        ItemHandler itemHandler = new ItemHandler();
        eventsJComboBox.addItemListener(itemHandler);

    }

    /**
     * Extends ItemListener to handle the event of selecting from JComboBox.
     */
    private class ItemHandler implements ItemListener {
        /**
         * handle ItemEvent and display information
         * @param event     ItemEvent of JComboBox selection
         */
        @Override
        public void itemStateChanged(ItemEvent event)
        {
            String string = event.toString();
            // determine whether item selected
            if (event.getStateChange() == ItemEvent.SELECTED)
                label1.setText("Now displaying results as " + event.getItem().toString());
        }
    }
}