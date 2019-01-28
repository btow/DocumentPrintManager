package ru.bww.document_print_manager.model;

import com.sun.javafx.beans.annotations.NonNull;
import ru.bww.document_print_manager.model.DocumentType;
import ru.bww.document_print_manager.model.document_types.*;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by btow on 11.01.2019.
 */
public class DocumentTypeList {

    private List<DocumentType> printQueue;
    private List<DocumentType> printedDocs;

    public DocumentTypeList() {
        this.printQueue = new LinkedList<>();
        this.printedDocs = new LinkedList<>();
    }

    public List<DocumentType> getPrintQueue() {
        return printQueue;
    }

    public List<DocumentType> addDocInQueue(@NonNull final DocumentType doc){
        printQueue.add(doc);
        return printQueue;
    }

    public List<DocumentType> getPrintedDocs() {
        return printedDocs;
    }

    public List<DocumentType> addDocInPrintedList(@NonNull final DocumentType doc) {
        printedDocs.add(doc);
        return printedDocs;
    }
}
