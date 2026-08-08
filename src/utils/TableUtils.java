package utils;

import ui.UIConstants;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class TableUtils {
    public static void styleTable(JTable table) {
        table.setRowHeight(30);
        table.setFont(UIConstants.NORMAL_FONT);
        table.setSelectionBackground(UIConstants.PRIMARY_BLUE);
        table.setSelectionForeground(Color.WHITE);
        table.setFillsViewportHeight(true);
        table.setShowGrid(true);
        table.setGridColor(Color.LIGHT_GRAY);

        JTableHeader header = table.getTableHeader();
        header.setFont(UIConstants.BOLD_FONT);
        header.setBackground(UIConstants.SECONDARY_GRAY);
        header.setForeground(Color.BLACK);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}