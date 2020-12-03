import javax.swing.*;

public class VotePanel extends JPanel {
    private JRadioButton candidate1;
    private JRadioButton candidate2;
    private JRadioButton candidate3;
    private JRadioButton candidate4;

    public VotePanel() {
        candidate1 = new JRadioButton("name 1");
        candidate2 = new JRadioButton("name 2");
        candidate3 = new JRadioButton("name 3");
        candidate4 = new JRadioButton("name 4");

        add(candidate1);
        add(candidate2);
        add(candidate3);
        add(candidate4);
    }
}
