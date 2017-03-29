package frontend;

import backend.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by GL on 2017-03-28.
 */
public class RoomSearch extends JPanel{

    private JCheckBox roomidBox = new JCheckBox("Room ID", true);
    private JCheckBox roomNumberBox = new JCheckBox("Room Number", true);
    private JCheckBox occupancyBox = new JCheckBox("Occupancy", true);
    private JCheckBox rnameBox = new JCheckBox("Residence Name", true);
    private JCheckBox addressBox = new JCheckBox("Address", true);
    private JCheckBox typeBox = new JCheckBox("Type", true);
    private JCheckBox accommodationBox = new JCheckBox("accommodation", true);
    private JCheckBox sizeBox = new JCheckBox("Size", true);
    private JCheckBox rateBox = new JCheckBox("Rate", true);
    private JCheckBox featuresBox = new JCheckBox("Features", true);

    private JTextField roomidText = new JTextField(12);
    private JTextField occupancyText = new JTextField(8);
    private JTextField accommodationText = new JTextField(8);
    private JTextField sizeText = new JTextField(8);
    private JTextField rateText = new JTextField(8);
    private JCheckBox kitchenBox = new JCheckBox("Kitchen");
    private JCheckBox bathroomBox = new JCheckBox("Bathroom");
    private JCheckBox loungeBox = new JCheckBox("Lounge");
    String[] typeString = {"any", "One Bed Room", "Two Bed Room", "Four Bed Room", "Six Bed Room", "Suite"};
    private JComboBox typeDrop = new JComboBox(typeString);
    String[] symbolString = {"<", ">", "=", "<=", ">="};
    private JComboBox occupancyDrop = new JComboBox(symbolString);
    private JComboBox accommodationDrop = new JComboBox(symbolString);
    private JComboBox sizeDrop = new JComboBox(symbolString);
    private JComboBox rateDrop = new JComboBox(symbolString);
    private JButton searchButton = new JButton("Search");

    public RoomSearch(int width, int height) {
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

        typeDrop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (typeDrop.getSelectedIndex() != 0) {
                    accommodationDrop.setEnabled(false);
                    accommodationText.setEnabled(false);
                    sizeDrop.setEnabled(false);
                    sizeText.setEnabled(false);
                    rateDrop.setEnabled(false);
                    rateText.setEnabled(false);
                    kitchenBox.setEnabled(false);
                    bathroomBox.setEnabled(false);
                    loungeBox.setEnabled(false);
                }
                else {
                    accommodationDrop.setEnabled(true);
                    accommodationText.setEnabled(true);
                    sizeDrop.setEnabled(true);
                    sizeText.setEnabled(true);
                    rateDrop.setEnabled(true);
                    rateText.setEnabled(true);
                    kitchenBox.setEnabled(true);
                    bathroomBox.setEnabled(true);
                    loungeBox.setEnabled(true);
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!roomidBox.isSelected() && !roomNumberBox.isSelected() && !occupancyBox.isSelected() && !rnameBox.isSelected() && !addressBox.isSelected()&&
                        !typeBox.isSelected() && !accommodationBox.isSelected() && !sizeBox.isSelected() && !rateBox.isSelected() && !featuresBox.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Please select at least one attribute", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (roomidText.getText().equals("") && occupancyText.getText().equals("") && (typeDrop.getSelectedIndex() == 0) && accommodationText.getText().equals("") && sizeText.getText().equals("") && rateText.getText().equals("") && !kitchenBox.isSelected() && !bathroomBox.isSelected() && !loungeBox.isSelected()) {
                    DefaultTableModel dtm = Room.searchRoom(roomidBox.isSelected(), roomNumberBox.isSelected(), occupancyBox.isSelected(), rnameBox.isSelected(), addressBox.isSelected(),
                            typeBox.isSelected(), accommodationBox.isSelected(), sizeBox.isSelected(), rateBox.isSelected(), featuresBox.isSelected());
                    new ResultTable(dtm).setVisible(true);
                }
            }
        });
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
        attributePanel.add(createCheckBoxPanel(roomidBox, roomNumberBox, occupancyBox, rnameBox, addressBox, typeBox, accommodationBox, sizeBox, rateBox, featuresBox), gbc);

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
        searchPanel.add(new JLabel("Room ID: "), gbc);
        gbc.gridx = 1;
        searchPanel.add(roomidText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        searchPanel.add(new JLabel("Occupancy: "), gbc);
        gbc.gridx = 1;
        searchPanel.add(createDropTextPanel(occupancyDrop, occupancyText), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        searchPanel.add(new JLabel("Type: "), gbc);
        gbc.gridx = 1;
        searchPanel.add(typeDrop, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        searchPanel.add(new JLabel("accommodation: "), gbc);
        gbc.gridx = 1;
        searchPanel.add(createDropTextPanel(accommodationDrop, accommodationText), gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        searchPanel.add(new JLabel("Size: "), gbc);
        gbc.gridx = 1;
        searchPanel.add(createDropTextPanel(sizeDrop, sizeText), gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        searchPanel.add(new JLabel("Rate: "), gbc);
        gbc.gridx = 1;
        searchPanel.add(createDropTextPanel(rateDrop, rateText), gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        searchPanel.add(new JLabel("Features: "), gbc);
        gbc.gridx = 1;
        searchPanel.add(createCheckBoxPanel(kitchenBox, bathroomBox, loungeBox), gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
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
