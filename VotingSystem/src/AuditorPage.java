import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        Options.getDarkModeButton(5).addActionListener(e -> {
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
        c.insets = new Insets(10,0,0,10);  // padding
        c.gridx = 1;
        c.gridy = 0;
        c.ipady = 10;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.PAGE_START;
        c.weightx = 0.2;
        add(Options.getDarkModeButton(5),c);

        c.insets = new Insets(10, 20, 0, 20);  // padding

        // language select component
        languagesJComboBox = new JComboBox<>(languages);
        Options.getLanguageComboBox(5).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                int selected = comboBox.getSelectedIndex();
                Options.setLanguageIndex(selected);

                // change labels to selected language
                auditorLabel.setText(auditorLang[selected]);
                returnToHomeButton.setText(returnHomeLang[selected]);
                viewResultsButton.setText(viewResultsLang[selected]);
                createBallotButton.setText(createBallotLang[selected]);
                profileButton.setText(profileLang[selected]);

                Options.changeLanguage();
                refreshPanel();
            }
        });
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = 0.2;
        c.insets = new Insets(10, 10, 100, 20);  // padding
        add(Options.getLanguageComboBox(5), c);

        // welcome components
        auditorLabel = new JLabel(auditorLang[Options.getLanguageIndex()]);
        auditorLabel.setFont(new Font("Arial", Font.BOLD, 20));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.ipady = 10;
        c.ipadx = 50;
        c.weightx = 0.6;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        add(auditorLabel, c);


        profileButton = new JButton("Profile");
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Options.getCardLayout().show(Options.getContentPanel(), "PROFILE");
                Options.updateProfilePage();
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
                Options.getCardLayout().show(Options.getContentPanel(), "CREATEBALLOT");
                System.out.println("create ballot");
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
                Options.getCardLayout().show(Options.getContentPanel(), "MEDIA");
                System.out.println("Media Page");
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
                Options.getCardLayout().show(Options.getContentPanel(), "LANDING");
                System.out.println("Landing");
                Options.clearAllInputs();
            }
        });
    }

    private void refreshPanel(){
        revalidate();
        repaint();
    }
}
