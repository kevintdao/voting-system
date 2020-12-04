import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VotePage extends JPanel {
    private String[] returnHomeLang = {"Return to Home", "Vuelve a casa", "Retourner à la maison"};
    
    JButton returnToHome;

    //this needs to have a numher passed in and then make as many pages as necessary
    public VotePage() {
        setLayout(new BorderLayout());
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

        JTabbedPane tabs = new JTabbedPane();
        VoteTab panel1 = new VoteTab(4); //these will get made when the auditor creates his ballot
        VoteTab panel2 = new VoteTab(3);
        VoteTab panel3 = new VoteTab(6);
        VoteTab panel4 = new VoteTab(5);
        VoteTab panel5 = new VoteTab(3);
        VoteTab panel6 = new VoteTab(1);

        //gets amount of tabs from database here

        tabs.add("page 1",panel1);
        tabs.add("page 2",panel2);
        tabs.add("page 3",panel3);
        tabs.add("page 4",panel4);
        tabs.add("page 5",panel5);
        tabs.add("page 6",panel6);
        add(tabs, BorderLayout.CENTER);
        add(Options.getLanguageComboBox(4),BorderLayout.PAGE_START);
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
