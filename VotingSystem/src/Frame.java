import javax.swing.*;
import java.sql.*;

public class Frame extends JFrame {
    private Home homePage;
    private Profile profilePage;
    private LandingPage landingPage;
    private Registration registrationPage;
    private VotePage votePage;
    private AuditorPage auditorPage;
    private MediaPage mediaPage;

    public Frame() {
        super("Voting system");

        Options.setUpComboBox();


        homePage = new Home();
        profilePage = new Profile();
        landingPage = new LandingPage();
        registrationPage = new Registration();
        votePage = new VotePage();
        auditorPage = new AuditorPage();
        mediaPage = new MediaPage();

        Options.getContentPanel().setLayout(Options.getCardLayout());

        Options.getContentPanel().add(landingPage, "LANDING");
        Options.getContentPanel().add(registrationPage, "REGISTRATION");
        Options.getContentPanel().add(homePage, "HOME");
        Options.getContentPanel().add(profilePage, "PROFILE");
        Options.getContentPanel().add(votePage, "VOTE");
        Options.getContentPanel().add(auditorPage, "AUDITOR");
        Options.getContentPanel().add(mediaPage, "MEDIA");

        setContentPane(Options.getContentPanel());

        Options.getCardLayout().show(Options.getContentPanel(), "LANDING");

        Options.createUsersTable();
    }
}
