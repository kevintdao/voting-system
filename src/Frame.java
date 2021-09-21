import javax.swing.*;
import java.awt.*;
/**
 * Extends JFrame to create a JFrame containing all JPanel pages of the voting system.
 * Implements functionality for dark/light mode.
 * @author Kevin Dao, Cole Garton, Timothy Evans
 * @version 1.0.0, Dec 6 2020
 * @see JFrame
 */
public class Frame extends JFrame {
    private Home homePage;
    private Profile profilePage;
    private LandingPage landingPage;
    private Registration registrationPage;
    private VotePage votePage;
    private AuditorPage auditorPage;
    private MediaPage mediaPage;
    private CreateBallot createBallot;

    public Frame() {
        super("Voting system");

        updateTheme();

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception f) {
        }

        GUIComponents.setUpComboBox();
        GUIComponents.setUpDarkModeButton();


        homePage = new Home();
        profilePage = new Profile();
        landingPage = new LandingPage();
        registrationPage = new Registration();
        votePage = new VotePage();
        auditorPage = new AuditorPage();
        mediaPage = new MediaPage();
        createBallot = new CreateBallot();

        GUIComponents.getContentPanel().setLayout(GUIComponents.getCardLayout());

        GUIComponents.getContentPanel().add(landingPage, "LANDING");
        GUIComponents.getContentPanel().add(registrationPage, "REGISTRATION");
        GUIComponents.getContentPanel().add(homePage, "HOME");
        GUIComponents.getContentPanel().add(profilePage, "PROFILE");
        GUIComponents.getContentPanel().add(votePage, "VOTE");
        GUIComponents.getContentPanel().add(auditorPage, "AUDITOR");
        GUIComponents.getContentPanel().add(mediaPage, "MEDIA");
        GUIComponents.getContentPanel().add(createBallot, "CREATEBALLOT");

        setContentPane(GUIComponents.getContentPanel());

        GUIComponents.getCardLayout().show(GUIComponents.getContentPanel(), "LANDING");
    }

    // update the theme based on whether dark mode is selected or not
    public static void updateTheme() {
        // dark mode
        if(GUIComponents.getDarkMode()) {
            UIManager.put("nimbusBase",  new Color(45, 44, 44));
            UIManager.put("nimbusLightBackground", new Color(59, 58, 58));
            UIManager.put("nimbusFocus", new Color( 96, 94, 92));
            UIManager.put("nimbusBlueGrey", new Color(59, 58, 58));
            UIManager.put("control", new Color( 45, 44, 44));
            UIManager.put("scrollbar", new Color( 96, 94, 92));
            UIManager.put("text", new Color(255, 255, 255, 255));
            UIManager.put("nimbusSelectionBackground", new Color( 96, 94, 92));
        }
        // light mode
        else {
            UIManager.put("nimbusBase", new Color(51, 98, 140));
            UIManager.put("nimbusLightBackground", new Color(255, 255, 255));
            UIManager.put("nimbusFocus", new Color( 115, 164, 209));
            UIManager.put("nimbusBlueGrey", new Color(169, 176, 190));
            UIManager.put("control", new Color(214,217,223));
            UIManager.put("scrollbar", new Color( 205, 208, 213));
            UIManager.put("text", new Color(0, 0, 0, 255));
            UIManager.put("nimbusSelectionBackground", new Color( 57,105,138));
        }
    }
}
