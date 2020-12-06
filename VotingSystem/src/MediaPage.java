import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

/**
 * Extends JPanel to create a GUI page for displaying results of
 * elections for different positions. Includes an update button to
 * update results, language selection, dark mode, log out button, and
 * back button.
 * @author Kevin Dao, Cole Garton, Timothy Evans
 * @version 1.0.0, Dec 6 2020
 * @see JPanel
 */
public class MediaPage extends JPanel
{
    private final JLabel resultsLabel;
    private final JButton backButton;
    private final JButton updateButton;
    private JButton logOutButton;

    //private static final String[] graphs = {"Numerical Results", "Bar Graph", "Pie Chart"};
    private String[] resultsLang = {"Results", "Resultados", "Résultats"};
    private String[] backLang = {"<- Back", "<- Atrás", "<- Retour"};
    private String[] updateLang = {"Update", "Actualizar", "Mise à Jour"};
    private String[] logOutLang = {"Log out", "Cerrar sesión", "Se déconnecter"};

    public MediaPage() {
        setLayout(new GridBagLayout()); // set frame layout
        setName("Media");
        GridBagConstraints c = new GridBagConstraints();

        GUIComponents.getDarkModeButton(6).addActionListener(e -> {
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
        c.gridx = 1;
        c.gridy = 0;
        c.ipady = 10;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.PAGE_START;
        c.weightx = 0.2;
        add(GUIComponents.getDarkModeButton(6),c);

        GUIComponents.getLanguageComboBox(6).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                int selected = comboBox.getSelectedIndex();
                GUIComponents.setLanguageIndex(selected);

                // change labels to selected language
                resultsLabel.setText(resultsLang[selected]);
                backButton.setText(backLang[selected]);
                updateButton.setText(updateLang[selected]);
                logOutButton.setText(logOutLang[selected]);

                GUIComponents.changeLanguage();
                GUIComponents.updateDarkModeButtonText();
                refreshPanel();

            }
        });
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = 0.2;
        c.insets = new Insets(10, 20, 0, 20);  // padding
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        add(GUIComponents.getLanguageComboBox(6), c);


        resultsLabel = new JLabel(resultsLang[GUIComponents.getLanguageIndex()]);
        resultsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.ipady = 10;
        c.weightx = 0.8;

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 20, 0, 20);  // padding
        c.anchor = GridBagConstraints.PAGE_START;
        add(resultsLabel, c);

        JLabel placeholder = new JLabel("");
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.ipady = 10;
        c.weightx = 0.5;
        add(placeholder, c);


        String contents = "RESULTS\n Position: \n Candidate: <name> <numvotes>";
        GUIComponents.getTextArea().setRows(10);
        GUIComponents.getTextArea().setText(contents);
        GUIComponents.getTextArea().setEditable(false);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        c.gridheight = 3;
        c.ipadx = 10;
        c.ipady = 50;
        c.insets = new Insets(10, 20, 40, 20);  // padding
        add(new JScrollPane(GUIComponents.getTextArea()), c);


        backButton = new JButton(backLang[GUIComponents.getLanguageIndex()]);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // check if the current user is an auditor
                if(!Database.checkAuditorStatus()){
                    JOptionPane.showMessageDialog(GUIComponents.getContentPanel(), "You're not an Auditor","Invalid Permission!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                GUIComponents.getCardLayout().show(GUIComponents.getContentPanel(), "AUDITOR");
            }
        });
        c.gridx = 0;
        c.gridy = 5;
        c.ipady = 10;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LAST_LINE_START;
        add(backButton, c);


        updateButton = new JButton(updateLang[GUIComponents.getLanguageIndex()]);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get the result from the auditor's county
                Database.getResult();
            }
        });
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 1;
        add(updateButton, c);

        logOutButton = new JButton(logOutLang[GUIComponents.getLanguageIndex()]);
        c.gridx = 2;
        c.gridy = 5;
        c.ipady = 10;
        c.gridwidth = 1;
        add(logOutButton, c);
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIComponents.getCardLayout().show(GUIComponents.getContentPanel(), "LANDING");
                GUIComponents.clearAllInputs();
            }
        });
    }

    private void refreshPanel(){
        revalidate();
        repaint();
    }
}