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
    public static int electionIndex = 38;
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


        Options.getLanguageComboBox(5).addActionListener(new ActionListener() { //TODO: change to 6
            @Override
            public void actionPerformed(ActionEvent event) {
                JComboBox comboBox = (JComboBox) event.getSource();
                int selected = comboBox.getSelectedIndex();
                Options.setLanguageIndex(selected);

                returnToHome.setText(returnHomeLang[selected]);
                Options.changeLanguage();
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
                        insertElections();
                        insertCandidates();
                    }
                } // end anonymous inner class
        ); // end call to addActionListener

        add(box); // add box to frame




        add(positionJComboBox, BorderLayout.PAGE_START);

        //add(Options.getLanguageComboBox(4),BorderLayout.PAGE_START); // change to 6
        returnToHome = new JButton(returnHomeLang[Options.getLanguageIndex()]);
        returnToHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Options.getCardLayout().show(Options.getContentPanel(), "HOME");
                //this needs to update the progress bar
            }
        });
        add(returnToHome, BorderLayout.PAGE_END);
    }

    private void refreshPanel(){
        revalidate();
        repaint();
    }
    private void insertCandidates(){
        electionIDs = new int[6];
        int i = 0;

        for(String pos : candidates.keySet()){
            if(candidates.get(pos).size() > 0) {
                electionIDs[i] = electionIndex;
                electionIndex++;
            }
            i++;
        }
        Options.insertCandidates(candidates, electionIDs);
    }
    private void insertElections(){
        int county = 1;
        Options.insertElections(candidates, county);
    }
}
