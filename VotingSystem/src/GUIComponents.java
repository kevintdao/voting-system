import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Implements functionality for dark mode, language selection, profile updating,
 * and constructing voting ballots. Contains Swing components to be use globally in other JPanel or JFrame
 * @author Kevin Dao, Cole Garton, Timothy Evans
 * @version 1.0.0, Dec 6 2020
 */
public class GUIComponents {
    private static String[] languages = new String[] {"English", "Spanish", "French"};
    private static String[] lightModeLang = {"Light Mode", "Modo de luz", "Mode lumière"};
    private static String[] darkModeLang = {"Dark Mode", "Modo oscuro", "Mode sombre"};
    private static boolean darkMode = false; // false = light mode
    private static int languageIndex = 0; // default: English = 0
    private static CardLayout cardLayout = new CardLayout();
    private static JPanel contentPanel = new JPanel();

    private static final int NUM_OF_PANELS = 8;

    /*
        Index of combo box and dark mode button in each page
        0 = landing page
        1 = profile page
        2 = home page
        3 = registration page
        4 = vote page
        5 = auditor page
        6 = media page
        7 = create ballot page
    */
    private static JComboBox[] languageComboArray = new JComboBox[NUM_OF_PANELS];
    private static JToggleButton[] darkModeButtonArray = new JToggleButton[NUM_OF_PANELS];
    private static JTabbedPane tabs = new JTabbedPane();
    private static ArrayList<VoteTab> tabArray = new ArrayList<>();
    private static JProgressBar progressBar = new JProgressBar();
    private static JTextArea textArea = new JTextArea();

    public static void updateDarkModeButtonText(){
        if(darkMode){
            for(int i = 0; i < NUM_OF_PANELS; i++){
                darkModeButtonArray[i].setText(darkModeLang[languageIndex]);
            }
        }
        else{
            for(int i = 0; i < NUM_OF_PANELS; i++){
                darkModeButtonArray[i].setText(lightModeLang[languageIndex]);
            }
        }
    }

    public static JTextArea getTextArea(){
        return textArea;
    }

    public static JProgressBar getProgressBar(){
        return progressBar;
    }

    public static JTabbedPane getTabs(){
        return tabs;
    }

    public static ArrayList<VoteTab> getTabArray(){
        return tabArray;
    }

    // set up the tabs for each electionid for the current user's county
    public static void setUpTab(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> electionNames = Database.getElectionNames();
                ArrayList<Integer> candidateAmount = Database.getCandidateAmount();
                ArrayList<Integer> electionIDs = Database.getElectionIDs();

                for(int i = 0; i < Database.getElectionAmount(); i++) {
                    VoteTab voteTab = new VoteTab(candidateAmount.get(i));
                    tabArray.add(voteTab);

                    int currentElectionID = electionIDs.get(i);
                    ArrayList<String> candidateNames = Database.getCandidateNames(currentElectionID);

                    for (int j = 0; j < candidateNames.size(); j++) {
                        voteTab.getCandiateButton(j).setText(candidateNames.get(j));
                    }

                    tabs.addTab(electionNames.get(i), tabArray.get(i));
                }
            }
        });
    }

    public static void removeAllTabs(){
        while(tabs.getTabCount() != 0){
            tabs.remove(0);
        }
    }

    public static void emptyTabArray(){
        tabArray.clear();
    }


    public static void setUpDarkModeButton(){
        for(int i = 0; i < NUM_OF_PANELS; i++){
            darkModeButtonArray[i] = new JToggleButton("Light Mode");
            darkModeButtonArray[i].setSelected(false);
        }
    }

    public static JToggleButton getDarkModeButton(int index){
        return darkModeButtonArray[index];
    }

    public static void changeMode(boolean value){
        if (value) {
            for(int i = 0; i < NUM_OF_PANELS; i++){
                darkModeButtonArray[i].setText(darkModeLang[languageIndex]);
                darkModeButtonArray[i].setSelected(true);
            }
        }
        else{
            for(int i = 0; i < NUM_OF_PANELS; i++){
                darkModeButtonArray[i].setText(lightModeLang[languageIndex]);
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
        for(int i = 0; i < NUM_OF_PANELS; i++){
            languageComboArray[i] = new JComboBox<String>(languages);
        }
    }

    public static void changeLanguage(){
        for(int i = 0; i < NUM_OF_PANELS; i++){
            languageComboArray[i].setSelectedIndex(languageIndex);
        }
    }

    // update the profile page
    public static void updateProfilePage(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JPanel panel = null;
                for(Component c : GUIComponents.getContentPanel().getComponents()){
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
                ArrayList<String> userInfo = Database.getUserInfo();
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
                for(Component c : GUIComponents.getContentPanel().getComponents()){
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
}
