import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Extends JPanel to create a GUI menu for a profile with auditor privileges
 * to navigate to the Profile, Landing, Media, or CreateBallot page.
 * @author Kevin Dao, Cole Garton, Timothy Evans
 * @version 1.0.0, Dec 6 2020
 * @see JPanel
 */
public class AuditorPage extends JPanel {
    private static final String[] languages = {"English", "Spanish", "French"};
    private final JComboBox<String> languagesJComboBox;
    private String[] auditorLang = {"Welcome Auditor", "Bienvenido Auditor", "Bienvenue Auditeur"};
    private String[] returnHomeLang = {"Return to Home", "Vuelve a Casa", "Retourner à la Maison"};
    private String[] viewResultsLang = {"View Results", "Ver Resultados", "Voir Les Résultats"};
    private String[] createBallotLang = {"Create Ballot", "Crear Boleta", "Créer un Bulletin de Vote"};
    private String[] profileLang = {"Profile", "Perfil", "Profil"};

    private JLabel auditorLabel;

    private JButton profileButton;
    private JButton createBallotButton;
    private JButton viewResultsButton;
    private JButton returnToHomeButton;

    public AuditorPage() {
        setLayout(new GridBagLayout());
        setName("Auditor");
        GridBagConstraints c = new GridBagConstraints();

        GUIComponents.getDarkModeButton(5).addActionListener(e -> {
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
        c.insets = new Insets(10,0,0,10);  // padding
        c.gridx = 1;
        c.gridy = 0;
        c.ipady = 10;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.PAGE_START;
        c.weightx = 0.2;
        add(GUIComponents.getDarkModeButton(5),c);

        c.insets = new Insets(10, 20, 0, 0);  // padding

        // language select component
        languagesJComboBox = new JComboBox<>(languages);
        GUIComponents.getLanguageComboBox(5).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                int selected = comboBox.getSelectedIndex();
                GUIComponents.setLanguageIndex(selected);

                // change labels to selected language
                auditorLabel.setText(auditorLang[selected]);
                returnToHomeButton.setText(returnHomeLang[selected]);
                viewResultsButton.setText(viewResultsLang[selected]);
                createBallotButton.setText(createBallotLang[selected]);
                profileButton.setText(profileLang[selected]);

                GUIComponents.changeLanguage();
                GUIComponents.updateDarkModeButtonText();
                refreshPanel();
            }
        });
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = 0.2;
        c.insets = new Insets(10, 10, 100, 20);  // padding
        add(GUIComponents.getLanguageComboBox(5), c);

        // welcome components
        auditorLabel = new JLabel(auditorLang[GUIComponents.getLanguageIndex()]);
        auditorLabel.setFont(new Font("Arial", Font.BOLD, 24));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.ipady = 10;
        c.ipadx = 50;
        c.weightx = 0.6;
        c.insets = new Insets(10, 20, 0, 20);  // padding
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        add(auditorLabel, c);


        profileButton = new JButton("Profile");
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GUIComponents.getCardLayout().show(GUIComponents.getContentPanel(), "PROFILE");
                GUIComponents.updateProfilePage();
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.ipady = 10;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10, 40, 0, 40);  // padding
        add(profileButton, c);


        createBallotButton = new JButton("Create Ballot");
        // change to BallotCreation page
        createBallotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIComponents.getCardLayout().show(GUIComponents.getContentPanel(), "CREATEBALLOT");
            }
        });
        c.gridx = 0;
        c.gridy = 2;
        c.ipady = 10;
        c.gridwidth = 3;
        add(createBallotButton, c);

        viewResultsButton = new JButton("View Results");
        c.gridx = 0;
        c.gridy = 3;
        c.ipady = 10;
        c.gridwidth = 3;
        add(viewResultsButton, c);
        viewResultsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database.getResult();
                GUIComponents.getCardLayout().show(GUIComponents.getContentPanel(), "MEDIA");
            }
        });

        returnToHomeButton = new JButton("Log out");
        c.gridx = 0;
        c.gridy = 4;
        c.ipady = 10;
        c.gridwidth = 3;
        add(returnToHomeButton, c);
        returnToHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIComponents.getCardLayout().show(GUIComponents.getContentPanel(), "LANDING");
                GUIComponents.clearAllInputs();
            }
        });
    }

    private void refreshPanel(){
        revalidate();
        repaint();
    }
}
