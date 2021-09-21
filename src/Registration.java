import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
/**
 * Extends JPanel to create a GUI page for registering a new profile.
 * Includes dark mode, language selection, password confirmation,
 * unique username check, and empty field detection.
 * @author Kevin Dao, Cole Garton, Timothy Evans
 * @version 1.0.0, Dec 6 2020
 * @see JPanel
 */
public class Registration extends JPanel {
    private String[] firstNameLang = {"First Name: ", "Nombre de pila: ", "Prénom: "};
    private String[] lastNameLang = {"Last Name: ", "Apellido: ", "Nom de famille: "};
    private String[] userIDLang = {"Username: ", "Nombre de usuario: ","Nom d'utilisateur: "};
    private String[] passLang = {"Password: ", "Contraseña: ", "Mot de passe: "};
    private String[] confirmPassLang = {"Confirm Password: ", "Confirmar contraseña: ", "Confirmez le mot de passe: "};
    private String[] countyLang = {"County: ", "Condado: ", "Comté:"};
    private String[] stateLang = {"State: ", "Estado: ", "Etat: "};
    private String[] birthdayLang = {"Date of Birth (MM/dd/yyyy): ", "Fecha de nacimiento (MM/dd/yyyy): ", "Date de naissance (MM/dd/yyyy): "};
    private String[] signUpLang = {"Sign Up", "Regístrate", "S'inscrire"};
    private String[] backLang = {"<- Back", "<- Regresa", "<- Retourner"};

    private JLabel userIDLabel;
    private JTextField userID;

    private JLabel firstNameLabel;
    private JTextField firstNameField;

    private JLabel lastNameLabel;
    private JTextField lastNameField;

    private JLabel passLabel;
    private JPasswordField passField;
    private JLabel confirmPassLabel;
    private JPasswordField confirmPassField;

    private JLabel countyLabel;
    private JTextField countyField;

    private JLabel stateLabel;
    private JTextField stateField;

    private JLabel birthdayLabel;
    private JFormattedTextField birthdayField;

    private JButton backButton;
    private JButton signUpButton;

    private Double labelWeightX = 0.2;
    private Double fieldWeightX = 0.8;

    public Registration(){
        setLayout(new GridBagLayout());
        setName("Registration");
        GridBagConstraints c = new GridBagConstraints();

        GUIComponents.getDarkModeButton(3).addActionListener(e -> {
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
        add(GUIComponents.getDarkModeButton(3),c);

        // language select component
        GUIComponents.getLanguageComboBox(3).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                int selected = comboBox.getSelectedIndex();
                GUIComponents.setLanguageIndex(selected);

                // set all labels to be the current selected language
                firstNameLabel.setText(firstNameLang[selected]);
                lastNameLabel.setText(lastNameLang[selected]);
                userIDLabel.setText(userIDLang[selected]);
                passLabel.setText(passLang[selected]);
                confirmPassLabel.setText(confirmPassLang[selected]);
                countyLabel.setText(countyLang[selected]);
                stateLabel.setText(stateLang[selected]);
                birthdayLabel.setText(birthdayLang[selected]);
                signUpButton.setText(signUpLang[selected]);
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
        c.insets = new Insets(5,20,0,20);  // padding
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        add(GUIComponents.getLanguageComboBox(3), c);

        // first name components
        firstNameLabel = new JLabel(firstNameLang[GUIComponents.getLanguageIndex()]);
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 10;
        c.weightx = labelWeightX;
        add(firstNameLabel, c);

        firstNameField = new JTextField();
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        c.ipady = 10;
        c.weightx = fieldWeightX;
        add(firstNameField, c);

        // last name components
        lastNameLabel = new JLabel(lastNameLang[GUIComponents.getLanguageIndex()]);
        c.gridx = 0;
        c.gridy = 2;
        c.ipady = 10;
        c.weightx = labelWeightX;
        add(lastNameLabel, c);

        lastNameField = new JTextField();
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        c.ipady = 10;
        c.weightx = fieldWeightX;
        add(lastNameField, c);


        // user ID components
        userIDLabel = new JLabel(userIDLang[GUIComponents.getLanguageIndex()]);
        c.gridx = 0; // first column
        c.gridy = 3; // second row
        c.ipady = 10; // component's height
        c.weightx = labelWeightX;
        add(userIDLabel, c);

        userID = new JTextField("");
        c.gridx = 1; // second column
        c.gridy = 3; // second row
        c.gridwidth = 2; // takes 2 columns
        c.ipady = 10; // component's height
        c.weightx = fieldWeightX;
        add(userID, c);

        // password components
        passLabel = new JLabel(passLang[GUIComponents.getLanguageIndex()]);
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        c.weightx = labelWeightX;
        add(passLabel, c);

        passField = new JPasswordField();
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 2;
        c.weightx = fieldWeightX;
        add(passField, c);

        // confirm password components
        confirmPassLabel = new JLabel(confirmPassLang[GUIComponents.getLanguageIndex()]);
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 1;
        c.weightx = labelWeightX;
        add(confirmPassLabel, c);

        confirmPassField = new JPasswordField();
        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = 2;
        c.weightx = fieldWeightX;
        add(confirmPassField, c);

        // county components
        countyLabel = new JLabel(countyLang[GUIComponents.getLanguageIndex()]);
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 1;
        c.weightx = labelWeightX;
        add(countyLabel, c);

        countyField = new JTextField();
        c.gridx = 1;
        c.gridy = 7;
        c.gridwidth = 2;
        c.weightx = fieldWeightX;
        add(countyField, c);

        // state components
        stateLabel = new JLabel(stateLang[GUIComponents.getLanguageIndex()]);
        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 1;
        c.weightx = labelWeightX;
        add(stateLabel, c);

        stateField = new JTextField();
        c.gridx = 1;
        c.gridy = 8;
        c.gridwidth = 2;
        c.weightx = fieldWeightX;
        add(stateField, c);

        // birthday components
        birthdayLabel = new JLabel(birthdayLang[GUIComponents.getLanguageIndex()]);
        c.gridx = 0;
        c.gridy = 9;
        c.gridwidth = 1;
        c.weightx = labelWeightX;
        add(birthdayLabel, c);

        try {
            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            birthdayField = new JFormattedTextField(dateMask);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.gridx = 1;
        c.gridy = 9;
        c.gridwidth = 2;
        c.weightx = fieldWeightX;
        add(birthdayField, c);

        // back button
        backButton = new JButton(backLang[GUIComponents.getLanguageIndex()]);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                clearAllInputs();

                GUIComponents.getCardLayout().show(GUIComponents.getContentPanel(), "LANDING");
            }
        });
        c.anchor = GridBagConstraints.LAST_LINE_START;
        c.gridx = 0;
        c.gridy = 10;
        c.gridwidth = 1;
        c.weightx = 0.2;
        c.insets = new Insets(30,40,0,40);  // padding
        add(backButton, c);


        // sign up button
        signUpButton = new JButton(signUpLang[GUIComponents.getLanguageIndex()]);
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.gridx = 1;
        c.gridy = 10;
        c.gridwidth = 2;
        c.weightx = 0.2;
        c.insets = new Insets(30,40,0,40);  // padding
        add(signUpButton, c);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // check if the password are the same
                if(!passField.getText().equals(confirmPassField.getText())){
                    JOptionPane.showMessageDialog(GUIComponents.getContentPanel(), "Incorrect password confirmation","Incorrect Password!", JOptionPane.ERROR_MESSAGE);
                    passField.setText("");
                    confirmPassField.setText("");
                    return;
                }

                // check if username already existed in database
                if(Database.checkUsername(userID.getText())){
                    JOptionPane.showMessageDialog(GUIComponents.getContentPanel(), "Please choose another username","Already existed username!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // check if any field is empty
                for(int i = 0; i < getComponentCount(); i++) {
                    if(getComponent(i) instanceof JTextField){
                        JTextField textfield = (JTextField) getComponent(i);
                        if(textfield.getText().length() == 0){
                            JOptionPane.showMessageDialog(GUIComponents.getContentPanel(), "Please fill out all of the fields","Empty field detected!", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }

                // add the user to database
                String username = userID.getText();
                String password = passField.getText();
                String first = firstNameField.getText();
                String last = lastNameField.getText();
                String dob = birthdayField.getText();
                String county = countyField.getText();
                String state = stateField.getText();

                // add new user to database
                Database.addNewUser(username, password, first, last, dob, county, state);

                // set the current user to be username
                Database.setCurrentUser(username);

                // check for account type
                if(username.contains("auditor:")){
                    GUIComponents.getCardLayout().show(GUIComponents.getContentPanel(), "AUDITOR");
                }
                else if(username.contains("media:")){
                    GUIComponents.getCardLayout().show(GUIComponents.getContentPanel(), "MEDIA");
                }
                else{
                    GUIComponents.getProgressBar().setString("NOT STARTED");
                    GUIComponents.getCardLayout().show(GUIComponents.getContentPanel(), "HOME");
                }

                clearAllInputs();
            }
        });
    }

    private void refreshPanel(){
        revalidate();
        repaint();
    }

    // clear the inputs from textfields in registration page
    private void clearAllInputs(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < getComponentCount(); i++) {
                    if(getComponent(i) instanceof JTextField){
                        JTextField textfield = (JTextField) getComponent(i);
                        textfield.setText("");
                    }
                }
            }
        });
    }
}
