import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Options {
    private static String[] languages = new String[] {"English", "Spanish", "French"};
    private static boolean darkMode = false; // false = light mode
    private static int languageIndex = 0; // default: English = 0
    private static CardLayout cardLayout = new CardLayout();
    private static JPanel contentPanel = new JPanel();
    private static JToggleButton darkModeButton = new JToggleButton("Dark Mode");

    // sql login
    private static final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/engr_class025";
    private static final String USERNAME = "engr_class025";
    private static final String PASSWORD = "ow9rw3hvWFX4sVcV";

    private static String currentUser = "";

    private static final int NUM_OF_PANELS = 7;

    /*
        Index of combo box and dark mode button in each page
        0 = landing page
        1 = profile page
        2 = home page
        3 = registration page
        4 = vote page
        5 = auditor page
        6 = media page
    */
    private static JComboBox[] languageComboArray = new JComboBox[NUM_OF_PANELS];
    private static JToggleButton[] darkModeButtonArray = new JToggleButton[NUM_OF_PANELS];

    public static void setUpDarkModeButton(){
        for(int i = 0; i < 7; i++){
            darkModeButtonArray[i] = new JToggleButton("Light Mode");
            darkModeButtonArray[i].setSelected(false);
        }
    }

    public static JToggleButton getDarkModeButton(int index){
        return darkModeButtonArray[index];
    }

    public static void changeMode(boolean value){
        if (value) {
            for(int i = 0; i < 7; i++){
                darkModeButtonArray[i].setText("Dark Mode");
                darkModeButtonArray[i].setSelected(true);
            }
        }
        else{
            for(int i = 0; i < 7; i++){
                darkModeButtonArray[i].setText("Light Mode");
                darkModeButtonArray[i].setSelected(false);
            }
        }

        darkMode = value;
        Frame.updateTheme();
    }

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

    public static void setCurrentUser(String username){
        currentUser = username;
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
                                        "countyID int NOT NULL DEFAULT 0," +
                                        "KEY(voterid))");

//            statement.executeUpdate("CREATE TABLE IF NOT EXISTS election," +
//                                        "(electionID int NOT NULL AUTO_INCREMENT," +
//                                        "electionName varchar(255)," +
//                                        "countyID int," +
//                                        "PRIMARY KEY (electionID)," +
//                                        "FOREIGN KEY (countyID) REFERENCES usertable(countyID))");
            
            System.out.println("Created table");
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // add new user from registration page
    public static void addNewUser(String username, String password, String first, String last, String dob, String county, String state){
        String sqlString = "INSERT INTO usertable (username, password, firstname, lastname, dob, county, state, media, auditor, voter)" +
                                    "VALUES ('"+username+"', '"+password+"', '"+first+"', '"+last+"', STR_TO_DATE('"+dob+"', '%m/%d/%Y'), '"+county+"', '"+state+"',";

        try{
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

            if(username.contains("auditor:")){
                sqlString += "1, 1, 0)";
            }
            else if(username.contains("media:")){
                sqlString += "1, 0, 0)";
            }
            else{
                sqlString += "0, 0, 1)";
            }

            statement.executeUpdate(sqlString);

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

            connection.close();
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

            connection.close();
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

            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return output;
    }

    // check for auditor status
    public static boolean checkAuditorStatus(){
        try{
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM usertable WHERE username='"+currentUser+"'");
            while(result.next()){
                if(result.getBoolean("auditor")){
                    return true;
                }
            }

            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    // update the profile page
    public static void updateProfilePage(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JPanel panel = null;
                for(Component c : Options.getContentPanel().getComponents()){
                    if(c.getName().equals("Profile")){
                        panel = (JPanel) c;
                    }
                }

                /*
                    Index for userInfo:
                    0 = voterid
                    1 = username
                    2 = first name
                    3 = last name
                    4 = date of birth
                    5 = county
                    6 = state
                */
                ArrayList<String> userInfo = Options.getUserInfo();
                for(int i = 0; i < panel.getComponentCount(); i++){
                    if(panel.getComponent(i) instanceof JTextField){
                        String currentComponentName = panel.getComponent(i).getName();
                        JTextField textfield = (JTextField) panel.getComponent(i);

                        if(currentComponentName.equals("idField")){
                            textfield.setText(userInfo.get(0));
                        }
                        else if(currentComponentName.equals("usernameField")){
                            textfield.setText(userInfo.get(1));
                        }
                        else if(currentComponentName.equals("firstNameField")){
                            textfield.setText(userInfo.get(2));
                        }
                        else if(currentComponentName.equals("lastNameField")){
                            textfield.setText(userInfo.get(3));
                        }
                        else if(currentComponentName.equals("birthdayField")){
                            textfield.setText(userInfo.get(4));
                        }
                        else if(currentComponentName.equals("countyField")){
                            textfield.setText(userInfo.get(5));
                        }
                        else if(currentComponentName.equals("stateField")){
                            textfield.setText(userInfo.get(6));
                        }
                    }
                }
            }
        });
    }

    // clear the inputs from textfields in landing page
    public static void clearAllInputs(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JPanel panel = null;
                for(Component c : Options.getContentPanel().getComponents()){
                    if(c.getName().equals("Landing")){
                        panel = (JPanel) c;
                    }
                }

                for(int i = 0; i < panel.getComponentCount(); i++) {
                    if(panel.getComponent(i) instanceof JTextField){
                        JTextField textfield = (JTextField) panel.getComponent(i);
                        textfield.setText("");
                    }
                }
            }
        });
    }

    // get the election result from the database
    public static void getResult(){
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

            ResultSet result;
            int countyID = 0;
            ArrayList<Integer> electionIDs = new ArrayList<>();

            // get the countyID
            result = statement.executeQuery("SELECT username, countyID FROM usertable WHERE username='"+currentUser+"'");
            while(result.next()){
               countyID = result.getInt("countyID");
            }

            // get all the elections ids from that county id
            result = statement.executeQuery("SELECT * FROM election WHERE countyID='"+countyID+"'");
            while(result.next()){
               electionIDs.add(result.getInt("electionID"));
            }

            // count up the vote

            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // get the amount of elections in the specific county id
    public static int getElectionAmount(){
        int numberOfElections = 0;
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

            int countyID = 0;

            ResultSet result = statement.executeQuery("SELECT username, countyID FROM usertable WHERE username='"+currentUser+"'");

            while(result.next()){
                countyID = result.getInt("countyID");
            }

            result = statement.executeQuery("SELECT COUNT(electionid) AS electioncount FROM election WHERE countyID='"+countyID+"'");

            while(result.next()){
                numberOfElections = result.getInt("electioncount");
            }


            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return numberOfElections;
    }
}