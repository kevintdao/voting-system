import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class CreateBallot extends JPanel {
//TODO: add languageComboBox[7]
    private String[] returnHomeLang = {"Return to Home", "Vuelve a casa", "Retourner à la maison"};
    private String[] candidatePositions = {"President", "Representative", "Congress", "Governor", "Mayor", "Sheriff"};
    JButton returnToHome;
    private JTextArea enterCandidateArea;
    private JTextArea showCandidatesArea;
    private final JComboBox<String> positionJComboBox;
    private JButton addButton;
    private HashMap<String, String> candidates;

    //this needs to have a numher passed in and then make as many pages as necessary
    public CreateBallot() {
        setLayout(new BorderLayout());
        setName("Vote");

        candidates = new HashMap<>();

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


        Box box = Box.createHorizontalBox(); // create box
        String demo = "This is a demo string to\n" +
                "illustrate copying text\nfrom one textarea to \n" +
                "another textarea using an\nexternal event\n";

        enterCandidateArea = new JTextArea(demo, 10, 15);
        box.add(new JScrollPane(enterCandidateArea)); // add scrollpane

        addButton = new JButton("Add >>>");
        box.add(addButton); // add copy button to box
        addButton.addActionListener(
                new ActionListener() // anonymous inner class
                {
                    // appened text from enterCandidatesArea to showCandidatesArea
                    @Override
                    public void actionPerformed(ActionEvent event)
                    {
                        showCandidatesArea.append(enterCandidateArea.getText() + "\n");

                        candidates.put(positionJComboBox.getSelectedItem().toString(), enterCandidateArea.getText());
                        enterCandidateArea.setText(""); //clear
                    }
                } // end anonymous inner class
        ); // end call to addActionListener

        showCandidatesArea = new JTextArea(10, 15);
        showCandidatesArea.setEditable(false);
        box.add(new JScrollPane(showCandidatesArea)); // add scrollpane

        add(box); // add box to frame



        positionJComboBox = new JComboBox(candidatePositions);
        positionJComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                String position = comboBox.getSelectedItem().toString();
                showCandidatesArea.setText(""); //clear text
                for(String i : candidates.keySet()){ //show candidates for that position
                    if(i == position){
                        showCandidatesArea.append(i + "/n");
                    }
                }

                refreshPanel();

            }
        });
        add(positionJComboBox, BorderLayout.PAGE_START);

        //add(Options.getLanguageComboBox(4),BorderLayout.PAGE_START); //TODO: change to 6
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

    }
}
