import javax.swing.*;
import java.awt.*;

public class Home extends JPanel {
    private JComboBox<String> languageSelect;
    private String[] languages = new String[] {"English", "Spanish"};

    private JProgressBar progressBar;

    private JButton viewProfileButton;
    private JButton voteButton;

    private JLabel welcomeBackLabel;

    public Home(){
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,20,0,20);  // padding
        c.fill = GridBagConstraints.HORIZONTAL;

        welcomeBackLabel = new JLabel("Welcome!");
        welcomeBackLabel.setFont(new Font("Arial", Font.BOLD, 24));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.ipady = 10;
        c.weightx = 0.8;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        add(welcomeBackLabel, c);

        languageSelect = new JComboBox<>(languages);
        c.gridx = 2; // third column
        c.gridy = 0; // first row
        c.ipady = 10;
        c.weightx = 0.2;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        add(languageSelect, c);

        progressBar = new JProgressBar();
        c.gridx = 2;
        c.gridy = 1;
        c.ipady = 10;
        c.weightx = 0.2;
        c.insets = new Insets(10,20,100,20);  // padding
        add(progressBar, c);

        viewProfileButton = new JButton("View Profile");
        c.gridx = 1;
        c.gridy = 2;
        c.ipady = 10;
        c.gridwidth = 3;
        c.weightx = 0.0;
        c.insets = new Insets(10,40,0,40);  // padding
        c.anchor = GridBagConstraints.CENTER;
        add(viewProfileButton, c);

        voteButton = new JButton("Vote");
        c.gridx = 1;
        c.gridy = 3;
        c.ipady = 10;
        c.gridwidth = 3;
        c.weightx = 0.0;
        c.anchor = GridBagConstraints.CENTER;
        add(voteButton, c);
    }
}
