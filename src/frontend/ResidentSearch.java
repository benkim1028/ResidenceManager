package frontend;

import javax.swing.*;
import java.awt.*;

/**
 * Created by GL on 2017-03-28.
 */
public class ResidentSearch extends JPanel{

    private JCheckBox studentidBox = new JCheckBox("Student ID", true);
    private JCheckBox nameBox = new JCheckBox("Name", true);
    private JCheckBox phoneBox = new JCheckBox("Phone", true);
    private JCheckBox emailBox = new JCheckBox("Email", true);
    private JCheckBox roomidBox = new JCheckBox("Room ID", true);
    private JCheckBox contractidBox = new JCheckBox("Contract ID", true);

    private JTextField studentidText = new JTextField(12);
    private JTextField nameText = new JTextField(30);
    private JTextField phoneText = new JTextField(30);
    private JTextField emailText = new JTextField(30);
    private JTextField roomidText = new JTextField(12);
    private JTextField contractidText = new JTextField(12);
    private JButton searchButton = new JButton("Search");

    public ResidentSearch(int width, int height) {
        super(new GridBagLayout());
        setPreferredSize(new Dimension(width, height));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(20, 20, 20, 20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(createAttributePanel(), gbc);

        gbc.gridy = 1;
        add(createSearchPanel(), gbc);
    }

    private JPanel createAttributePanel() {
        JPanel attributePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        attributePanel.add(new JLabel("Show attributes:"), gbc);
        gbc.gridy = 1;
        attributePanel.add(createCheckBoxPanel(studentidBox, nameBox, phoneBox, emailBox, roomidBox, contractidBox), gbc);

        return attributePanel;
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        searchPanel.add(new JLabel("Search by:"), gbc);

        gbc.gridy = 1;
        searchPanel.add(new JLabel("Student ID: "), gbc);
        gbc.gridx = 1;
        searchPanel.add(studentidText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        searchPanel.add(new JLabel("Name: "), gbc);
        gbc.gridx = 1;
        searchPanel.add(nameText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        searchPanel.add(new JLabel("Phone: "), gbc);
        gbc.gridx = 1;
        searchPanel.add(phoneText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        searchPanel.add(new JLabel("Email: "), gbc);
        gbc.gridx = 1;
        searchPanel.add(emailText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        searchPanel.add(new JLabel("Room ID: "), gbc);
        gbc.gridx = 1;
        searchPanel.add(roomidText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        searchPanel.add(new JLabel("Contract ID: "), gbc);
        gbc.gridx = 1;
        searchPanel.add(contractidText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        searchPanel.add(searchButton, gbc);

        return searchPanel;
    }

    private JPanel createCheckBoxPanel(JCheckBox... checkBoxes) {
        JPanel checkBoxPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridy = 0;
        int i = 0;
        for (JCheckBox jcb : checkBoxes) {
            if (i >= 5) {
                gbc.gridy++;
                i = i - 5;
            }
            gbc.gridx = i;
            checkBoxPanel.add(jcb, gbc);
            i++;
        }

        return checkBoxPanel;
    }

    private JPanel createDropTextPanel(JComboBox dropDown, JTextField textField) {
        JPanel dropTextPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 5, 10, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        dropTextPanel.add(dropDown);
        gbc.gridx = 1;
        dropTextPanel.add(textField);

        return dropTextPanel;
    }
}