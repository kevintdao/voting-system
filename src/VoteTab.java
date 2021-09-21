import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

/**
 * Extends JPanel to create a tab displaying a ballot of candidates running
 * for one position. Supports varying quantity of candidates running for same
 * race.
 * @author Kevin Dao, Cole Garton, Timothy Evans
 * @version 1.0.0, Dec 6 2020
 * @see JPanel
 */
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
            candidates[i] = new JRadioButton("pulled from database");
            candidateGroup.add(candidates[i]);
            c.gridy = i;

            add(candidates[i],c);
        }
    }

    public JRadioButton getCandiateButton(int i){
        return candidates[i];
    }
}
