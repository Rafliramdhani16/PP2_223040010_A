package Membership.src.view.member;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import Membership.src.model.Member;

public class MemberTableModel extends AbstractTableModel {
    private String[] columnNames = {"Nama", "Jenis Member"};
    private List<Member> data;

    // Constructor
    public MemberTableModel(List<Member> data) {
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
        Member rowItem = data.get(row);
        String value = "";

        switch (col) {
            case 0:
                value = rowItem.getNama();
                break;
            case 1:
                value = rowItem.getJenisMember().getNama();
                break;
        }
        return value;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false; // Make all cells non-editable
    }

    // Method to add new member
    public void add(Member value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    // Method to update the entire dataset
    public void setData(List<Member> newData) {
        this.data = newData;
        fireTableDataChanged();
    }

    // Method to get Member object at specific row
    public Member getMemberAt(int row) {
        if (row >= 0 && row < data.size()) {
            return data.get(row);
        }
        return null;
    }

    // Method to remove a member
    public void removeMember(int row) {
        if (row >= 0 && row < data.size()) {
            data.remove(row);
            fireTableRowsDeleted(row, row);
        }
    }

    // Method to clear all data
    public void clearData() {
        data.clear();
        fireTableDataChanged();
    }

    // Method to get all data
    public List<Member> getData() {
        return data;
    }
}