import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Extends JPanel to create a GUI menu for a profile with auditor
 * privileges to create a ballot with an arbitrary number of
 * candidates running for a position. Only one ballot can be
 * created per countyID. The ballot is inserted into the election
 * table and the candidates are inserted into the candidates table.
 * @author Kevin Dao, Cole Garton, Timothy Evans
 * @version 1.0.0, Dec 6 2020
 * @see JPanel
 */
public class CreateBallot extends JPanel {
    private String[] returnHomeLang = {"Return to Home", "Vuelve a casa", "Retourner à la maison"};
    private String[] addLang = {"Add >>>", "Agregar >>>", "Ajouter >>>"};
    private String[] confirmLang = {"Confirm Ballots", "Confirmar papeletas", "Confirmer les bulletins de vote"};
    private String[] candidatePositions = {"President", "Representative", "Senate", "Governor", "Mayor", "Sheriff"};
    JButton returnToHome;
    private JTextArea enterCandidateArea;
    private JTextArea showCandidatesArea;
    private final JComboBox<String> positionJComboBox;
    private JButton addButton;
    private JButton submitButton;
    private HashMap<String, ArrayList<String>> candidates;
    private int electionIndex;

    public CreateBallot() {
        setLayout(new GridBagLayout());
        setName("CreateBallot");
        GridBagConstraints c = new GridBagConstraints();

        GUIComponents.getDarkModeButton(7).addActionListener(e -> {
            if(GUIComponents.getDarkMode()) {
                GUIComponents.setDarkMode(false);
                GUIComponents.changeMode(false);
            }
            else {
                GUIComponents.setDarkMode(true);
                GUIComponents.changeMode(true);
            }
            refreshPanel();
        });
        c.insets = new Insets(10,20,0,20);  // padding
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = 0.5;
        add(GUIComponents.getDarkModeButton(7), c);

        GUIComponents.getLanguageComboBox(7).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JComboBox comboBox = (JComboBox) event.getSource();
                int selected = comboBox.getSelectedIndex();
                GUIComponents.setLanguageIndex(selected);

                returnToHome.setText(returnHomeLang[selected]);
                addButton.setText(addLang[selected]);
                submitButton.setText(confirmLang[selected]);

                GUIComponents.changeLanguage();
                GUIComponents.updateDarkModeButtonText();
                refreshPanel();
            }
        });
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = 0.2;
        c.insets = new Insets(10,20,0,20);  // padding
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        add(GUIComponents.getLanguageComboBox(7), c);

        candidates = new HashMap<>(); //key: position, value: array of candidates running for position

        c.fill = GridBagConstraints.HORIZONTAL;
        positionJComboBox = new JComboBox(candidatePositions);
        positionJComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                String position = comboBox.getSelectedItem().toString();
                showCandidatesArea.setText(""); //clear text
                for(String i : candidates.keySet()){ //show candidates for selected position
                    if(i == position){
                        for(int j = 0; j < candidates.get(i).size(); j++){
                            showCandidatesArea.append(candidates.get(i).get(j));
                            showCandidatesArea.append("\n");
                        }

                    }
                }
                refreshPanel();
            }
        });
        //set up hash map to store the candidates entered for each position
        for(int i = 0; i < positionJComboBox.getItemCount(); i++){
            ArrayList<String> a = new ArrayList<>();
            candidates.put(positionJComboBox.getItemAt(i), a);
        }
        c.insets = new Insets(10,20,0,20);  // padding
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = 0.5;
        add(positionJComboBox, c);


        Box box = Box.createHorizontalBox();
        String demo = "";

        enterCandidateArea = new JTextArea(demo, 10, 15);
        box.add(new JScrollPane(enterCandidateArea)); // add scrollpane

        addButton = new JButton("Add >>>");
        box.add(addButton);
        addButton.addActionListener(
                new ActionListener()
                {
                    // append text from enterCandidatesArea to showCandidatesArea
                    @Override
                    public void actionPerformed(ActionEvent event)
                    {
                        showCandidatesArea.append(enterCandidateArea.getText() + "\n");
                        ArrayList<String> candidatesArray = candidates.get(positionJComboBox.getSelectedItem().toString());
                        candidatesArray.add(enterCandidateArea.getText());

                        enterCandidateArea.setText("");
                        for(String i : candidates.keySet()) {
                            for(int j = 0; j < candidates.get(i).size(); j++){
                            }
                        }
                    }
                }
        );

        showCandidatesArea = new JTextArea(10, 15);
        showCandidatesArea.setEditable(false);
        box.add(new JScrollPane(showCandidatesArea)); // add scrollpane

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.ipady = 30;
        c.weightx = 0.2;
        c.insets = new Insets(10,10,0,10);  // padding
        c.anchor = GridBagConstraints.CENTER;
        add(box, c);

        submitButton = new JButton("Confirm Ballots");
        submitButton.addActionListener(
                new ActionListener()
                {
                    // append text from enterCandidatesArea to showCandidatesArea
                    @Override
                    public void actionPerformed(ActionEvent event)
                    {
                        if(Database.checkUniqueCountyID(Integer.parseInt(Database.getUserInfo().get(7)))){ //if no election for that county
                            electionIndex = insertElections(); //insert into election table
                            Database.insertCandidates(candidates, electionIndex); //insert into candidates table
                        }
                        else{
                            enterCandidateArea.setText("Ballot already created for " + Database.getUserInfo().get(5) + " county");
                        }
                        for(Map.Entry<String,ArrayList<String>> e : candidates.entrySet()){ //clear ballot creation
                            e.setValue(null );
                            e.setValue(new ArrayList<String>() );
                        }
                        showCandidatesArea.setText("");
                    }
                }
        );

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        c.ipady = 10;
        c.weightx = 0.5;
        c.insets = new Insets(40,20,0,20);  // padding
        add(submitButton, c);


        returnToHome = new JButton(returnHomeLang[GUIComponents.getLanguageIndex()]);
        returnToHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIComponents.getCardLayout().show(GUIComponents.getContentPanel(), "AUDITOR");
            }
        });
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 3;
        c.ipady = 10;
        c.weightx = 0.5;
        add(returnToHome, c);

    }

    private void refreshPanel(){
        revalidate();
        repaint();
    }

    private int insertElections(){
        int county = Integer.parseInt(Database.getUserInfo().get(7));
        int index = Database.insertElections(candidates, county); //returns AUTO_INCREMENT value
        return index;
    }
}
