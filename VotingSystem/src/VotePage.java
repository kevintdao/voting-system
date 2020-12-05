import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VotePage extends JPanel {
    private String[] returnHomeLang = {"Return to Home", "Vuelve a casa", "Retourner à la maison"};
    private VoteTab[] tabArray;
    
    JButton returnToHome;

    //this needs to have a number passed in and then make as many pages as necessary
    public VotePage() {
        setLayout(new BorderLayout());
        setName("Vote");
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
        add(Options.getLanguageComboBox(4),BorderLayout.PAGE_START);

        JTabbedPane tabs = new JTabbedPane();
        tabArray = new VoteTab[5]; //this will be updated with same number as below
        for (int i = 1; i < 5; i++) { // this loop will loop for some amount from database
            String title = "from database";
            tabArray[i] = new VoteTab(5); //this will be updated from database
            tabs.add(title,tabArray[i]);
        }
        add(tabs, BorderLayout.CENTER);

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
}
