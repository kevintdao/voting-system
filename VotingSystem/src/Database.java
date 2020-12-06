import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Implements the functionality for connecting to mySQL database and executing queries.
 * Contains a variety of functions performing selects and inserts to the usertable, counties, election,
 * candidates, and votes tables. Includes functions to add a new user to table, check a user's password
 * for correctness, get user information, check for uniqueness of username, retrieve votes and election
 * information, check various statuses, and selecting various information for GUI components.
 * @author Kevin Dao, Cole Garton, Timothy Evans
 * @version 1.0.0, Dec 6 2020
 */
public class Database {
    // sql login
    private static final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/engr_class025";
    private static final String USERNAME = "engr_class025";
    private static final String PASSWORD = "ow9rw3hvWFX4sVcV";

    private static String currentUser = "";

    public static void setCurrentUser(String username){
        currentUser = username;
    }

    // get connection to sql server
    public static Connection getConnection(){
        Connection connection;
        try {
            connection = DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // add new user from registration page
    public static void addNewUser(String username, String password, String first, String last, String dob, String county, String state){
        String sqlString = "INSERT INTO usertable (username, password, firstname, lastname, dob, county, state, media, auditor, voter, countyID)" +
                "VALUES ('"+username+"', '"+password+"', '"+first+"', '"+last+"', STR_TO_DATE('"+dob+"', '%m/%d/%Y'), '"+county+"', '"+state+"',";

        try{
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

            if(username.contains("auditor:")){
                sqlString += "1, 1, 0,";
            }
            else if(username.contains("media:")){
                sqlString += "1, 0, 0,";
            }
            else{
                sqlString += "0, 0, 1,";
            }

            // get the countyID from database
            ResultSet result = statement.executeQuery("SELECT countyID FROM counties WHERE county='"+county+"'");
            int countyID = 0;
            while(result.next()){
                countyID = result.getInt("countyID");
            }

            // countyID not in database
            if(countyID == 0){
                statement.executeUpdate("INSERT INTO counties (county) VALUES ('"+county+"')"); // add the county into counties table
                result = statement.executeQuery("SELECT countyID FROM counties WHERE county='"+county+"'"); // get that countyid
                while(result.next()){
                    countyID = result.getInt("countyID");
                }
            }

            sqlString += "'"+countyID+"')";

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

    // return the user's info from the usertable in the database
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
                output.add(result.getString("countyID"));
            }
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return output;
    }

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

    // get the election result from the auditor's county from the database
    public static void getResult(){
        GUIComponents.getTextArea().setText("");
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            Statement statement1 = connection.createStatement();

            ResultSet result;
            int countyID = 0;
            ArrayList<Integer> electionIDs = new ArrayList<>();
            ArrayList<String> electionNames = new ArrayList<>();

            // get the countyID
            result = statement.executeQuery("SELECT username, countyID FROM usertable WHERE username='"+currentUser+"'");
            while(result.next()){
                countyID = result.getInt("countyID");
            }

            // get all the elections ids from that county id
            result = statement.executeQuery("SELECT * FROM election WHERE countyID='"+countyID+"'");
            while(result.next()){
                electionIDs.add(result.getInt("electionID"));
                electionNames.add(result.getString("electionName"));
            }

            // count up the vote
            String candidateName = "";
            for(int i = 0; i < electionIDs.size(); i++) {
                GUIComponents.getTextArea().append(electionNames.get(i) + "\n");

                result = statement.executeQuery("SELECT DISTINCT forCandidateID FROM votes WHERE electionID='"+electionIDs.get(i)+"'");
                while (result.next()) {
                    int currentCandidateID = result.getInt("forCandidateID");
                    candidateName = Database.getCandidateName(currentCandidateID);

                    ResultSet result1 = statement1.executeQuery("SELECT forCandidateID FROM votes WHERE forCandidateID='"+currentCandidateID+"'");

                    // get the number of vote for the current candidate id
                    int count = 0;
                    while(result1.next()){
                        count++;
                    }

                    GUIComponents.getTextArea().append(candidateName + " - " + count + " vote(s) " + "\n");
                }
                GUIComponents.getTextArea().append("\n");
            }
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

    // submit the user's votes
    public static void submitVote(ArrayList<String> selected) {
        ArrayList<Integer> userVotes = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            Statement statement1 = connection.createStatement();

            int countyID = 0;
            int voterID = 0;
            int candidateID = 0;
            ArrayList<Integer> electionIDs = new ArrayList<>();

            ResultSet result = statement.executeQuery("SELECT * FROM usertable WHERE username='"+currentUser+"'");

            // get the county id
            while(result.next()){
                voterID = result.getInt("voterID");
                countyID = result.getInt("countyID");
            }

            result = statement.executeQuery("SELECT electionID FROM election WHERE countyID='"+countyID+"'");

            // get the election ids
            while(result.next()){
                electionIDs.add(result.getInt("electionID"));
            }

            // get all candidate and candidateName from id
            for(int id : electionIDs) {
                result = statement.executeQuery("SELECT candidateID,candidateName FROM candidates WHERE electionID='"+id+"'");
                while (result.next()) {
                    if (selected.contains(result.getString("candidateName"))) {
                        candidateID = result.getInt("candidateID");
                        statement1.executeUpdate("INSERT votes (voterID, forCandidateID, electionID) VALUES ('" + voterID + "', '" + candidateID + "', '" + id + "')");
                    }
                }
            }
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // get the number of candidate in the user's county
    public static ArrayList<Integer> getCandidateAmount(){
        ArrayList<Integer> output =  new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

            int countyID = 0;
            ArrayList<Integer> electionIDs = new ArrayList<>();

            ResultSet result = statement.executeQuery("SELECT username, countyID FROM usertable WHERE username='"+currentUser+"'");

            while(result.next()){
                countyID = result.getInt("countyID");
            }

            result = statement.executeQuery("SELECT electionID FROM election WHERE countyID='"+countyID+"'");

            while(result.next()){
                electionIDs.add(result.getInt("electionID"));
            }

            for(int i = 0; i < electionIDs.size(); i++){
                result = statement.executeQuery("SELECT COUNT(candidateName) AS electioncount FROM candidates WHERE electionID='"+electionIDs.get(i)+"'");
                while(result.next()){
                    output.add(result.getInt("electioncount"));
                }
            }
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return output;
    }

    // get the list of election names from the user's county
    public static ArrayList<String> getElectionNames(){
        ArrayList<String> output =  new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

            int countyID = 0;

            ResultSet result = statement.executeQuery("SELECT username, countyID FROM usertable WHERE username='"+currentUser+"'");

            while(result.next()){
                countyID = result.getInt("countyID");
            }

            result = statement.executeQuery("SELECT electionName FROM election WHERE countyID='"+countyID+"'");

            while(result.next()){
                output.add(result.getString("electionName"));
            }
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return output;
    }

    // get the list of candidate names from the electionid
    public static ArrayList<String> getCandidateNames(int electionID){
        ArrayList<String> output =  new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery("SELECT candidateName FROM candidates WHERE electionID='"+electionID+"'");

            while(result.next()){
                output.add(result.getString("candidateName"));
            }
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return output;
    }

    // get the list of electionid based on the user's county
    public static ArrayList<Integer> getElectionIDs(){
        ArrayList<Integer> output =  new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

            int countyID = 0;

            ResultSet result = statement.executeQuery("SELECT username, countyID FROM usertable WHERE username='"+currentUser+"'");

            while(result.next()){
                countyID = result.getInt("countyID");
            }

            result = statement.executeQuery("SELECT electionID FROM election WHERE countyID='"+countyID+"'");

            while(result.next()){
                output.add(result.getInt("electionID"));
            }
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return output;
    }

    // inserting the candidates into database
    public static void insertCandidates(HashMap<String, ArrayList<String>> ballot, int electionIndex){
        try{
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sqlString = "";

            for(String pos : ballot.keySet()){
                for(int i = 0; i < ballot.get(pos).size(); i++){
                    if(ballot.get(pos).size() > 0){
                        sqlString = "INSERT INTO candidates (candidateName, electionID) " +
                                "VALUES ('"+ ballot.get(pos).get(i) + "', "+ electionIndex + ");";
                        statement.executeUpdate(sqlString);

                    }

                }
                if(ballot.get(pos).size()>0){
                    electionIndex++;
                }

            }
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // inserting the election into county
    public static int insertElections(HashMap<String, ArrayList<String>> ballot, int county){
        int electionIndex = 0;
        try{
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery("SELECT AUTO_INCREMENT" +
                    " FROM information_schema.TABLES" +
                    " WHERE TABLE_SCHEMA = 'engr_class025'" +
                    " AND TABLE_NAME = 'election'");
            while(result.next()){
                electionIndex = result.getInt(1);
            }

            for(String pos : ballot.keySet()){
                if(ballot.get(pos).size() > 0){
                    String sqlString = "INSERT INTO election (electionName, countyID) " +
                            "VALUES ('"+ pos + "', "+ county + ");";

                    statement.executeUpdate(sqlString);
                }

            }
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return electionIndex;
    }

    // check for unique county id in election database
    public static boolean checkUniqueCountyID(int id){
        boolean unique = false;

        ArrayList<String> output =  new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery("SELECT countyID FROM election WHERE countyID = "+id+";");

            if(!result.next()){
                unique = true;
            }
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return unique;
    }

    // update the voting status of the voter
    /*  status:
        NOT STARTED = not started
        IN PROGRESS = in progress
        FINISHED = finished
     */
    public static void updateVotingStatus(String status){
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

            statement.executeUpdate("UPDATE usertable SET ballotprogress='"+status+"' WHERE username='"+currentUser+"'");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // get the voting status of the voter
    public static String getVotingStatus(){
        String output = "";
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery("SELECT ballotprogress FROM usertable WHERE username='"+currentUser+"'");
            while(result.next()){
                output = result.getString("ballotprogress");
            }
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return output;
    }

    // get the candidate's name based on the candidateid in candidates table
    public static String getCandidateName(int candidateID){
        String output = "";
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT candidateName FROM candidates WHERE candidateID='"+candidateID+"'");
            while(result.next()){
                output = result.getString("candidateName");
            }
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return output;
    }
}
