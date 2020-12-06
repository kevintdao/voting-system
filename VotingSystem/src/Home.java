import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Home extends JPanel {
    private String[] welcomeLang = {"Welcome!", "Bienvenido!", "Bienvenu!"};
    private String[] currProgLang = {"Current Progress: ", "Progreso actual: ", "Progrès en cours: "};
    private String[] viewProfLang = {"View Profile", "Ver perfil", "Voir le profil"};
    private String[] voteLang = {"Vote", "Votar", "Vote"};
    private String[] logOutLang = {"Log out", "Cerrar sesión", "Se déconnecter"};

    private JLabel progressLabel;
    private JProgressBar progressBar;

    private JButton viewProfileButton;
    private JButton voteButton;
    private JButton logOutButton;

    private JLabel welcomeBackLabel;

    public Home(){
        setLayout(new GridBagLayout());
        setName("Home");
        GridBagConstraints c = new GridBagConstraints();

        Options.getDarkModeButton(2).addActionListener(e -> {
            if(Options.getDarkMode()) {
                Options.setDarkMode(false);
                Options.changeMode(false);
            }
            else {
                Options.setDarkMode(true);
                Options.changeMode(true);
            }
            refreshPanel();
        });
        c.insets = new Insets(10,20,0,20);  // padding
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = 0.5;
        add(Options.getDarkModeButton(2), c);

        // language select component
        Options.getLanguageComboBox(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                int selected = comboBox.getSelectedIndex();
                Options.setLanguageIndex(selected);

                // change labels to selected language
                welcomeBackLabel.setText(welcomeLang[selected]);
                progressLabel.setText(currProgLang[selected]);
                viewProfileButton.setText(viewProfLang[selected]);
                voteButton.setText(voteLang[selected]);
                logOutButton.setText(logOutLang[selected]);

                Options.changeLanguage();
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
        add(Options.getLanguageComboBox(2), c);


        c.fill = GridBagConstraints.HORIZONTAL;

        // welcome components
        welcomeBackLabel = new JLabel(welcomeLang[Options.getLanguageIndex()]);
        welcomeBackLabel.setFont(new Font("Arial", Font.BOLD, 24));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        add(welcomeBackLabel, c);

        // progress bar components
        progressLabel = new JLabel(currProgLang[Options.getLanguageIndex()]);
        progressLabel.setFont(new Font("Arial", Font.BOLD, 16));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 10;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(20,20,100,20);  // padding
        add(progressLabel, c);

        progressBar = new JProgressBar();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        c.ipadx = 50;
        c.ipady = 10;
        c.weightx = 0.5;
        progressBar.setString("In process");
        progressBar.setStringPainted(true);
        add(progressBar, c);

        viewProfileButton = new JButton(viewProfLang[Options.getLanguageIndex()]);
        // change to profile page
        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Options.getCardLayout().show(Options.getContentPanel(), "PROFILE");
                Options.updateProfilePage();
            }
        });
        c.gridx = 0;
        c.gridy = 2;
        c.ipady = 10;
        c.gridwidth = 3;
        c.insets = new Insets(10,40,0,40);  // padding
        add(viewProfileButton, c);

        voteButton = new JButton(voteLang[Options.getLanguageIndex()]);
        c.gridx = 0;
        c.gridy = 3;
        c.ipady = 10;
        c.gridwidth = 3;
        add(voteButton, c);
        voteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Options.setUpTab();
                Options.getCardLayout().show(Options.getContentPanel(), "VOTE");
            }
        });

        logOutButton = new JButton(logOutLang[Options.getLanguageIndex()]);
        c.gridx = 0;
        c.gridy = 4;
        c.ipady = 10;
        c.gridwidth = 3;
        add(logOutButton, c);
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Options.getCardLayout().show(Options.getContentPanel(), "LANDING");
                Options.clearAllInputs();
                Options.removeAllTabs();
            }
        });
    }

    private void refreshPanel(){
        revalidate();
        repaint();
    }
}