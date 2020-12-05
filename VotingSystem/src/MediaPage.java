import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;


public class MediaPage extends JPanel
{
    private String[] candidatePositions = {"President", "Representative", "Congress", "Governor", "Mayor", "Sheriff"};
    private final JLabel resultsLabel;
    private final JComboBox<String> positionJComboBox;
    private final JButton backButton;
    private final JButton updateButton;
    private JTextArea textArea;

    //private static final String[] graphs = {"Numerical Results", "Bar Graph", "Pie Chart"};
    private String[] resultsLang = {"Results", "Resultados", "Résultats"};
    private String[] backLang = {"<- Back", "<- Atrás", "<- Retour"};
    private String[] updateLang = {"Update", "Actualizar", "Mise à Jour"};

    public MediaPage() {
        setLayout(new GridBagLayout()); // set frame layout
        setName("Media");
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 20, 0, 20);  // padding
        c.fill = GridBagConstraints.HORIZONTAL;

        //graphsJComboBox = new JComboBox<String>(graphs); // set up JComboBox
        //graphsJComboBox.setMaximumRowCount(3); // display three rows

        resultsLabel = new JLabel(resultsLang[Options.getLanguageIndex()]);
        resultsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.ipady = 10;
        c.weightx = 0.8;
        c.anchor = GridBagConstraints.PAGE_START;
        add(resultsLabel, c);

        JLabel placeholder = new JLabel("");
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = 0.5;
        add(placeholder, c);

        Options.getLanguageComboBox(6).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                int selected = comboBox.getSelectedIndex();
                Options.setLanguageIndex(selected);

                // change labels to selected language
                resultsLabel.setText(resultsLang[selected]);
                backButton.setText(backLang[selected]);

                Options.changeLanguage();
                refreshPanel();

            }
        });

        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = 0.2;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        add(Options.getLanguageComboBox(6), c);

        positionJComboBox = new JComboBox(candidatePositions);
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = 0.2;
        add(positionJComboBox, c);

        textArea = new JTextArea();
        String contents = "RESULTS\n Position: \n Candidate: <name> <numvotes>";
        textArea.setRows(10);
        textArea.setText(contents);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        c.gridheight = 3;
        c.ipadx = 10;
        c.ipady = 50;
        c.insets = new Insets(10, 20, 40, 20);  // padding
        add(textArea, c);


        backButton = new JButton(backLang[Options.getLanguageIndex()]);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Options.getCardLayout().show(Options.getContentPanel(), "AUDITOR");
                System.out.println("Auditor page");
            }
        });
        c.gridx = 0;
        c.gridy = 5;
        c.ipady = 10;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LAST_LINE_START;
        add(backButton, c);


        updateButton = new JButton(updateLang[Options.getLanguageIndex()]);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //update()
            }
        });
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 2;
        add(updateButton, c);

        // JLabel constructor with string and alignment arguments
        //add(label1); // add label1 to JFrame

        //register handler and add listener
        ItemHandler itemHandler = new ItemHandler();
        //graphsJComboBox.addItemListener(itemHandler);
        positionJComboBox.addItemListener(itemHandler);
    }
    public void updateResults(){
        //TODO: query db and update textArea
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
            String string = event.getItem().toString();
            // determine whether item selected
            if (event.getStateChange() == ItemEvent.SELECTED){
                //label1.setText("Now displaying results as " + event.getItem().toString());
                //TODO: update with database queries
                textArea.setText("RESULTS\n Position: " + string + "\n Candidate: <name> <numvotes>");
            }

        }
    }

    private void refreshPanel(){
        revalidate();
        repaint();
    }
}