import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Extends JPanel to create a GUI page for voting for a candidate. Sends the vote
 * to be inserted into votes table and updates voting progress. Includes dark mode
 * and language selection.
 * @author Kevin Dao, Cole Garton, Timothy Evans
 * @version 1.0.0, Dec 6 2020
 * @see JPanel
 */
public class VotePage extends JPanel {
    private String[] returnHomeLang = {"Return to Home", "Vuelve a casa", "Retourner à la maison"};
    private String[] submitVoteLang = {"Submit Vote", "Enviar voto", "Soumettre votre vote"};
    
    JButton returnToHome;
    JButton voteButton;

    public VotePage() {
        setLayout(new GridBagLayout());
        setName("Vote");
        GridBagConstraints c = new GridBagConstraints();

        GUIComponents.getDarkModeButton(4).addActionListener(e -> {
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
        c.insets = new Insets(10,40,0,40);  // padding
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 10;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.PAGE_START;
        c.weightx = 0.2;
        add(GUIComponents.getDarkModeButton(4),c);


        GUIComponents.getLanguageComboBox(4).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JComboBox comboBox = (JComboBox) event.getSource();
                int selected = comboBox.getSelectedIndex();
                GUIComponents.setLanguageIndex(selected);

                returnToHome.setText(returnHomeLang[selected]);
                voteButton.setText(submitVoteLang[selected]);

                GUIComponents.changeLanguage();
                GUIComponents.updateDarkModeButtonText();
                refreshPanel();
            }
        });
        c.insets = new Insets(10,20,0,20);
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 0.2;
        c.ipady = 10;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        add(GUIComponents.getLanguageComboBox(4), c);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.5;
        c.ipady = 10;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        add(new JLabel(""), c);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.5;
        c.ipady = 10;
        c.anchor = GridBagConstraints.PAGE_START;
        add(new JLabel(""), c);


        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1.0;
        c.ipady = 80;
        c.gridwidth = 3;
        c.insets = new Insets(10,10,0,10);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        add(GUIComponents.getTabs(), c);

        returnToHome = new JButton(returnHomeLang[GUIComponents.getLanguageIndex()]);
        returnToHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIComponents.getCardLayout().show(GUIComponents.getContentPanel(), "HOME");
            }
        });
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1.0;
        c.ipady = 10;
        c.gridwidth = 1;
        c.insets = new Insets(10,20,0,20);
        add(returnToHome, c);

        voteButton = new JButton("Submit Vote");
        voteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ArrayList<String> selected = getSelectedButtons();
                // check if the user voted for at least 1 candidate
                if(selected.size() == 0)
                {
                    JOptionPane.showMessageDialog(GUIComponents.getContentPanel(), "You must vote for at least 1 candidate","No candidate selected", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // finished voting
                Database.submitVote(selected);
                JOptionPane.showMessageDialog(GUIComponents.getContentPanel(), "Thank you for voting!","Vote Submitted!", JOptionPane.INFORMATION_MESSAGE);
                Database.updateVotingStatus("FINISHED");
                GUIComponents.getProgressBar().setString("FINISHED");

                GUIComponents.getCardLayout().show(GUIComponents.getContentPanel(), "HOME");
            }
        });
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1.0;
        c.ipady = 10;
        c.gridwidth = 2;
        add(voteButton, c);
    }

    private void refreshPanel(){
        revalidate();
        repaint();
    }

    // get the list of name of the selected radio buttons
    private ArrayList<String> getSelectedButtons(){
        ArrayList<String> selected = new ArrayList<>();
        for(int i = 0; i < GUIComponents.getTabArray().size(); i++){
            VoteTab currentTab = GUIComponents.getTabArray().get(i);

            Component[] components = currentTab.getComponents();

            for(Component c : components){
                if(c instanceof JRadioButton){
                    JRadioButton radioButton = (JRadioButton) c;
                    if(radioButton.isSelected()){
                        selected.add(radioButton.getText());
                    }
                }
            }
        }
        return selected;
    }
}
