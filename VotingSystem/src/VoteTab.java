import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;


public class VoteTab extends JPanel {
    private JRadioButton[] candidates;
    private JTextField writeInTextField;
    ButtonGroup candidateGroup;

    public VoteTab(int numCandidates) {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,20,0,20);  // padding
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;

        candidates = new JRadioButton[numCandidates];
        candidateGroup = new ButtonGroup();

        for (int i = 0; i < numCandidates; i++) {
            if (i+1 == numCandidates) {
                candidates[i] = new JRadioButton("Write in:");
            }
            else {
                candidates[i] = new JRadioButton("pulled from database");
            }
            candidateGroup.add(candidates[i]);
            c.gridy = i;

            add(candidates[i],c);
        }

        candidates[numCandidates-1].addItemListener(e -> {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        writeInTextField.setEditable(true);
                    }
                    else if (e.getStateChange() == ItemEvent.DESELECTED) {
                        writeInTextField.setEditable(false);
                    }
                }

        );
        
        c.gridy = numCandidates-1;
        c.gridx = 1;
        writeInTextField = new JTextField("Write In Candidate",10);
        writeInTextField.setEditable(false);
        add(writeInTextField,c);
    }
}
