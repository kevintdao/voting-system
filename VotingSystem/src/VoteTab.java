import javax.swing.*;
import java.awt.*;


public class VoteTab extends JPanel {
    private JRadioButton[] candidates;
    private JTextField writeInTextField;
    ButtonGroup candidateGroup;

    public VoteTab(int numCandidates) {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,20,0,20);  // padding
        c.anchor = GridBagConstraints.CENTER;

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

        c.gridy = numCandidates;
        c.gridx = 1;
        c.weightx = 0.8;
        writeInTextField = new JTextField(10);
        add(writeInTextField,c);
        writeInTextField.addActionListener(
                event -> {
                    
                }
        );
    }
}
