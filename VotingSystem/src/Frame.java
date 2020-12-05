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
    private CreateBallot createBallot;

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
    }
}
