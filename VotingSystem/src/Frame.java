import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public Frame() {
        super("voting system");
        VotePage votePage = new VotePage();
        add(votePage);
    }
}
