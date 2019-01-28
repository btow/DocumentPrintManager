package ru.bww.document_print_manager.controller;

import ru.bww.document_print_manager.controller.listeners.*;
/**
 * Created by btow on 13.01.2019.
 */
public class PrintManagerMenuController extends Thread {
    private StopMenuListener stopMenuListener;
    private AddSubMenuItemActionListener addSubMenuItemActionListener;
    private RemMenuListener remMenuListener;
    private AscendingPrintNumberActionListener ascendingPrintNumberActionListener;
    private DescendingPrintNumberActionListener descendingPrintNumberActionListener;
    private AscendingDocumentTypeActionListener ascendingDocumentTypeActionListener;
    private DescendingDocumentTypeActionListener descendingDocumentTypeActionListener;
    private AscendingDurationOfPrintActionListener ascendingDurationOfPrintActionListener;
    private DescendingDurationOfPrintActionListener descendingDurationOfPrintActionListener;
    private AscendingPaperSizeActionListener ascendingPaperSizeActionListener;
    private DescendingPaperSizeActionListener descendingPaperSizeActionListener;
    private CompMenuListener compMenuListener;

    public PrintManagerMenuController(final StopMenuListener stopMenuListener,
                                      final AddSubMenuItemActionListener addSubMenuItemActionListener,
                                      final RemMenuListener remMenuListener,
                                      final AscendingPrintNumberActionListener ascendingPrintNumberActionListener,
                                      final DescendingPrintNumberActionListener descendingPrintNumberActionListener,
                                      final AscendingDocumentTypeActionListener ascendingDocumentTypeActionListener,
                                      final DescendingDocumentTypeActionListener descendingDocumentTypeActionListener,
                                      final AscendingDurationOfPrintActionListener ascendingDurationOfPrintActionListener,
                                      final DescendingDurationOfPrintActionListener descendingDurationOfPrintActionListener,
                                      final AscendingPaperSizeActionListener ascendingPaperSizeActionListener,
                                      final DescendingPaperSizeActionListener descendingPaperSizeActionListener,
                                      final CompMenuListener compMenuListener) {
        this.stopMenuListener = stopMenuListener;
        this.addSubMenuItemActionListener = addSubMenuItemActionListener;
        this.remMenuListener = remMenuListener;
        this.ascendingPrintNumberActionListener = ascendingPrintNumberActionListener;
        this.descendingPrintNumberActionListener = descendingPrintNumberActionListener;
        this.ascendingDocumentTypeActionListener = ascendingDocumentTypeActionListener;
        this.descendingDocumentTypeActionListener = descendingDocumentTypeActionListener;
        this.ascendingDurationOfPrintActionListener = ascendingDurationOfPrintActionListener;
        this.descendingDurationOfPrintActionListener = descendingDurationOfPrintActionListener;
        this.ascendingPaperSizeActionListener = ascendingPaperSizeActionListener;
        this.descendingPaperSizeActionListener = descendingPaperSizeActionListener;
        this.compMenuListener = compMenuListener;
    }

    @Override
    public void run() {
        try {
            //noinspection InfiniteLoopStatement
            while (true) {
                Thread.sleep(100);
                this.stopMenuListener.setNonDoubleRun();
                this.addSubMenuItemActionListener.setNonDoubleRun();
                this.remMenuListener.setNonDoubleRun();
                this.ascendingPrintNumberActionListener.setNonDoubleRun();
                this.descendingPrintNumberActionListener.setNonDoubleRun();
                this.ascendingDocumentTypeActionListener.setNonDoubleRun();
                this.descendingDocumentTypeActionListener.setNonDoubleRun();
                this.ascendingDurationOfPrintActionListener.setNonDoubleRun();
                this.descendingDurationOfPrintActionListener.setNonDoubleRun();
                this.ascendingPaperSizeActionListener.setNonDoubleRun();
                this.descendingPaperSizeActionListener.setNonDoubleRun();
                this.compMenuListener.setNonDoubleRun();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
