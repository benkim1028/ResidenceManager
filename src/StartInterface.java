import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Created by GL on 2017-03-26.
 */
public class StartInterface extends JFrame {

    private JLabel instruction = new JLabel("Please select your user.");
    private JButton residentButton = new JButton("Resident");
    private JButton managerButton = new JButton("Manager");

    public StartInterface() {
        super("Resident Manager");
        JPanel newPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(20, 20, 20, 20);

        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(instruction, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        newPanel.add(residentButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        newPanel.add(managerButton, constraints);

        residentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new UserInterface().setVisible(true);
                dispose();
            }
        });

        managerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new UserInterface().setVisible(true);
                dispose();
            }
        });

        add(newPanel);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StartInterface().setVisible(true);
            }
        });
    }
}
