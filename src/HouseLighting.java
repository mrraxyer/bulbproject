import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HouseLighting extends JFrame {
    static int bulbCount = 0; // Counter for the number of bulbs
    static int doubleBulbCount = 0; // Counter for doubles
    static ArrayList<BulbPanel> bulbPanels = new ArrayList<>();
    static ArrayList<DualBulbPanel> dualBulbPanels = new ArrayList<>();

    // Constructor to set up the GUI
    public HouseLighting(){
        setTitle("House Lighting Control with Bulbs");
        setSize(400,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2)); // 3x2 grid layout
        setLocationRelativeTo(null); // Center the frame on the screen

        // Create rooms
        JPanel livingRoom = createRoomPanel("Living Room");
        JPanel kitchen = createRoomPanel("Kitchen");
        JPanel bedroom = createRoomPanel("Bedroom");
        JPanel bathroom = createRoomPanel("Bathroom");
        JPanel hallway = createRoomPanel("Hallway");
        JPanel garage = createRoomPanel("Garage");

        bulbCount+= 6; // Increment the bulb count for each room

        //Create menu for the application
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        JMenuItem bulkOff = new JMenuItem("Bulk Off");
        JMenuItem bulkRed = new JMenuItem("Bulk Red Color");
        JMenuItem bulkGreen = new JMenuItem("Bulk Green Color");
        JMenuItem bulkBlue = new JMenuItem("Bulk Blue Color");

        //Add a menu to add new bulbs
        JMenu addMenu = new JMenu("Add Bulb");

        JMenuItem addRoom = new JMenuItem("Add New Single Room");
        JMenuItem addDualRoom = new JMenuItem("Add Dual Bulb Room");

        // Add action listeners to menu items
        bulkOff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set all bulbs to off
                for (int i = 0; i < bulbPanels.size(); i++) {
                    bulbPanels.get(i).setColor(3);
                }
                for (int i = 0; i < dualBulbPanels.size(); i++) {
                    dualBulbPanels.get(i).setColorBoth(3);
                }
            }
        });

        bulkRed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set all bulbs to red
                for (int i = 0; i < bulbPanels.size(); i++) {
                    bulbPanels.get(i).setColor(1);
                }
                for (int i = 0; i < dualBulbPanels.size(); i++) {
                    dualBulbPanels.get(i).setColorBoth(1);
                }
            }
        });

        bulkGreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set all bulbs to green
                for (int i = 0; i < bulbPanels.size(); i++) {
                    bulbPanels.get(i).setColor(2);
                }
                for (int i = 0; i < dualBulbPanels.size(); i++) {
                    dualBulbPanels.get(i).setColorBoth(2);
                }
            }
        });

        bulkBlue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set all bulbs to blue
                for (int i = 0; i < bulbPanels.size(); i++) {
                    bulbPanels.get(i).setColor(0);
                }
                for (int i = 0; i < dualBulbPanels.size(); i++) {
                    dualBulbPanels.get(i).setColorBoth(0);
                }
            }
        });

        //Add a new room
        addRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new room panel and add it to the frame
                String name = JOptionPane.showInputDialog("Add a room name");
                if (name == null || name.isBlank()) return;
                JPanel newRoom = createRoomPanel(name);
                add(newRoom);
                bulbCount++;

                updateFrameSize();
                revalidate();
                repaint();
            }
        });

        // Add double bulb
        addDualRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel roomPanel = new JPanel(new BorderLayout());
                // Show input dialog to name the room
                String name = JOptionPane.showInputDialog("Add a room name");
                if (name == null || name.isBlank()) return;
                JLabel roomLabel = new JLabel(name, SwingConstants.CENTER);
                roomPanel.add(roomLabel, BorderLayout.NORTH);

                DualBulbPanel dualBulbPanel = new DualBulbPanel();
                dualBulbPanels.add(dualBulbPanel);
                roomPanel.add(dualBulbPanel, BorderLayout.CENTER);

                JButton lightButton = new JButton("Change Both Colors");
                roomPanel.add(lightButton, BorderLayout.SOUTH);

                lightButton.addActionListener(ev -> dualBulbPanel.toggleBoth());

                add(roomPanel);
                doubleBulbCount++;
                bulbCount += 2;
                updateFrameSize();
                revalidate();
                repaint();
            }
        });

        // Add menu items to the menu
        menu.add(bulkOff);
        menu.add(bulkRed);
        menu.add(bulkGreen);
        menu.add(bulkBlue);

        // Add the add room menu item
        addMenu.add(addRoom);
        addMenu.add(addDualRoom);

        // Add menu to the menu bar
        menuBar.add(menu);
        setJMenuBar(menuBar); // Set the menu bar for the frame

        // Add the add menu to the menu bar
        menuBar.add(addMenu);

        // Add initial rooms to the frame
        add(livingRoom);
        add(kitchen);
        add(bedroom);
        add(bathroom);
        add(hallway);
        add(garage);
    }

    private JPanel createRoomPanel(String roomName) {
        JPanel roomPanel = new JPanel();
        roomPanel.setLayout(new BorderLayout());

        JLabel roomLabel = new JLabel(roomName, SwingConstants.CENTER);
        roomPanel.add(roomLabel, BorderLayout.NORTH);

        // Custom panel to draw the bulb
        BulbPanel bulbPanel = new BulbPanel();
        bulbPanels.add(bulbPanel);
        roomPanel.add(bulbPanel, BorderLayout.CENTER);

        JButton lightButton = new JButton("Change Color");
        roomPanel.add(lightButton, BorderLayout.SOUTH);

        // Action listener to toggle light on/off
        lightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bulbPanel.toggleLight(); // Toggle the light in the bulb panel
            }
        });

        return roomPanel;
    }

    private void updateFrameSize() {
        setSize(400 + (bulbCount * 20) + 400, 600);
        setLocationRelativeTo(null);
    }
}