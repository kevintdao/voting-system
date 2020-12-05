import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Options {
    private static String[] languages = new String[] {"English", "Spanish", "French"};
    private static boolean darkMode = false;
    private static int languageIndex = 0; // default: English = 0
    private static CardLayout cardLayout = new CardLayout();
    private static JPanel contentPanel = new JPanel();

    // sql login
    private static final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/engr_class025";
    private static final String USERNAME = "engr_class025";
    private static final String PASSWORD = "ow9rw3hvWFX4sVcV";

    private static String currentUser = "";

    /*
        Index of combo box in each page
        0 = landing page
        1 = profile page
        2 = home page
        3 = registration page
        4 = vote page
    */
    private static JComboBox[] languageComboArray = new JComboBox[7];

    public static boolean getDarkMode(){
        return darkMode;
    }

    public static void setDarkMode(boolean value){
        darkMode = value;
    }

    public static int getLanguageIndex(){
         return languageIndex;
    }

    public static void setLanguageIndex(int index){
        languageIndex = index;
    }

    public static CardLayout getCardLayout(){
        return cardLayout;
    }

    public static JPanel getContentPanel(){
        return contentPanel;
    }

    public static JComboBox getLanguageComboBox(int index){
        return languageComboArray[index];
    }

    public static void setUpComboBox(){
        for(int i = 0; i < 7; i++){
            languageComboArray[i] = new JComboBox<String>(languages);
        }
    }

    public static void changeLanguage(){
        for(int i = 0; i < 7; i++){
            languageComboArray[i].setSelectedIndex(languageIndex);
        }
    }

    public static Connection getConnection(){
        Connection connection;
        try {
            connection = DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
            System.out.println("Connection Successful");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void createUsersTable(){
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS usertable(voterid int NOT NULL AUTO_INCREMENT, " +
                                        "username varchar(255)," +
                                        "password varchar(255)," +
                                        "firstname varchar(255)," +
                                        "lastname varchar(255)," +
                                        "dob date," +
                                        "county varchar(255)," +
                                        "state varchar(255)," +
                                        "ballotprogress varchar(255)," +
                                        "media bit NOT NULL DEFAULT 0," +
                                        "auditor bit NOT NULL DEFAULT 0," +
                                        "voter bit NOT NULL DEFAULT 1," +
                                        "KEY(voterid))");

            System.out.println("Created table");
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // add new user from registration page
    public static void addNewUser(String username, String password, String first, String last, String dob, String county, String state){
        try{
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO usertable (username, password, firstname, lastname, dob, county, state, media, auditor, voter)" +
                                        "VALUES ('"+username+"', '"+password+"', '"+first+"', '"+last+"', STR_TO_DATE('"+dob+"', '%m/%d/%Y'), '"+county+"', '"+state+"', 0, 0, 1)");

            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // check the login
    public static boolean checkLogin(String inputUserName, String inputPassword){
        try{
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM usertable");
            while(result.next()){
                if(result.getString("username").equals(inputUserName) && result.getString("password").equals(inputPassword)){
                    currentUser = inputUserName;
                    return true;
                }
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    // check if username already existed for registration
    public static boolean checkUsername(String inputUsername){
        try{
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM usertable");
            while(result.next()){
                if(result.getString("username").equals(inputUsername)){
                    return true;
                }
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<String> getUserInfo(){
        ArrayList<String> output = new ArrayList<>();
        try{
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM usertable WHERE username='"+currentUser+"'");
            while(result.next()){
                output.add(result.getString("voterid"));
                output.add(result.getString("username"));
                output.add(result.getString("firstname"));
                output.add(result.getString("lastname"));
                output.add(result.getString("dob"));
                output.add(result.getString("county"));
                output.add(result.getString("state"));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return output;
    }
}
