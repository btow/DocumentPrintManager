package ru.bww.document_print_manager.controller;

import ru.bww.document_print_manager.Main;
import ru.bww.document_print_manager.model.DocumentType;
import ru.bww.document_print_manager.model.DocumentTypeList;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by btow on 11.01.2019.
 */
public class PrintManagerController extends Thread {

    private final DocumentTypeList documentTypeList;
    private boolean toByContinue = true;

    public PrintManagerController(final String threadName, final DocumentTypeList documentTypeList) {
        super(threadName);
        this.documentTypeList = documentTypeList;
    }

    public void setToByContinue(final boolean toByContinue) {
        this.toByContinue = toByContinue;
    }

    public DocumentTypeList getDocumentTypeList() {
        return this.documentTypeList;
    }

    public BigDecimal getMeanDurationOfPrint() {
        BigDecimal result = new BigDecimal(0);
        for (DocumentType doc :
                this.documentTypeList.getPrintedDocs()) {
            result = result.add(new BigDecimal(doc.getDurationOfPrint()));
        }
        if (this.documentTypeList.getPrintedDocs().size() == 0) {
            return new BigDecimal(0);
        } else {
            result = result.divide(
                    new BigDecimal(this.documentTypeList.getPrintedDocs().size()),
                    2, RoundingMode.UP);
            return result;
        }
    }

    @Override
    public void run() {
        List<DocumentType> documentTypes = new LinkedList<>();
        try {
            do {
                if (!isInterrupted()){
                    documentTypes.addAll(documentTypeList.getPrintQueue());
                    if (documentTypes.size() > 0) {
                        for (Iterator<DocumentType> iterator
                             = documentTypes.iterator(); iterator.hasNext(); ) {
                            DocumentType documentType = iterator.next();
                            Thread.sleep(documentType.getDurationOfPrint());
                            documentTypeList.getPrintedDocs().add(documentType);
                            iterator.remove();
                            documentTypeList.getPrintQueue().remove(0);
                            Main.INSTANSE.updateTable();
                        }
                    } else {
                        Thread.sleep(10);
                    }
                    documentTypes.clear();
                } else {
                    throw new InterruptedException("Thread is interrupted");
                }
            } while (toByContinue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public interface Interface {
        public void updateTable();
    }
}
