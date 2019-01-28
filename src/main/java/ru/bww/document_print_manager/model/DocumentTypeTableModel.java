package ru.bww.document_print_manager.model;

import javafx.util.Pair;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by btow on 12.01.2019.
 */
public class DocumentTypeTableModel extends AbstractTableModel {

    private List<DocumentType> documentTypes;

    public DocumentTypeTableModel(final List<DocumentType> documentTypes) {
        this.documentTypes = documentTypes;
    }

    @Override
    public int getRowCount() {
        return documentTypes.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Class<?> getColumnClass(final int columnIndex) {
        switch (columnIndex){
            case 1: return String.class;
            case 2: return Integer.class;
            case 3: return Pair.class;
            default: return BigInteger.class;
        }
    }

    @Override
    public Object getValueAt(final int rowIndex, final int columnIndex) {
        switch (columnIndex){
            case 1: return documentTypes.get(rowIndex).getTypeName();
            case 2: return documentTypes.get(rowIndex).getDurationOfPrint();
            case 3: return documentTypes.get(rowIndex).getPaperSize();
            default: return  documentTypes.get(rowIndex).getNumberInQueue();
        }
    }

    @Override
    public void fireTableDataChanged() {
        super.fireTableDataChanged();
    }
}
