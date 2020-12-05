import javax.swing.*;
import java.awt.*;

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

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception f) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        Options.setUpComboBox();
        Options.setUpDarkModeButton();


        homePage = new Home();
        profilePage = new Profile();
        landingPage = new LandingPage();
        registrationPage = new Registration();
        votePage = new VotePage();
        auditorPage = new AuditorPage();
        mediaPage = new MediaPage();
        createBallot = new CreateBallot();

        Options.getContentPanel().setLayout(Options.getCardLayout());

        Options.getContentPanel().add(landingPage, "LANDING");
        Options.getContentPanel().add(registrationPage, "REGISTRATION");
        Options.getContentPanel().add(homePage, "HOME");
        Options.getContentPanel().add(profilePage, "PROFILE");
        Options.getContentPanel().add(votePage, "VOTE");
        Options.getContentPanel().add(auditorPage, "AUDITOR");
        Options.getContentPanel().add(mediaPage, "MEDIA");
        Options.getContentPanel().add(createBallot, "CREATEBALLOT");

        setContentPane(Options.getContentPanel());

        Options.getCardLayout().show(Options.getContentPanel(), "LANDING");

        Options.createUsersTable();
//        Options.createElectionsTable();
    }


    public static void updateTheme() {
        // dark mode
        if(Options.getDarkMode()) {
            UIManager.put("control", new Color( 45, 44, 44));
            UIManager.put("info", new Color(128,128,128));
            UIManager.put("nimbusBase", new Color(55, 85, 121));
            UIManager.put("text", new Color(0, 0, 0, 255));
        }
        // light mode
        else {
            UIManager.put("nimbusBase", new Color(255, 255, 255, 255));
            UIManager.put("nimbusBase", new Color(55, 85, 121));
            UIManager.put("control", new Color(255, 255, 255, 255));
            UIManager.put("text", new Color(0, 0, 0, 255));
        }
    }
}
