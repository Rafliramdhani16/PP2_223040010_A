package Membership.src.view.jenismember;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import Membership.src.model.JenisMember;

public class JenisMemberTableModel extends AbstractTableModel {
    private String[] columnNames = {"Nama"};
    private List<JenisMember> data;

    public JenisMemberTableModel(List<JenisMember> data) {
        this.data = data;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        JenisMember rowItem = data.get(row);
        return rowItem.getNama();
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void add(JenisMember value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void setData(List<JenisMember> newData) {
        this.data = newData;
        fireTableDataChanged();
    }

    public JenisMember getJenisMemberAt(int row) {
        if (row >= 0 && row < data.size()) {
            return data.get(row);
        }
        return null;
    }
}
