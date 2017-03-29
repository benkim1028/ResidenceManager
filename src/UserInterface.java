import backend.Resident;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Created by GL on 2017-03-26.
 */
public class UserInterface extends JFrame {

    private JLabel labelSelect = new JLabel("SELECT: ");
    private JLabel labelFrom = new JLabel("FROM: ");
    private JLabel labelWhere = new JLabel("WHERE: ");
    private JTextField textSelect = new JTextField(40);
    private JTextField textFrom = new JTextField(40);
    private JTextField textWhere = new JTextField(40);
    private JButton submit = new JButton("Submit");

    public UserInterface() {
        super("Resident Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel newPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(labelSelect, constraints);

        constraints.gridx = 1;
        newPanel.add(textSelect, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        newPanel.add(labelFrom, constraints);

        constraints.gridx = 1;
        newPanel.add(textFrom, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        newPanel.add(labelWhere, constraints);

        constraints.gridx = 1;
        newPanel.add(textWhere, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(submit, constraints);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String select = textSelect.getText();
                String from = textFrom.getText();
                String where = textWhere.getText();
                try {
                    ResultSet rs = Resident.testMethod(select, from, where);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int col = rsmd.getColumnCount();
                    while (rs.next()) {
                        for (int i = 1; i <= col; i++) {
                            String str = rs.getString(i);
                            System.out.println(rsmd.getColumnLabel(i) + ": " + str);
                        }
                        System.out.print("\n");
                    }
                }catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                    System.out.println(e.toString());
                }

            }
        });

        add(newPanel);
        pack();
        setLocationRelativeTo(null);
    }
}
