import javax.swing.*;
import java.awt.*;

public class DualBulbPanel extends JPanel {
    private BulbPanel bulb1 = new BulbPanel();
    private BulbPanel bulb2 = new BulbPanel();

    public DualBulbPanel() {
        setLayout(new GridLayout(1, 2, 5, 0));
        add(bulb1);
        add(bulb2);
    }

    public void toggleBoth() {
        bulb1.toggleLight();
        bulb2.toggleLight();
    }

    public void setColorBoth(int color) {
        bulb1.setColor(color);
        bulb2.setColor(color);
    }
}