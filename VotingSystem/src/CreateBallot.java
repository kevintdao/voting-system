import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class CreateBallot extends JPanel {
    //TODO: language functionality
    private String[] returnHomeLang = {"Return to Home", "Vuelve a casa", "Retourner à la maison"};
    private String[] candidatePositions = {"President", "Representative", "Congress", "Governor", "Mayor", "Sheriff"};
    JButton returnToHome;
    private JTextArea enterCandidateArea;
    private JTextArea showCandidatesArea;
    private final JComboBox<String> positionJComboBox;
    private JButton addButton;
    private JButton submitButton;
    private HashMap<String, ArrayList<String>> candidates;
    private int electionIndex;
    private int[] electionIDs;


    //this needs to have a numher passed in and then make as many pages as necessary
    public CreateBallot() {
        setLayout(new BorderLayout());
        setName("Vote");

        candidates = new HashMap<>(); //key: position, value: array of candidates running for position

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


        GUIComponents.getLanguageComboBox(5).addActionListener(new ActionListener() { //TODO: change to 6
            @Override
            public void actionPerformed(ActionEvent event) {
                JComboBox comboBox = (JComboBox) event.getSource();
                int selected = comboBox.getSelectedIndex();
                GUIComponents.setLanguageIndex(selected);

                returnToHome.setText(returnHomeLang[selected]);
                GUIComponents.changeLanguage();
                refreshPanel();
            }
        });

        //From fig26_47_48
        Box box = Box.createHorizontalBox(); // create box
        String demo = "This is a demo string";

        enterCandidateArea = new JTextArea(demo, 10, 15);
        box.add(new JScrollPane(enterCandidateArea)); // add scrollpane

        addButton = new JButton("Add >>>");
        box.add(addButton); // add copy button to box
        addButton.addActionListener(
                new ActionListener() // anonymous inner class
                {
                    // append text from enterCandidatesArea to showCandidatesArea
                    @Override
                    public void actionPerformed(ActionEvent event)
                    {
                        System.out.println(electionIndex);
                        showCandidatesArea.append(enterCandidateArea.getText() + "\n");
                        ArrayList<String> candidatesArray = candidates.get(positionJComboBox.getSelectedItem().toString());
                        candidatesArray.add(enterCandidateArea.getText());

                        enterCandidateArea.setText(""); //clear
                        for(String i : candidates.keySet()) {
                            System.out.println(i + ": ");
                            for(int j = 0; j < candidates.get(i).size(); j++){
                                System.out.println(candidates.get(i).get(j));
                            }
                        }
                        System.out.println(Database.getUserInfo().get(7));
                    }
                } // end anonymous inner class
        ); // end call to addActionListener

        showCandidatesArea = new JTextArea(10, 15);
        showCandidatesArea.setEditable(false);
        box.add(new JScrollPane(showCandidatesArea)); // add scrollpane

        submitButton = new JButton("Confirm Ballots");
        box.add(submitButton); // add copy button to box
        submitButton.addActionListener(
                new ActionListener() // anonymous inner class
                {
                    // append text from enterCandidatesArea to showCandidatesArea
                    @Override
                    public void actionPerformed(ActionEvent event)
                    {
                        electionIndex = insertElections();
                        Database.insertCandidates(candidates, electionIndex);
                    }
                } // end anonymous inner class
        ); // end call to addActionListener

        add(box); // add box to frame




        add(positionJComboBox, BorderLayout.PAGE_START);

        //add(GUIComponents.getLanguageComboBox(4),BorderLayout.PAGE_START); // change to 6
        returnToHome = new JButton(returnHomeLang[GUIComponents.getLanguageIndex()]);
        returnToHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIComponents.getCardLayout().show(GUIComponents.getContentPanel(), "AUDITOR");
                //this needs to update the progress bar
            }
        });
        add(returnToHome, BorderLayout.PAGE_END);
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
