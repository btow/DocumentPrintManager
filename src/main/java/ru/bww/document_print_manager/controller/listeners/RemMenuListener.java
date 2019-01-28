package ru.bww.document_print_manager.controller.listeners;

import ru.bww.document_print_manager.Main;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 * Created by btow on 13.01.2019.
 */
public class RemMenuListener implements MenuListener {

    private boolean doubleRun = NONDOUBLE_RUN;
    private static final boolean DOUBLE_RUN = true;
    private static final boolean NONDOUBLE_RUN = false;

    public void setNonDoubleRun() {
        this.doubleRun = NONDOUBLE_RUN;
    }

    @Override
    public void menuSelected(final MenuEvent menuEvent) {
        if (!doubleRun){
            //Auto-lock from reactivation
            doubleRun = DOUBLE_RUN;
            Main.INSTANSE.remSelected(menuEvent);
        }
    }

    @Override
    public void menuDeselected(final MenuEvent menuEvent) {
        Main.INSTANSE.remSelected(null);
    }

    @Override
    public void menuCanceled(final MenuEvent menuEvent) {
        Main.INSTANSE.remSelected(null);
    }

    public interface Interface {
        public void remSelected(final MenuEvent menuEvent);
    }
}
