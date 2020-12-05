import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingPage extends JPanel {
    private String[] userIDLang = {"Username: ", "Nombre de usuario: ","Nom d'utilisateur: "};
    private String[] passLang = {"Password: ", "Contraseña: ", "Mot de passe: "};
    private String[] submitLang = {"Submit", "Enviar", "Soumettre"};
    private String[] registerLang = {"Register", "Registrarse", "S'inscrire"};
    
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
        setName("Landing");
        GridBagConstraints c = new GridBagConstraints();

        Options.getDarkModeButton().addActionListener(e -> {
            if(Options.getDarkMode()) {
                Options.setDarkMode(false);
            }
            else {
                Options.setDarkMode(true);
            }
            refreshPanel();
        });
        c.insets = new Insets(10,40,0,40);  // padding
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 10;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.PAGE_START;
        c.weightx = 0.2;
        add(Options.getDarkModeButton(),c);

        Options.getLanguageComboBox(0).addActionListener(
                event -> {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    int selected = comboBox.getSelectedIndex();
                    Options.setLanguageIndex(selected);

                    // change language for labels
                    usernameLabel.setText(userIDLang[selected]);
                    passwordLabel.setText(passLang[selected]);
                    submit.setText(submitLang[selected]);
                    register.setText(registerLang[selected]);

                    Options.changeLanguage();
                    refreshPanel();
                }
        );

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.5;
        c.ipady = 10;
        c.anchor = GridBagConstraints.PAGE_START;
        add(new JLabel(""), c);

        c.insets = new Insets(10,20,0,20);
        c.gridx = 2; // third column
        c.gridy = 0; // first row
        c.weightx = 0.2;
        c.ipady = 10;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        add(Options.getLanguageComboBox(0), c);

        usernameLabel = new JLabel(userIDLang[Options.getLanguageIndex()]);
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

        passwordLabel = new JLabel(passLang[Options.getLanguageIndex()]);
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

        submit = new JButton(submitLang[Options.getLanguageIndex()]);
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 2;
        c.weightx = 0.2;
        c.insets = new Insets(40,40,0,40);  // padding
        add(submit, c);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();

                // check if username and password exist in database
                if(!Options.checkLogin(username, password)){
                    JOptionPane.showMessageDialog(Options.getContentPanel(), "Incorrect login!","Incorrect Login!", JOptionPane.ERROR_MESSAGE );
                    return;
                }

                if(username.contains("auditor:")){
                    Options.getCardLayout().show(Options.getContentPanel(), "AUDITOR");
                }
                else if(username.contains("media:")){
                    Options.getCardLayout().show(Options.getContentPanel(), "MEDIA");
                }
                else{
                    Options.getCardLayout().show(Options.getContentPanel(), "HOME");
                }
            }
        });

        register = new JButton(registerLang[Options.getLanguageIndex()]);
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

    private void refreshPanel(){
        revalidate();
        repaint();
    }
}
