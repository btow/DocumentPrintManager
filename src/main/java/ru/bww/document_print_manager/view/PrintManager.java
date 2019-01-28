package ru.bww.document_print_manager.view;

import ru.bww.document_print_manager.Main;
import ru.bww.document_print_manager.controller.PrintManagerMenuController;
import ru.bww.document_print_manager.controller.listeners.*;
import ru.bww.document_print_manager.model.DocumentType;
import ru.bww.document_print_manager.model.DocumentTypeList;
import ru.bww.document_print_manager.model.DocumentTypeTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;

/**
 * Created by btow on 10.01.2019.
 */
public class PrintManager extends JFrame {

    private static final int COLUMN_MARGIN = 5;
    private static final boolean PRIM_STEP = true;
    private static final boolean NON_PRIM_STEP = false;
    private final DocumentTypeList documentTypeList;
    private boolean tableMode;
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JTable tableDocs;
    private JMenuBar menuBar = new JMenuBar();
    private DocumentTypeListSelectionListener documentTypeListSelectionListener
            = new DocumentTypeListSelectionListener();
    private DocumentTypeTableModel printQueueDocumentTypeTableModel = null;
    private DocumentTypeTableModel printedDocumentTypeTableModel = null;

    private StopMenuListener stopMenuListener
            = new StopMenuListener();
    private AddSubMenuItemActionListener addSubMenuItemActionListener
            = new AddSubMenuItemActionListener();
    private RemMenuListener remMenuListener
            = new RemMenuListener();
    private AscendingPrintNumberActionListener ascendingPrintNumberActionListener
            = new AscendingPrintNumberActionListener();
    private DescendingPrintNumberActionListener descendingPrintNumberActionListener
            = new DescendingPrintNumberActionListener();
    private AscendingDocumentTypeActionListener ascendingDocumentTypeActionListener
            = new AscendingDocumentTypeActionListener();
    private DescendingDocumentTypeActionListener descendingDocumentTypeActionListener
            = new DescendingDocumentTypeActionListener();
    private AscendingDurationOfPrintActionListener ascendingDurationOfPrintActionListener
            = new AscendingDurationOfPrintActionListener();
    private DescendingDurationOfPrintActionListener descendingDurationOfPrintActionListener
            = new DescendingDurationOfPrintActionListener();
    private AscendingPaperSizeActionListener ascendingPaperSizeActionListener
            = new AscendingPaperSizeActionListener();
    private DescendingPaperSizeActionListener descendingPaperSizeActionListener
            = new DescendingPaperSizeActionListener();
    private CompMenuListener compMenuListener
            = new CompMenuListener();

    public PrintManager(final DocumentTypeList documentTypeList, final boolean tableMode) {
        super("Менеджер печати документов");
        this.documentTypeList = documentTypeList;
        this.tableMode = tableMode;
        this.setContentPane(this.mainPanel);
        setUpTableDocsToPrintQueue(tableDocs, PRIM_STEP);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 550, 300);
        this.setJMenuBar(createMenuBar());
        this.setVisible(true);
        new PrintManagerMenuController(stopMenuListener
                , addSubMenuItemActionListener
                , remMenuListener
                , ascendingPrintNumberActionListener
                , descendingPrintNumberActionListener
                , ascendingDocumentTypeActionListener
                , descendingDocumentTypeActionListener
                , ascendingDurationOfPrintActionListener
                , descendingDurationOfPrintActionListener
                , ascendingPaperSizeActionListener
                , descendingPaperSizeActionListener
                , compMenuListener
        ).start();
    }

    private void setUpTableDocsToPrintQueue(final JTable tableDocs, final boolean primStep) {
        if (printQueueDocumentTypeTableModel == null)
            printQueueDocumentTypeTableModel = new DocumentTypeTableModel(documentTypeList.getPrintQueue());
        tableDocs.setModel(printQueueDocumentTypeTableModel);
        tableDocs.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (primStep) {
            tableDocs.getSelectionModel().addListSelectionListener(documentTypeListSelectionListener);
        }
        documentTypeListSelectionListener.setEnableSelection(Main.TURN_ON);
        columnModelSetUp(tableDocs.getColumnModel());
    }

    private void setUpTableDocsToPrintedDocs(JTable tableDocs) {
        tableMode = Main.PRINT_MODE;
        if (printedDocumentTypeTableModel == null)
            printedDocumentTypeTableModel = new DocumentTypeTableModel(documentTypeList.getPrintedDocs());
        tableDocs.setModel(printedDocumentTypeTableModel);
        documentTypeListSelectionListener.setEnableSelection(Main.TURN_OFF);
        columnModelSetUp(tableDocs.getColumnModel());
    }

    private void columnModelSetUp(final TableColumnModel columnModel) {
        columnModel.setColumnMargin(COLUMN_MARGIN);
        Enumeration<TableColumn> columnEnumeration = columnModel.getColumns();
        while (columnEnumeration.hasMoreElements()) {
            TableColumn column = columnEnumeration.nextElement();
            switch (column.getModelIndex()) {
                case 1:
                    column.setHeaderValue("Тип документа");
                    break;
                case 2:
                    column.setHeaderValue("Продолжительность печати");
                    break;
                case 3:
                    column.setHeaderValue("Размер бумаги");
                    break;
                default:
                    column.setHeaderValue("Номер в очереди");
                    break;
            }
        }
    }

    private JMenuBar createMenuBar() {
        JMenu stopMenu = new JMenu("Стоп");
        stopMenu.setMnemonic('т');
        stopMenu.addMenuListener(stopMenuListener);
        stopMenu.setToolTipText("Остановить менеджер печати документов");
        menuBar.add(stopMenu);
        JMenu addMenu = new JMenu("Добавить в очередь");
        addMenu.setMnemonic('Д');
        addMenu.setToolTipText("Добавить документ в очередь");
        addAllSubMenuToAddMenu(addMenu);
        menuBar.add(addMenu);
        JMenu remMenu = new JMenu("Удалить из очереди");
        remMenu.setMnemonic('У');
        remMenu.setToolTipText("Удалить выбранный документ из очереди");
        remMenu.setEnabled(false);
        remMenu.addMenuListener(remMenuListener);
        menuBar.add(remMenu);
        JMenu listMenu = new JMenu("Список напечатанных");
        listMenu.setMnemonic('п');
        listMenu.setToolTipText("Показать список напечатанных документов");
        addAllSubMenuToListMenu(listMenu);
        menuBar.add(listMenu);
        JMenu compMenu = new JMenu("Вычислить");
        compMenu.setMnemonic('В');
        compMenu.setToolTipText("Вычислить среднее время печати отпечатанных документов");
        compMenu.addMenuListener(compMenuListener);
        menuBar.add(compMenu);
        return menuBar;
    }

    private void addAllSubMenuToListMenu(final JMenu listMenu) {
        JMenuItem sortList = new JMenuItem("Сортировать");
        listMenu.add(sortList);
        JMenu onPrintNumberSubMenu = new JMenu("по порядку печати");
        onPrintNumberSubMenu.setMnemonic('ч');
        JMenuItem ascendingPrintNumber = new JMenuItem("по возрастанию");
        ascendingPrintNumber.setMnemonic('в');
        ascendingPrintNumber.addActionListener(ascendingPrintNumberActionListener);
        onPrintNumberSubMenu.add(ascendingPrintNumber);
        JMenuItem descendingPrintNumber = new JMenuItem("по убыванию");
        descendingPrintNumber.setMnemonic('у');
        descendingPrintNumber.addActionListener(descendingPrintNumberActionListener);
        onPrintNumberSubMenu.add(descendingPrintNumber);
        listMenu.add(onPrintNumberSubMenu);

        JMenu onDocumentTypeSubMenu = new JMenu("по типу документов");
        onDocumentTypeSubMenu.setMnemonic('и');
        JMenuItem ascendingDocumentType = new JMenuItem("по возрастанию");
        ascendingDocumentType.setMnemonic('в');
        ascendingDocumentType.addActionListener(ascendingDocumentTypeActionListener);
        onDocumentTypeSubMenu.add(ascendingDocumentType);
        JMenuItem descendingDocumentType = new JMenuItem("по убыванию");
        descendingDocumentType.setMnemonic('у');
        descendingDocumentType.addActionListener(descendingDocumentTypeActionListener);
        onDocumentTypeSubMenu.add(descendingDocumentType);
        listMenu.add(onDocumentTypeSubMenu);

        JMenu onDurationOfPrintSubMenu = new JMenu("по породолжительности печати");
        onDurationOfPrintSubMenu.setMnemonic('р');
        JMenuItem ascendingDurationOfPrint = new JMenuItem("по возрастанию");
        ascendingDurationOfPrint.setMnemonic('в');
        ascendingDurationOfPrint.addActionListener(ascendingDurationOfPrintActionListener);
        onDurationOfPrintSubMenu.add(ascendingDurationOfPrint);
        JMenuItem descendingDurationOfPrint = new JMenuItem("по убыванию");
        descendingDurationOfPrint.setMnemonic('у');
        descendingDurationOfPrint.addActionListener(descendingDurationOfPrintActionListener);
        onDurationOfPrintSubMenu.add(descendingDurationOfPrint);
        listMenu.add(onDurationOfPrintSubMenu);

        JMenu onPaperSizeSubMenu = new JMenu("по размеру бумаги");
        onPaperSizeSubMenu.setMnemonic('з');
        JMenuItem ascendingPaperSize = new JMenuItem("по возрастанию");
        ascendingPaperSize.setMnemonic('в');
        ascendingPaperSize.addActionListener(ascendingPaperSizeActionListener);
        onPaperSizeSubMenu.add(ascendingPaperSize);
        JMenuItem descendingPaperSize = new JMenuItem("по убыванию");
        descendingPaperSize.setMnemonic('у');
        descendingPaperSize.addActionListener(descendingPaperSizeActionListener);
        onPaperSizeSubMenu.add(descendingPaperSize);
        listMenu.add(onPaperSizeSubMenu);
    }

    private void addAllSubMenuToAddMenu(final JMenu addMenu) {
        final String SCANNED_PACKAGE = "ru.bww.document_print_manager.model.document_types";
        final String SCANNED_PATH = "ru/bww/document_print_manager/model/document_types";
        final String BAD_PACKAGE_ERROR = "Unable to get resources from path '%s'. Are you sure the package '%s' exists?";

        URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource(
                SCANNED_PATH);
        if (scannedUrl == null) {
            throw new IllegalArgumentException(String.format(BAD_PACKAGE_ERROR, SCANNED_PATH, SCANNED_PACKAGE));
        }
        File scannedDir = new File(scannedUrl.getFile());
        File[] files = scannedDir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().endsWith(".class")) {
                    int endCharIndex = file.getName().length() - ".class".length();
                    String menuItemTitle = file.getName().substring(0, endCharIndex);
                    JMenuItem menuItem = new JMenuItem(menuItemTitle);
                    menuItem.addActionListener(addSubMenuItemActionListener);
                    addMenu.add(menuItem);
                }
            }
        }
    }

    public void showMessageDialog(final String message, final String title, final int option) {
        JOptionPane.showMessageDialog(this.getContentPane(), message,
                title, option);
    }

    public void updateTable(final ListSelectionEvent listSelectionEvent) {
        if (tableMode) {
            //The table mode: show print queue
            setUpTableDocsToPrintQueue(tableDocs, NON_PRIM_STEP);
            ListSelectionEvent newListSelectionEvent = shiftListSelectionEvent(listSelectionEvent);
            if (newListSelectionEvent != null) {
                tableDocs.getSelectionModel().setSelectionInterval(
                        newListSelectionEvent.getFirstIndex(), newListSelectionEvent.getLastIndex());
                tableDocs.getSelectionModel().setValueIsAdjusting(newListSelectionEvent.getValueIsAdjusting());
            } else {
                setRemMenuState(false);
            }
        } else {
            setUpTableDocsToPrintedDocs(tableDocs);
        }
        ((DocumentTypeTableModel) tableDocs.getModel()).fireTableDataChanged();
    }

    private ListSelectionEvent shiftListSelectionEvent(final ListSelectionEvent listSelectionEvent) {
        if (listSelectionEvent == null) {
            return null;
        }
        int newFirstIndex = listSelectionEvent.getFirstIndex() - 1;
        int newLastIndex = listSelectionEvent.getLastIndex() - 1;
        boolean newValueIsAdjusting = listSelectionEvent.getValueIsAdjusting();
        Object newSource = listSelectionEvent.getSource();
        if (!(listSelectionEvent.getFirstIndex() > listSelectionEvent.getLastIndex())) {
            if (!(newFirstIndex < 0)) {
                return new ListSelectionEvent(newSource, newFirstIndex, newLastIndex, newValueIsAdjusting);
            } else if (!(newLastIndex < 0)) {
                newFirstIndex = 0;
            } else
                return null;
        } else {
            if (!(newLastIndex < 0)) {
                return new ListSelectionEvent(newSource, newFirstIndex, newLastIndex, newValueIsAdjusting);
            } else if (!(newFirstIndex < 0)) {
                newLastIndex = 0;
            } else
                return null;
        }
        return new ListSelectionEvent(newSource, newFirstIndex, newLastIndex, newValueIsAdjusting);
    }

    public void setRemMenuState(final boolean state) {
        menuBar.getMenu(2).setEnabled(state);
    }

    public void remSelected(final ListSelectionEvent listSelectionEvent) {
        if (listSelectionEvent != null) {
            int firstIndex = listSelectionEvent.getFirstIndex();
            int lastIndex = listSelectionEvent.getLastIndex();
            if (firstIndex > lastIndex) {
                int tempIndex = lastIndex;
                lastIndex = firstIndex;
                firstIndex = tempIndex;
            }
            for (int i = lastIndex; i >= firstIndex; i--) {
                documentTypeList.getPrintQueue().remove(i);
            }
        }
        updateTable(null);
    }

    public void switchListSelectModelListener(final boolean sWitch) {
        if (!sWitch) {
            clearSelection();
            for (int i = 0; i < menuBar.getMenuCount() - 2; i++) {
                menuBar.getMenu(i).setEnabled(false);
            }
        }
        documentTypeListSelectionListener.setEnableSelection(sWitch);
    }

    public void clearSelection() {
        tableDocs.clearSelection();
    }

    public void ascendingPrintNumberSort() {
        setUpTableDocsToPrintedDocs(tableDocs);
        Collections.sort(documentTypeList.getPrintedDocs(), new Comparator<DocumentType>() {
            @Override
            public int compare(DocumentType o1, DocumentType o2) {
                return o1.getNumberInQueue().compareTo(o2.getNumberInQueue());
            }
        });
        ((DocumentTypeTableModel) tableDocs.getModel()).fireTableDataChanged();
    }

    public void descendingPrintNumberSort() {
        setUpTableDocsToPrintedDocs(tableDocs);
        Collections.sort(documentTypeList.getPrintedDocs(), new Comparator<DocumentType>() {
            @Override
            public int compare(DocumentType o1, DocumentType o2) {
                 return o2.getNumberInQueue().compareTo(o1.getNumberInQueue());
            }
        });
        ((DocumentTypeTableModel) tableDocs.getModel()).fireTableDataChanged();
    }

    public void ascendingDocumentTypeSort() {
        setUpTableDocsToPrintedDocs(tableDocs);
        Collections.sort(documentTypeList.getPrintedDocs(), new Comparator<DocumentType>() {
            @Override
            public int compare(DocumentType o1, DocumentType o2) {
                return o1.getTypeName().compareTo(o2.getTypeName());
            }
        });
        ((DocumentTypeTableModel) tableDocs.getModel()).fireTableDataChanged();
    }

    public void descendingDocumentTypeSort() {
        setUpTableDocsToPrintedDocs(tableDocs);
        Collections.sort(documentTypeList.getPrintedDocs(), new Comparator<DocumentType>() {
            @Override
            public int compare(DocumentType o1, DocumentType o2) {
                return o2.getTypeName().compareTo(o1.getTypeName());
            }
        });
        ((DocumentTypeTableModel) tableDocs.getModel()).fireTableDataChanged();
    }

    public void ascendingDurationOfPrintSort() {
        setUpTableDocsToPrintedDocs(tableDocs);
        Collections.sort(documentTypeList.getPrintedDocs(), new Comparator<DocumentType>() {
            @Override
            public int compare(DocumentType o1, DocumentType o2) {
                return o1.getDurationOfPrint().compareTo(o2.getDurationOfPrint());
            }
        });
        ((DocumentTypeTableModel) tableDocs.getModel()).fireTableDataChanged();
    }

    public void descendingDurationOfPrintSort() {
        setUpTableDocsToPrintedDocs(tableDocs);
        Collections.sort(documentTypeList.getPrintedDocs(), new Comparator<DocumentType>() {
            @Override
            public int compare(DocumentType o1, DocumentType o2) {
                return o2.getDurationOfPrint().compareTo(o1.getDurationOfPrint());
            }
        });
        ((DocumentTypeTableModel) tableDocs.getModel()).fireTableDataChanged();
    }

    public void ascendingPaperSizeSort() {
        setUpTableDocsToPrintedDocs(tableDocs);
        Collections.sort(documentTypeList.getPrintedDocs(), new Comparator<DocumentType>() {
            @Override
            public int compare(DocumentType o1, DocumentType o2) {
                Integer i1 = o1.getPaperSize().getKey() * o1.getPaperSize().getValue();
                Integer i2 = o2.getPaperSize().getKey() * o2.getPaperSize().getValue();
                return i1.compareTo(i2);
            }
        });
        ((DocumentTypeTableModel) tableDocs.getModel()).fireTableDataChanged();
    }

    public void descendingPaperSizeSort() {
        setUpTableDocsToPrintedDocs(tableDocs);
        Collections.sort(documentTypeList.getPrintedDocs(), new Comparator<DocumentType>() {
            @Override
            public int compare(DocumentType o1, DocumentType o2) {
                Integer i1 = o1.getPaperSize().getKey() * o1.getPaperSize().getValue();
                Integer i2 = o2.getPaperSize().getKey() * o2.getPaperSize().getValue();
                return i2.compareTo(i1);
            }
        });
        ((DocumentTypeTableModel) tableDocs.getModel()).fireTableDataChanged();
    }

    public void setTableMode(final boolean tableMode) {
        this.tableMode = tableMode;
    }
}
