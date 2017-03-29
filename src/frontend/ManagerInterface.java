package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by GL on 2017-03-28.
 */
public class ManagerInterface extends JFrame {

    private final int WIDTH = 800;
    private final int HEIGHT = 650;

    private JMenuBar menuBar = new JMenuBar();
    private JMenu searchMenu = new JMenu("Search");
    private JMenu updateMenu = new JMenu("Update");
    private JMenu optionsMenu = new JMenu("Options");
    private JMenuItem sResidentMenu = new JMenuItem("Search Residents");
    private JMenuItem sRoomMenu = new JMenuItem("Search Rooms");
    private JMenuItem uResidentMenu = new JMenuItem("Update Residents");
    private JMenuItem uRoomMenu = new JMenuItem("Update Rooms");
    private JMenuItem changeUserMenu = new JMenuItem("Change User");
    private JMenuItem exitMenu = new JMenuItem("Exit");

    public ManagerInterface() {
        super("Residence Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        final JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        searchMenu.setMargin(new Insets(0, 20, 0, 20));
        searchMenu.add(sResidentMenu);
        searchMenu.add(sRoomMenu);
        optionsMenu.setMargin(new Insets(0, 20, 0, 20));
        optionsMenu.add(changeUserMenu);
        optionsMenu.add(exitMenu);
        menuBar.add(searchMenu);
        menuBar.add(optionsMenu);
        setJMenuBar(menuBar);

        sResidentMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                mainPanel.add(new ResidentSearch(WIDTH, HEIGHT));
                mainPanel.validate();
                mainPanel.repaint();
            }
        });

        sRoomMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                mainPanel.add(new RoomSearch(WIDTH, HEIGHT));
                mainPanel.validate();
                mainPanel.repaint();
            }
        });

        changeUserMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StartInterface().setVisible(true);
                dispose();
            }
        });

        exitMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }
}
