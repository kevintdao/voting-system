import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

public class LandingPage extends JPanel {
    private static final String[] languages = {"english", "spanish", "french"};
    JComboBox<String> languagesJComboBox;
    
    JPasswordField passwordField;
    JLabel passwordLabel;

    JTextField usernameField;
    JLabel usernameLabel;

    JButton submit;

    JButton register;

    private Double labelWeightX = 0.2;
    private Double fieldWeightX = 0.8;


    public LandingPage() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        languagesJComboBox = new JComboBox<String>(languages);

        languagesJComboBox.addItemListener(
                event -> {
                    if (event.getStateChange() == ItemEvent.SELECTED) {
                        // change languages will go here
                    }
                }
        );

        c.insets = new Insets(10,20,0,20);
        c.gridx = 2; // third column
        c.gridy = 0; // first row
        c.weightx = 0.2;
        c.ipady = 10;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        add(languagesJComboBox, c);

        usernameLabel = new JLabel("User ID: ");
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0; // first column
        c.gridy = 1; // second row
        c.ipady = 10; // component's height
        c.weightx = labelWeightX;
        add(usernameLabel, c);

        usernameField = new JTextField();
        c.gridx = 1; // second column
        c.gridy = 1; // second row
        c.gridwidth = 2; // takes 2 columns
        c.ipady = 10; // component's height
        c.weightx = fieldWeightX;
        add(usernameField, c);

        passwordLabel = new JLabel("Password: ");
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.weightx = labelWeightX;
        add(passwordLabel, c);

        passwordField = new JPasswordField();
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        c.weightx = fieldWeightX;
        add(passwordField, c);

        submit = new JButton("Submit");
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 2;
        c.weightx = 0.2;
        c.insets = new Insets(40,40,0,40);  // padding
        add(submit, c);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Options.getCardLayout().show(Options.getContentPanel(), "HOME");
            }
        });

        register = new JButton("Register");
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.weightx = 0.2;
        add(register, c);
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Options.getCardLayout().show(Options.getContentPanel(), "REGISTRATION");
            }
        });
    }
}
