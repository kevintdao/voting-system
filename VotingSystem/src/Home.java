import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Extends JPanel to create a GUI menu for navigating to Profile, Vote, and Landing pages
 * includes language selection, dark mode, voting progress bar, and blocking vote access
 * if user has already voted.
 * @author Kevin Dao, Cole Garton, Timothy Evans
 * @version 1.0.0, Dec 6 2020
 * @see JPanel
 */
public class Home extends JPanel {
    private String[] welcomeLang = {"Welcome!", "Bienvenido!", "Bienvenu!"};
    private String[] currProgLang = {"Current Progress: ", "Progreso actual: ", "Progrès en cours: "};
    private String[] viewProfLang = {"View Profile", "Ver perfil", "Voir le profil"};
    private String[] voteLang = {"Vote", "Votar", "Vote"};
    private String[] logOutLang = {"Log out", "Cerrar sesión", "Se déconnecter"};

    private JLabel progressLabel;

    private JButton viewProfileButton;
    private JButton voteButton;
    private JButton logOutButton;

    private JLabel welcomeBackLabel;

    public Home(){
        setLayout(new GridBagLayout());
        setName("Home");
        GridBagConstraints c = new GridBagConstraints();

        GUIComponents.getDarkModeButton(2).addActionListener(e -> {
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
        c.insets = new Insets(10,20,0,20);  // padding
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = 0.5;
        add(GUIComponents.getDarkModeButton(2), c);

        // language select component
        GUIComponents.getLanguageComboBox(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                int selected = comboBox.getSelectedIndex();
                GUIComponents.setLanguageIndex(selected);

                // change labels to selected language
                welcomeBackLabel.setText(welcomeLang[selected]);
                progressLabel.setText(currProgLang[selected]);
                viewProfileButton.setText(viewProfLang[selected]);
                voteButton.setText(voteLang[selected]);
                logOutButton.setText(logOutLang[selected]);

                GUIComponents.changeLanguage();
                GUIComponents.updateDarkModeButtonText();
                refreshPanel();
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = 0.2;
        c.insets = new Insets(10,20,0,20);  // padding
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        add(GUIComponents.getLanguageComboBox(2), c);


        c.fill = GridBagConstraints.HORIZONTAL;

        // welcome components
        welcomeBackLabel = new JLabel(welcomeLang[GUIComponents.getLanguageIndex()]);
        welcomeBackLabel.setFont(new Font("Arial", Font.BOLD, 24));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        add(welcomeBackLabel, c);

        // progress bar components
        progressLabel = new JLabel(currProgLang[GUIComponents.getLanguageIndex()]);
        progressLabel.setFont(new Font("Arial", Font.BOLD, 16));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 10;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(20,20,100,20);  // padding
        add(progressLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        c.ipadx = 50;
        c.ipady = 10;
        c.weightx = 0.5;
        GUIComponents.getProgressBar().setString("NOT STARTED");
        GUIComponents.getProgressBar().setStringPainted(true);
        add(GUIComponents.getProgressBar(), c);

        viewProfileButton = new JButton(viewProfLang[GUIComponents.getLanguageIndex()]);
        // change to profile page
        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIComponents.getCardLayout().show(GUIComponents.getContentPanel(), "PROFILE");
                GUIComponents.updateProfilePage();
            }
        });
        c.gridx = 0;
        c.gridy = 2;
        c.ipady = 10;
        c.gridwidth = 3;
        c.insets = new Insets(10,40,0,40);  // padding
        add(viewProfileButton, c);

        voteButton = new JButton(voteLang[GUIComponents.getLanguageIndex()]);
        c.gridx = 0;
        c.gridy = 3;
        c.ipady = 10;
        c.gridwidth = 3;
        add(voteButton, c);
        voteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // set up the voting tabs
                GUIComponents.setUpTab();

                String currentVotingStatus = Database.getVotingStatus();

                // check voting status
                if(currentVotingStatus == null){
                    Database.updateVotingStatus("IN PROGRESS");
                }
                else if(currentVotingStatus.equals("FINISHED")){
                    JOptionPane.showMessageDialog(GUIComponents.getContentPanel(), "You can't vote more than once!","You already voted!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // change progress bar and move to vote page
                GUIComponents.getProgressBar().setString(currentVotingStatus);
                GUIComponents.getCardLayout().show(GUIComponents.getContentPanel(), "VOTE");
            }
        });

        logOutButton = new JButton(logOutLang[GUIComponents.getLanguageIndex()]);
        c.gridx = 0;
        c.gridy = 4;
        c.ipady = 10;
        c.gridwidth = 3;
        add(logOutButton, c);
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // move to landing page and remove the voting tabs, clear all inputs
                GUIComponents.getCardLayout().show(GUIComponents.getContentPanel(), "LANDING");
                GUIComponents.clearAllInputs();
                GUIComponents.emptyTabArray();
                GUIComponents.removeAllTabs();
            }
        });
    }

    private void refreshPanel(){
        revalidate();
        repaint();
    }
}