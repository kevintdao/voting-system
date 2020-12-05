import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class Registration extends JPanel {
    private String[] firstNameLang = {"First Name: ", "Nombre de pila: ", "Prénom: "};
    private String[] lastNameLang = {"Last Name: ", "Apellido: ", "Nom de famille: "};
    private String[] userIDLang = {"Username: ", "Nombre de usuario: ","Nom d'utilisateur: "};
    private String[] socialLang = {"Social Security: ", "Seguridad Social: ", "Sécurité sociale: "};
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

    private JLabel socialLabel;
    private JFormattedTextField socialField;

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
        c.insets = new Insets(5,20,0,20);  // padding

        // language select component
        Options.getLanguageComboBox(3).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                int selected = comboBox.getSelectedIndex();
                Options.setLanguageIndex(selected);

                // set all labels to be the current selected language
                firstNameLabel.setText(firstNameLang[selected]);
                lastNameLabel.setText(lastNameLang[selected]);
                userIDLabel.setText(userIDLang[selected]);
                socialLabel.setText(socialLang[selected]);
                passLabel.setText(passLang[selected]);
                confirmPassLabel.setText(confirmPassLang[selected]);
                countyLabel.setText(countyLang[selected]);
                stateLabel.setText(stateLang[selected]);
                birthdayLabel.setText(birthdayLang[selected]);
                signUpButton.setText(signUpLang[selected]);
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
        add(Options.getLanguageComboBox(3), c);

        // first name components
        firstNameLabel = new JLabel(firstNameLang[Options.getLanguageIndex()]);
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
        lastNameLabel = new JLabel(lastNameLang[Options.getLanguageIndex()]);
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
        userIDLabel = new JLabel(userIDLang[Options.getLanguageIndex()]);
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

        // social security components
        socialLabel = new JLabel(socialLang[Options.getLanguageIndex()]);
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.weightx = labelWeightX;
        add(socialLabel, c);

        try {
            MaskFormatter socialMask = new MaskFormatter("###-###-####");
            socialField = new JFormattedTextField(socialMask);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 2;
        c.weightx = fieldWeightX;
        add(socialField, c);

        // password components
        passLabel = new JLabel(passLang[Options.getLanguageIndex()]);
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
        confirmPassLabel = new JLabel(confirmPassLang[Options.getLanguageIndex()]);
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
        countyLabel = new JLabel(countyLang[Options.getLanguageIndex()]);
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
        stateLabel = new JLabel(stateLang[Options.getLanguageIndex()]);
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
        birthdayLabel = new JLabel(birthdayLang[Options.getLanguageIndex()]);
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
        backButton = new JButton(backLang[Options.getLanguageIndex()]);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                clearAllInputs();

                Options.getCardLayout().show(Options.getContentPanel(), "LANDING");
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
        signUpButton = new JButton(signUpLang[Options.getLanguageIndex()]);
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
                    JOptionPane.showMessageDialog(null, "Incorrect password confirmation","Incorrect Password!", JOptionPane.ERROR_MESSAGE);
                    passField.setText("");
                    confirmPassField.setText("");
                    return;
                }

                // check if username already existed in database
                if(Options.checkUsername(userID.getText())){
                    JOptionPane.showMessageDialog(null, "Please choose another username","Already existed username!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // add the user to database
                String username = userID.getText();
                String password = passField.getText();
                String first = firstNameField.getText();
                String last = lastNameField.getText();
                String dob = birthdayField.getText();
                String county = countyField.getText();
                String state = stateField.getText();

                Options.addNewUser(username, password, first, last, dob, county, state);

                Options.getCardLayout().show(Options.getContentPanel(), "HOME");
                clearAllInputs();
            }
        });
    }

    private void refreshPanel(){
        revalidate();
        repaint();
    }

    // clear the inputs from textfields
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
