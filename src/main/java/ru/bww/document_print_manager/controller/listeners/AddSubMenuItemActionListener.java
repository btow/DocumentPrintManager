package ru.bww.document_print_manager.controller.listeners;

import ru.bww.document_print_manager.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by btow on 12.01.2019.
 */
public class AddSubMenuItemActionListener implements ActionListener {

    private boolean doubleRun = NONDOUBLE_RUN;
    private static final boolean DOUBLE_RUN = true;
    private static final boolean NONDOUBLE_RUN = false;

    public void setNonDoubleRun() {
        this.doubleRun = NONDOUBLE_RUN;
    }

    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        if (!doubleRun){
            //Auto-lock from reactivation
            doubleRun = DOUBLE_RUN;
            Main.INSTANSE.addDocToPrintQueue(actionEvent);
        }
    }

    public interface Interface {
        void addDocToPrintQueue(final ActionEvent actionEvent);
    }
}
