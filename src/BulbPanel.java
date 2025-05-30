import javax.swing.*;
import java.awt.*;

public class BulbPanel extends JPanel {
    private int status = 3; // 0: blue, 1: red, 2: green, 3: off

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw a light bulb
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the bulb outline
        g2d.setColor(Color.BLACK);
        g2d.drawOval(50, 20, 100, 100); // Bulb outline
        g2d.drawRect(85, 120, 30, 40); // Bulb base

        // Fill the bulb with light color depending on the state
        switch (status) {
            case 0:
                g2d.setColor(Color.BLUE);
                break;
            case 1:
                g2d.setColor(Color.RED);
                break;
            case 2:
                g2d.setColor(Color.GREEN);
                break;
            case 3:
                g2d.setColor(Color.GRAY);
        }

        g2d.fillOval(50, 20, 100, 100); // Fill the bulb

    }

    public void toggleLight() {
        // Toggle the light status
        if (status == 0) {
            status = 1; // Change to red
        } else if (status == 1) {
            status = 2; // Change to green
        } else if (status == 2) {
            status = 3; // Turn off
        } else {
            status = 0; // Change to blue
        }
        repaint(); // Repaint the panel to update the bulb color
    }

    public void setColor(int color) {
        // Set the bulb color directly
        status = color;
        repaint(); // Repaint the panel to update the bulb color
    }

    public int getStatus() {
        return status;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HouseLighting frame = new HouseLighting();
            frame.setVisible(true);
        });

    }
}


