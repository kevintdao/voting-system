import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// get data from data and display the info
public class Profile extends JPanel {
    private String[] firstNameLang = {"First Name: ", "Nombre de pila: ", "Prénom: "};
    private String[] lastNameLang = {"Last Name: ", "Apellido: ", "Nom de famille: "};
    private String[] usernameLang = {"Username: ", "Nombre de usuario: ","Nom d'utilisateur: "};
    private String[] voterIDLang = {"Voter ID: ", "Identificación de votante: ", "ID de l'électeur: "};
    private String[] countyLang = {"County: ", "Condado: ", "Comté:"};
    private String[] stateLang = {"State: ", "Estado: ", "Etat: "};
    private String[] birthdayLang = {"Date of Birth: ", "Fecha de nacimiento: ", "Date de naissance: "};
    private String[] backLang = {"<- Back", "<- Regresa", "<- Retourner"};

    private JLabel userNameLabel;
    private JLabel userName;

    private JLabel firstNameLabel;
    private JLabel firstName;

    private JLabel lastNameLabel;
    private JLabel lastName;

    private JLabel birthdayLabel;
    private JLabel birthday;

    private JLabel idLabel;
    private JLabel id;

    private JLabel countyLabel;
    private JLabel county;

    private JLabel stateLabel;
    private JLabel state;

    private JButton backButton;

    private Double labelWeightX = 0.2;
    private Double fieldWeightX = 0.8;

    public Profile(){
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,20,0,20);  // padding

        // language select component
        Options.getLanguageComboBox(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                int selected = comboBox.getSelectedIndex();
                Options.setLanguageIndex(selected);

                // set all labels to be the current selected language
                firstNameLabel.setText(firstNameLang[selected]);
                lastNameLabel.setText(lastNameLang[selected]);
                idLabel.setText(voterIDLang[selected]);
                userNameLabel.setText(usernameLang[selected]);
                countyLabel.setText(countyLang[selected]);
                stateLabel.setText(stateLang[selected]);
                birthdayLabel.setText(birthdayLang[selected]);
                backButton.setText(backLang[selected]);

                Options.changeLanguage();
                refreshPanel();
            }
        });
        c.gridx = 2; // third column
        c.gridy = 0; // first row
        c.weightx = 0.2;
        c.ipady = 10;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        add(Options.getLanguageComboBox(1), c);

        // username components
        userNameLabel = new JLabel(usernameLang[Options.getLanguageIndex()]);
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 10;
        c.weightx = labelWeightX;
        add(userNameLabel, c);

        userName = new JLabel("asdfsdaf");
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        c.ipady = 10;
        c.weightx = fieldWeightX;
        add(userName, c);

        // id components
        idLabel = new JLabel(voterIDLang[Options.getLanguageIndex()]);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = labelWeightX;
        add(idLabel, c);

        id = new JLabel("sadfsadfasdf");
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        c.ipady = 10;
        c.weightx = fieldWeightX;
        add(id, c);

        // first name components
        firstNameLabel = new JLabel(firstNameLang[Options.getLanguageIndex()]);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = labelWeightX;
        add(firstNameLabel, c);

        firstName = new JLabel("sadfsadfasdf");
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 2;
        c.ipady = 10;
        c.weightx = fieldWeightX;
        add(firstName, c);

        // last name components
        lastNameLabel = new JLabel(lastNameLang[Options.getLanguageIndex()]);
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = labelWeightX;
        add(lastNameLabel, c);

        lastName = new JLabel("sadfsadfasdf");
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 2;
        c.ipady = 10;
        c.weightx = fieldWeightX;
        add(lastName, c);

        // birthday components
        birthdayLabel = new JLabel(birthdayLang[Options.getLanguageIndex()]);
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = labelWeightX;
        add(birthdayLabel, c);

        birthday = new JLabel("12/31/1980");
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 2;
        c.ipady = 10;
        c.weightx = fieldWeightX;
        add(birthday, c);

        // county components
        countyLabel = new JLabel(countyLang[Options.getLanguageIndex()]);
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = labelWeightX;
        add(countyLabel, c);

        county = new JLabel("sdasfdsafds");
        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = 2;
        c.ipady = 10;
        c.weightx = fieldWeightX;
        add(county, c);

        // state components
        stateLabel = new JLabel(stateLang[Options.getLanguageIndex()]);
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = labelWeightX;
        add(stateLabel, c);

        state = new JLabel("sdasfdsafds");
        c.gridx = 1;
        c.gridy = 7;
        c.gridwidth = 2;
        c.ipady = 10;
        c.weightx = fieldWeightX;
        add(state, c);

        // back button
        backButton = new JButton(backLang[Options.getLanguageIndex()]);
        // change to home page
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Options.getCardLayout().show(Options.getContentPanel(), "HOME");
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