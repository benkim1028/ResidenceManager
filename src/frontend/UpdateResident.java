package frontend;

import backend.Resident;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

/**
 * Created by GL on 2017-03-30.
 */
public class UpdateResident extends JPanel {

    private JTextField studentidText = new JTextField(12);
    private JButton updateResident = new JButton("Update Resident");
    private JButton viewResident = new JButton("View Residents");

    private JTextField ageText = new JTextField(5);
    private JTextField phoneText = new JTextField(20);
    private JTextField emailText = new JTextField(20);
    private JButton confirmUpdate = new JButton("Update");
    private JButton cancelUpdate = new JButton("Cancel");

    private String studentid = null;

    public UpdateResident(int width, int height) {
        super(new GridBagLayout());
        setPreferredSize(new Dimension(width, height));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(40, 40, 40, 40);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(createUpdateResidentPanel(), gbc);

        updateResident.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentid = studentidText.getText();
                if (Resident.exists(studentid)) {
                    String name = Resident.getName(studentid);
                    int age = Resident.getAge(studentid);
                    String phone = Resident.getPhone(studentid);
                    String email = Resident.getEmail(studentid);
                    removeAll();
                    add(createEditResidentPanel(studentid, name, age, phone, email));
                    validate();
                    repaint();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Student " + studentid + " does not exist", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        viewResident.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel dtm = Resident.viewResident();
                new ResultTable(dtm).setVisible(true);
            }
        });

        confirmUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (studentid != null) {
                    try {
                        int age = Integer.parseInt(ageText.getText());
                        try {
                            Resident.updateResident(studentid, Integer.parseInt(ageText.getText()), phoneText.getText(), emailText.getText());
                            studentid = null;
                            removeAll();
                            add(createUpdateResidentPanel());
                            validate();
                            repaint();
                        }catch (SQLException se) {
                            if (se.getErrorCode() == 2290) {
                                JOptionPane.showMessageDialog(null, "Invalid age, must be at least 18 years old", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                JOptionPane.showMessageDialog(null, se.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        }

                    }catch (NumberFormatException ne) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid age", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

        cancelUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentid = null;
                removeAll();
                add(createUpdateResidentPanel());
                validate();
                repaint();
            }
        });
    }

    private JPanel createUpdateResidentPanel() {
        JPanel updateResidentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(20, 20, 20, 20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        updateResidentPanel.add(new JLabel("Update Resident:"), gbc);

        gbc.gridy = 1;
        updateResidentPanel.add(new JLabel("Student ID: "), gbc);
        gbc.gridx = 1;
        updateResidentPanel.add(studentidText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        updateResidentPanel.add(updateResident, gbc);
        gbc.gridx = 1;
        updateResidentPanel.add(viewResident, gbc);

        return updateResidentPanel;
    }

    private JPanel createEditResidentPanel(String studentid, String name, int age, String phone, String email) {
        JPanel editResidentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        editResidentPanel.add(new JLabel("Student ID: "), gbc);
        gbc.gridx = 1;
        editResidentPanel.add(new JLabel(studentid), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        editResidentPanel.add(new JLabel("Name: "), gbc);
        gbc.gridx = 1;
        editResidentPanel.add(new JLabel(name), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        editResidentPanel.add(new JLabel("Age: "), gbc);
        gbc.gridx = 1;
        ageText.setText(Integer.toString(age));
        editResidentPanel.add(ageText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        editResidentPanel.add(new JLabel("Phone: "), gbc);
        gbc.gridx = 1;
        phoneText.setText(phone);
        editResidentPanel.add(phoneText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        editResidentPanel.add(new JLabel("Email: "), gbc);
        gbc.gridx = 1;
        emailText.setText(email);
        editResidentPanel.add(emailText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        editResidentPanel.add(confirmUpdate, gbc);
        gbc.gridx = 1;
        editResidentPanel.add(cancelUpdate, gbc);

        return editResidentPanel;
    }
}
