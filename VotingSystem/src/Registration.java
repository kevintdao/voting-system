import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class Registration extends JPanel {
    private JComboBox<String> languageSelect;
    private String[] languages = new String[] {"English", "Spanish"};

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

    private JButton signUpButton;

    private Double labelWeightX = 0.2;
    private Double fieldWeightX = 0.8;

    public Registration(){
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,20,0,20);  // padding

        // language select component
        languageSelect = new JComboBox<>(languages);
        c.gridx = 2; // third column
        c.gridy = 0; // first row
        c.weightx = 0.2;
        c.ipady = 10;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        add(languageSelect, c);

        // first name components
        firstNameLabel = new JLabel("First Name: ");
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
        lastNameLabel = new JLabel("Last Name: ");
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
        userIDLabel = new JLabel("User ID: ");
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
        socialLabel = new JLabel("Social Security: ");
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
        passLabel = new JLabel("Password: ");
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
        confirmPassLabel = new JLabel("Confirm password: ");
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
        countyLabel = new JLabel("County: ");
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
        stateLabel = new JLabel("State: ");
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
        birthdayLabel = new JLabel("Date of Birth (MM/dd/yyyy): ");
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

        // sign up button
        signUpButton = new JButton("Sign Up");
        c.anchor = GridBagConstraints.PAGE_END;
        c.gridx = 0;
        c.gridy = 10;
        c.gridwidth = 3;
        c.weightx = 0.2;
        c.insets = new Insets(30,40,0,40);  // padding
        add(signUpButton, c);
    }
}
