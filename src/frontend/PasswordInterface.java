package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Created by GL on 2017-03-31.
 */
public class PasswordInterface extends JFrame {

    private final int width = 350;
    private final int height = 150;

    private final char[] password = {'1','2','3','4'};

    private JPasswordField passwordField = new JPasswordField(12);
    private JButton continueButton = new JButton("Continue");
    private JButton cancelButton = new JButton("Cancel");

    public PasswordInterface() {
        super("Password");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        JPanel newPanel = new JPanel(new GridBagLayout());
        newPanel.setPreferredSize(new Dimension(width, height));
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(new JLabel("Password: "), constraints);
        constraints.gridx = 1;
        newPanel.add(passwordField, constraints);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 1;
        newPanel.add(createButtonPanel(), constraints);

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] input = passwordField.getPassword();
                if (Arrays.equals(password, input)) {
                    new ManagerInterface().setVisible(true);
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Invalid Password", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StartInterface().setVisible(true);
                dispose();
            }
        });

        add(newPanel);
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createButtonPanel() {
        JPanel newPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(continueButton, constraints);
        constraints.gridx = 1;
        newPanel.add(cancelButton,constraints);

        return newPanel;
    }
}
