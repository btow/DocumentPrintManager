package ru.bww.document_print_manager.controller.listeners;

import ru.bww.document_print_manager.Main;
import ru.bww.document_print_manager.model.DocumentType;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;

/**
 * Created by btow on 13.01.2019.
 */
public class DocumentTypeListSelectionListener implements ListSelectionListener {

    private boolean enableSelection = true;

    @Override
    public void valueChanged(final ListSelectionEvent listSelectionEvent) {
        if (listSelectionEvent != null){
            if (enableSelection){
                Main.INSTANSE.setListSelectionEvent(listSelectionEvent);
            } else {
                Main.INSTANSE.setListSelectionEvent(null);
            }
        }
    }

    public void setEnableSelection(final boolean enableSelection){
        this.enableSelection = enableSelection;
    }

    public interface Interface {
        public void setListSelectionEvent(final ListSelectionEvent listSelectionEvent);
    }
}
