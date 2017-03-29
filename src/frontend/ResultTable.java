package frontend;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Created by GL on 2017-03-28.
 */
public class ResultTable extends JFrame {

    private final int WIDTH = 1000;
    private final int HEIGHT = 500;

    public ResultTable(DefaultTableModel dtm) {
        super("Search Result");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel tablePanel = new JPanel(new GridLayout(1, 0));

        final JTable table = new JTable(dtm);
        table.setPreferredScrollableViewportSize(new Dimension(WIDTH, HEIGHT));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane);
        tablePanel.setOpaque(true);
        setContentPane(tablePanel);

        pack();
        setLocationRelativeTo(null);
    }
}
