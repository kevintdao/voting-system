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
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(100, 20, 0, 20);  // padding
        //c.fill = GridBagConstraints.HORIZONTAL;

        // welcome components
        auditorLabel = new JLabel(auditorLang[Options.getLanguageIndex()]);
        auditorLabel.setFont(new Font("Arial", Font.BOLD, 20));
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.ipady = 10;
        c.anchor = GridBagConstraints.PAGE_START;
        add(auditorLabel, c);

        JLabel placeholder = new JLabel("");
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.ipady = 10;
        //c.weightx = 0.5;
        add(placeholder, c);

        // language select component
        languagesJComboBox = new JComboBox<>(languages);
        languagesJComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                int selected = comboBox.getSelectedIndex();
                // change labels to selected language
                auditorLabel.setText(auditorLang[selected]);
                returnToHomeButton.setText(returnHomeLang[selected]);
                viewResultsButton.setText(viewResultsLang[selected]);
                createBallotButton.setText(createBallotLang[selected]);
                profileButton.setText(profileLang[selected]);
            }
        });
        JLabel placeholder2 = new JLabel("");
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.ipady = 10;
        //c.weightx = 0.5;
        add(placeholder2, c);

        profileButton = new JButton("Profile");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 10;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(20, 20, 100, 20);  // padding
        add(profileButton, c);

        JLabel placeholder4 = new JLabel("");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        c.ipadx = 50;
        c.ipady = 10;
        c.weightx = 0.5;
        add(placeholder4, c);

        //c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        c.ipady = 10;
        //c.weightx = 0.8;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        add(languagesJComboBox, c);


        createBallotButton = new JButton("Create Ballot");
        // change to BallotCreation page
        createBallotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Options.getCardLayout().show(Options.getContentPanel(), "CREATEBALLOT");
                System.out.println("create ballot");
                //TODO: go to ballot creation page
            }
        });
        c.gridx = 0;
        c.gridy = 2;
        c.ipady = 10;
        c.gridwidth = 3;
        c.insets = new Insets(10, 40, 0, 40);  // padding
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

        returnToHomeButton = new JButton("Return to Home");
        c.gridx = 0;
        c.gridy = 4;
        c.ipady = 10;
        c.gridwidth = 3;
        add(returnToHomeButton, c);
        returnToHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Options.getCardLayout().show(Options.getContentPanel(), "HOME");
                System.out.println("Home page");
            }
        });
    }
}
