import javax.swing.*;
import java.awt.*;

public class Options {
    private static String[] languages = new String[] {"English", "Spanish", "French"};
    private static boolean darkMode = false;
    private static int languageIndex = 0; // default: English = 0
    private static CardLayout cardLayout = new CardLayout();
    private static JPanel contentPanel = new JPanel();

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
}
