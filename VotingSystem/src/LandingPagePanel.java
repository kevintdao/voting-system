import javax.swing.*;
import java.awt.event.ItemEvent;

public class LandingPagePanel extends JPanel {
    private static final String[] languages = {"english", "spanish", "french"};

    public LandingPagePanel() {
        JComboBox<String> languagesJComboBox = new JComboBox<String>(languages);
        JPasswordField passwordField = new JPasswordField(10);
        JTextField usernameField = new JTextField(10);

        languagesJComboBox.addItemListener(
                event -> {
                    if (event.getStateChange() == ItemEvent.SELECTED) {
                        // change languages will go here
                    }
                }
        );

        add(languagesJComboBox);
        add(usernameField);
        add(passwordField);
    }
}
