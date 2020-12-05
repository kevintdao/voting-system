import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VotePage extends JPanel {
    private String[] returnHomeLang = {"Return to Home", "Vuelve a casa", "Retourner à la maison"};
    private VoteTab[] tabArray;
    
    JButton returnToHome;
    JButton voteButton;

    //this needs to have a number passed in and then make as many pages as necessary
    public VotePage() {
        setLayout(new GridBagLayout());
        setName("Vote");
        GridBagConstraints c = new GridBagConstraints();

        Options.getLanguageComboBox(4).addActionListener(new ActionListener() {
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
        c.insets = new Insets(10,20,0,20);
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 0.2;
        c.ipady = 10;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        add(Options.getLanguageComboBox(4), c);

        c.gridx = 0;
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

        JTabbedPane tabs = new JTabbedPane();
        tabArray = new VoteTab[5]; //this will be updated with same number as below
        for (int i = 1; i < 5; i++) { // this loop will loop for some amount from database
            String title = "from database";
            tabArray[i] = new VoteTab(5); //this will be updated from database
            tabs.add(title,tabArray[i]);
        }
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1.0;
        c.ipady = 80;
        c.gridwidth = 3;
        c.insets = new Insets(10,10,0,10);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        add(tabs, c);

        returnToHome = new JButton(returnHomeLang[Options.getLanguageIndex()]);
        returnToHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Options.getCardLayout().show(Options.getContentPanel(), "HOME");
                //this needs to update the progress bar
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
}
