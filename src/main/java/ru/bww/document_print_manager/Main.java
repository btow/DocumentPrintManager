package ru.bww.document_print_manager;

import ru.bww.document_print_manager.controller.*;
import ru.bww.document_print_manager.controller.listeners.*;
import ru.bww.document_print_manager.model.DocumentType;
import ru.bww.document_print_manager.model.DocumentTypeList;
import ru.bww.document_print_manager.view.PrintManager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.MenuEvent;
import java.awt.event.ActionEvent;
import java.lang.String;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;

/**
 * Created by btow on 10.01.2019.
 */
public class Main implements
        PrintManagerController.Interface,
        StopMenuListener.Interface,
        AddSubMenuItemActionListener.Interface,
        DocumentTypeListSelectionListener.Interface,
        RemMenuListener.Interface,
        AscendingPrintNumberActionListener.Interface,
        DescendingPrintNumberActionListener.Interface,
        AscendingDocumentTypeActionListener.Interface,
        DescendingDocumentTypeActionListener.Interface,
        AscendingDurationOfPrintActionListener.Interface,
        DescendingDurationOfPrintActionListener.Interface,
        AscendingPaperSizeActionListener.Interface,
        DescendingPaperSizeActionListener.Interface,
        CompMenuListener.Interfase {

    private final static String THREAD_NAME = "print";
    private static PrintManagerController printManagerController;
    private static PrintManager printManager;
    private static DocumentTypeList documentTypeList;
    private ListSelectionEvent listSelectionEvent;
    public final static Main INSTANSE = new Main();
    public static final boolean TURN_ON = true;
    public static final boolean TURN_OFF = false;
    public static final boolean PRINT_MODE = false;
    public static final boolean QUEUE_MODE = true;

    private Main() {
        super();
    }

    public static void main(final String[] args) {
        documentTypeList = new DocumentTypeList();
        printManager = new PrintManager(documentTypeList, QUEUE_MODE);
        printManagerController
                = new PrintManagerController(THREAD_NAME, documentTypeList);
        printManagerController.start();
    }

    @Override
    public void stopSelected(final MenuEvent menuEvent) {
        if (menuEvent != null) {
            printManagerController.setToByContinue(false);
            printManagerController.interrupt();
            setListSelectionEvent(null);
            printManager.switchListSelectModelListener(TURN_OFF);
            printManager.showMessageDialog(
                    "Менеджер печати документов остановлен",
                    "Внимание!",
                    JOptionPane.CLOSED_OPTION);
        }
    }

    @Override
    public void addDocToPrintQueue(final ActionEvent actionEvent) {
        try {
            BigInteger lastPrinNumber = new BigInteger("0");
            if (documentTypeList.getPrintQueue().size() > 0) {
                lastPrinNumber =
                        documentTypeList.getPrintQueue().get(
                                documentTypeList.getPrintQueue().size() - 1).getNumberInQueue();
            } else if (documentTypeList.getPrintedDocs().size() > 0) {
                lastPrinNumber =
                        documentTypeList.getPrintedDocs().get(
                                documentTypeList.getPrintedDocs().size() - 1).getNumberInQueue();
            }
            Class<?> c = Class.forName("ru.bww.document_print_manager.model.document_types." + actionEvent.getActionCommand());
            Constructor<?> constructor = c.getConstructor(BigInteger.class);
            documentTypeList.addDocInQueue((DocumentType) constructor.newInstance(lastPrinNumber.add(new BigInteger("1"))));
            printManager.setTableMode(QUEUE_MODE);
            printManager.updateTable(listSelectionEvent);
        } catch (ClassNotFoundException | NoSuchMethodException |
                IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTable() {
        printManager.updateTable(listSelectionEvent);
    }

    @Override
    public void setListSelectionEvent(final ListSelectionEvent listSelectionEvent) {
        this.listSelectionEvent = listSelectionEvent;
        if (this.listSelectionEvent != null) {
            printManager.setRemMenuState(true);
        } else {
            printManager.clearSelection();
            printManager.setRemMenuState(false);
        }
    }

    @Override
    public void remSelected(final MenuEvent menuEvent) {
        if (menuEvent != null) {
            printManagerController.setToByContinue(false);
            printManagerController.interrupt();
            printManager.remSelected(listSelectionEvent);
            printManagerController
                    = new PrintManagerController(THREAD_NAME, documentTypeList);
            printManagerController.start();
        }
    }

    @Override
    public void ascendingPrintNumberSelected() {
        printManager.ascendingPrintNumberSort();
    }

    @Override
    public void descendingPrintNumberSelected() {
        printManager.descendingPrintNumberSort();
    }

    @Override
    public void ascendingDocumentTypeSelected() {
        printManager.ascendingDocumentTypeSort();
    }

    @Override
    public void descendingDocumentTypeSelected() {
        printManager.descendingDocumentTypeSort();
    }

    @Override
    public void ascendingDurationOfPrintSelected() {
        printManager.ascendingDurationOfPrintSort();
    }

    @Override
    public void descendingDurationOfPrintSelected() {
        printManager.descendingDurationOfPrintSort();
    }

    @Override
    public void ascendingPaperSizeSelected() {
        printManager.ascendingPaperSizeSort();
    }

    @Override
    public void descendingPaperSizeSelected() {
        printManager.descendingPaperSizeSort();
    }

    @Override
    public void compSelected(final MenuEvent menuEvent) {
        if (menuEvent != null)
            printManager.showMessageDialog(
                    String.format("Среднее время печати напечатанных документов %s мсек",
                            printManagerController.getMeanDurationOfPrint().toString()),
                    "Сообщение",
                    JOptionPane.CLOSED_OPTION);
    }
}
