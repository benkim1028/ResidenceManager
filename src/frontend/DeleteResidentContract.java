package frontend;

import backend.Contract;
import backend.Residence;
import backend.Resident;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by GL on 2017-03-29.
 */
public class DeleteResidentContract extends JPanel{

    private JTextField studentidText = new JTextField(12);
    private JTextField contractidText = new JTextField(12);
    private JButton deleteResident = new JButton("Delete Resident");
    private JButton viewResident = new JButton("View Residents");
    private JButton deleteContract = new JButton("Delete Contract");
    private JButton viewContract = new JButton("View Contracts");

    public DeleteResidentContract(int width, int height) {
        super(new GridBagLayout());
        setPreferredSize(new Dimension(width, height));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(40, 40, 40, 40);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(createDeleteResidentPanel(), gbc);

        gbc.gridy = 1;
        add(createDeleteContractPanel(), gbc);

        deleteResident.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentid = studentidText.getText();
                if (Resident.exists(studentid)) {
                    String confirmMessage = "Delete resident " + studentid + " (" + Resident.getName(studentid) + ")?";
                    int response = JOptionPane.showConfirmDialog(null, confirmMessage, "Confirm", JOptionPane.CANCEL_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        Resident.deleteResident(studentid);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Resident " + studentid + " does not exist", "Error", JOptionPane.ERROR_MESSAGE);
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

        deleteContract.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String contractid = contractidText.getText();
                if (Contract.exists(contractid)) {
                    String confirmMessage = "Delete contract " + contractid + " (" + Resident.getName(Contract.getStudentid(contractid)) + ")?";
                    int response = JOptionPane.showConfirmDialog(null, confirmMessage, "Confirm", JOptionPane.CANCEL_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        Contract.deleteContract(contractid);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Contract " + contractid + " does not exist", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        viewContract.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel dtm = Contract.viewContract();
                new ResultTable(dtm).setVisible(true);
            }
        });
    }

    private JPanel createDeleteResidentPanel() {
        JPanel deleteResidentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(20, 20, 20, 20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        deleteResidentPanel.add(new JLabel("Delete Resident:"), gbc);

        gbc.gridy = 1;
        deleteResidentPanel.add(new JLabel("Student ID: "), gbc);
        gbc.gridx = 1;
        deleteResidentPanel.add(studentidText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        deleteResidentPanel.add(deleteResident, gbc);
        gbc.gridx = 1;
        deleteResidentPanel.add(viewResident, gbc);

        return deleteResidentPanel;
    }

    private JPanel createDeleteContractPanel() {
        JPanel deleteResidentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(20, 20, 20, 20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        deleteResidentPanel.add(new JLabel("Delete Contract:"), gbc);

        gbc.gridy = 1;
        deleteResidentPanel.add(new JLabel("Contract ID: "), gbc);
        gbc.gridx = 1;
        deleteResidentPanel.add(contractidText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        deleteResidentPanel.add(deleteContract, gbc);
        gbc.gridx = 1;
        deleteResidentPanel.add(viewContract, gbc);

        return deleteResidentPanel;
    }
}
