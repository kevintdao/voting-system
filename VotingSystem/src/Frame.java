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

//    private static final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/engr_class025";
//    private static final String USERNAME = "engr_class025";
//    private static final String PASSWORD = "ow9rw3hvWFX4sVcV";

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
        Options.getContentPanel().add(auditorPage, "AUDIT");
        Options.getContentPanel().add(mediaPage, "MEDIA");

        setContentPane(Options.getContentPanel());

        Options.getCardLayout().show(Options.getContentPanel(), "LANDING");

        Options.createUsersTable();
    }
}
