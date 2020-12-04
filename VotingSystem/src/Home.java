import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JPanel {
    private JComboBox<String> languageSelect;
    private String[] languages = {"English", "Spanish", "French"};
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
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,20,0,20);  // padding
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

        JLabel placeholder = new JLabel("");
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = 0.5;
        add(placeholder, c);

        // language select component
        languageSelect = new JComboBox<>(languages);
        languageSelect.setSelectedIndex(Options.getLanguageIndex()); // set the current language to selected language from previous pages
        languageSelect.addActionListener(new ActionListener() {
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
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = 0.2;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        add(languageSelect, c);

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
            }
        });
    }
}