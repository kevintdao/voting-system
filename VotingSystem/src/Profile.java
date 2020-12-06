import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Extends JPanel to create a GUI page for displaying user profile information
 * Includes dark mode, language selection, and a back button.
 * @author Kevin Dao, Cole Garton, Timothy Evans
 * @version 1.0.0, Dec 6 2020
 * @see JPanel
 */
public class Profile extends JPanel {// get data from data and display the info
    private String[] firstNameLang = {"First Name: ", "Nombre de pila: ", "Prénom: "};
    private String[] lastNameLang = {"Last Name: ", "Apellido: ", "Nom de famille: "};
    private String[] usernameLang = {"Username: ", "Nombre de usuario: ","Nom d'utilisateur: "};
    private String[] voterIDLang = {"Voter ID: ", "Identificación de votante: ", "ID de l'électeur: "};
    private String[] countyLang = {"County: ", "Condado: ", "Comté:"};
    private String[] stateLang = {"State: ", "Estado: ", "Etat: "};
    private String[] birthdayLang = {"Date of Birth: ", "Fecha de nacimiento: ", "Date de naissance: "};
    private String[] backLang = {"<- Back", "<- Regresa", "<- Retourner"};

    private JLabel userNameLabel;
    private JTextField userName;

    private JLabel firstNameLabel;
    private JTextField firstName;

    private JLabel lastNameLabel;
    private JTextField lastName;

    private JLabel birthdayLabel;
    private JTextField birthday;

    private JLabel idLabel;
    private JTextField id;

    private JLabel countyLabel;
    private JTextField county;

    private JLabel stateLabel;
    private JTextField state;

    private JButton backButton;

    private Double labelWeightX = 0.2;
    private Double fieldWeightX = 0.8;

    public Profile(){
        setLayout(new GridBagLayout());
        setName("Profile");
        GridBagConstraints c = new GridBagConstraints();

        GUIComponents.getDarkModeButton(1).addActionListener(e -> {
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
        add(GUIComponents.getDarkModeButton(1),c);


        c.insets = new Insets(5,20,0,20);  // padding
        // language select component
        GUIComponents.getLanguageComboBox(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                int selected = comboBox.getSelectedIndex();
                GUIComponents.setLanguageIndex(selected);

                // set all labels to be the current selected language
                firstNameLabel.setText(firstNameLang[selected]);
                lastNameLabel.setText(lastNameLang[selected]);
                idLabel.setText(voterIDLang[selected]);
                userNameLabel.setText(usernameLang[selected]);
                countyLabel.setText(countyLang[selected]);
                stateLabel.setText(stateLang[selected]);
                birthdayLabel.setText(birthdayLang[selected]);
                backButton.setText(backLang[selected]);

                GUIComponents.changeLanguage();
                GUIComponents.updateDarkModeButtonText();
                refreshPanel();
            }
        });
        c.gridx = 2; // third column
        c.gridy = 0; // first row
        c.weightx = 0.2;
        c.ipady = 10;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        add(GUIComponents.getLanguageComboBox(1), c);

        c.gridx = 1;
        c.gridy = 0;
        c.ipady = 10;
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.PAGE_START;
        add(new JLabel(""), c);

        // username components
        userNameLabel = new JLabel(usernameLang[GUIComponents.getLanguageIndex()]);
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 10;
        c.weightx = labelWeightX;
        add(userNameLabel, c);

        userName = new JTextField();
        userName.setEditable(false);
        userName.setName("usernameField");
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        c.ipady = 10;
        c.weightx = fieldWeightX;
        add(userName, c);

        // id components
        idLabel = new JLabel(voterIDLang[GUIComponents.getLanguageIndex()]);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = labelWeightX;
        add(idLabel, c);

        id = new JTextField();
        id.setEditable(false);
        id.setName("idField");
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        c.ipady = 10;
        c.weightx = fieldWeightX;
        add(id, c);

        // first name components
        firstNameLabel = new JLabel(firstNameLang[GUIComponents.getLanguageIndex()]);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = labelWeightX;
        add(firstNameLabel, c);

        firstName = new JTextField();
        firstName.setEditable(false);
        firstName.setName("firstNameField");
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 2;
        c.ipady = 10;
        c.weightx = fieldWeightX;
        add(firstName, c);

        // last name components
        lastNameLabel = new JLabel(lastNameLang[GUIComponents.getLanguageIndex()]);
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = labelWeightX;
        add(lastNameLabel, c);

        lastName = new JTextField();
        lastName.setEditable(false);
        lastName.setName("lastNameField");
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 2;
        c.ipady = 10;
        c.weightx = fieldWeightX;
        add(lastName, c);

        // birthday components
        birthdayLabel = new JLabel(birthdayLang[GUIComponents.getLanguageIndex()]);
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = labelWeightX;
        add(birthdayLabel, c);

        birthday = new JTextField();
        birthday.setEditable(false);
        birthday.setName("birthdayField");
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 2;
        c.ipady = 10;
        c.weightx = fieldWeightX;
        add(birthday, c);

        // county components
        countyLabel = new JLabel(countyLang[GUIComponents.getLanguageIndex()]);
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = labelWeightX;
        add(countyLabel, c);

        county = new JTextField();
        county.setEditable(false);
        county.setName("countyField");
        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = 2;
        c.ipady = 10;
        c.weightx = fieldWeightX;
        add(county, c);

        // state components
        stateLabel = new JLabel(stateLang[GUIComponents.getLanguageIndex()]);
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = labelWeightX;
        add(stateLabel, c);

        state = new JTextField();
        state.setEditable(false);
        state.setName("stateField");
        c.gridx = 1;
        c.gridy = 7;
        c.gridwidth = 2;
        c.ipady = 10;
        c.weightx = fieldWeightX;
        add(state, c);

        // back button
        backButton = new JButton(backLang[GUIComponents.getLanguageIndex()]);
        // change to home page
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // move to correct home page based on status
                if(Database.checkAuditorStatus()){
                    GUIComponents.getCardLayout().show(GUIComponents.getContentPanel(), "AUDITOR");
                }
                else {
                    GUIComponents.getCardLayout().show(GUIComponents.getContentPanel(), "HOME");
                }
            }
        });
        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = 0.0;
        c.anchor = GridBagConstraints.LAST_LINE_START;
        c.insets = new Insets(50,20,0,20);  // padding
        add(backButton, c);
    }

    private void refreshPanel(){
        revalidate();
        repaint();
    }
}