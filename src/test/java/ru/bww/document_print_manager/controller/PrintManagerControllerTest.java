package ru.bww.document_print_manager.controller;

import com.sun.javafx.beans.annotations.NonNull;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import ru.bww.document_print_manager.model.*;
import ru.bww.document_print_manager.model.document_types.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by btow on 11.01.2019.
 */
public class PrintManagerControllerTest {

    private List<Pair<DocumentTypeList, PrintManagerController>> testCases;

    /**
     * Before you run the test, you must comment out a line "Main.INSTANSE.updateTable();
     * in the "run()" method of the "PrintManagerController" class.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        System.out.println(
                "Before you run the test, you must comment out a line \"Main.INSTANSE.updateTable();\"\n" +
                        "in the \"run()\" method of the \"PrintManagerController\" class.\n"
        );

        BigInteger zero = new BigInteger("0");
        BigInteger one = new BigInteger("1");
        BigInteger two = new BigInteger("2");
        BigInteger three = new BigInteger("3");
        BigInteger four = new BigInteger("4");
        testCases = new ArrayList<>();
        //case 0
        DocumentTypeList origDocumentTypeList_0 = new DocumentTypeList();
        origDocumentTypeList_0.addDocInQueue(new Type1(zero));
        DocumentTypeList expDocumentTypeList_0 = new DocumentTypeList();
        expDocumentTypeList_0.addDocInPrintedList(new Type1(zero));
        testCases.add(
                new Pair<>(expDocumentTypeList_0,
                        new PrintManagerController("test_print", origDocumentTypeList_0)));
        //case 1
        DocumentTypeList origDocumentTypeList_1 = new DocumentTypeList();
        origDocumentTypeList_1.addDocInQueue(new Type1(zero));
        origDocumentTypeList_1.addDocInQueue(new Type2(one));
        DocumentTypeList expDocumentTypeList_1 = new DocumentTypeList();
        expDocumentTypeList_1.addDocInPrintedList(new Type1(zero));
        expDocumentTypeList_1.addDocInPrintedList(new Type2(one));
        testCases.add(
                new Pair<>(expDocumentTypeList_1,
                        new PrintManagerController("test_print", origDocumentTypeList_1)));
        //case 2
        DocumentTypeList origDocumentTypeList_2 = new DocumentTypeList();
        origDocumentTypeList_2.addDocInQueue(new Type1(zero));
        origDocumentTypeList_2.addDocInQueue(new Type2(one));
        origDocumentTypeList_2.addDocInQueue(new Type3(two));
        DocumentTypeList expDocumentTypeList_2 = new DocumentTypeList();
        expDocumentTypeList_2.addDocInPrintedList(new Type1(zero));
        expDocumentTypeList_2.addDocInPrintedList(new Type2(one));
        expDocumentTypeList_2.addDocInPrintedList(new Type3(two));
        testCases.add(
                new Pair<>(expDocumentTypeList_2,
                        new PrintManagerController("test_print", origDocumentTypeList_2)));
        //case 3
        DocumentTypeList origDocumentTypeList_3 = new DocumentTypeList();
        origDocumentTypeList_3.addDocInQueue(new Type1(zero));
        origDocumentTypeList_3.addDocInQueue(new Type2(one));
        origDocumentTypeList_3.addDocInQueue(new Type3(two));
        origDocumentTypeList_3.addDocInQueue(new Type4(three));
        DocumentTypeList expDocumentTypeList_3 = new DocumentTypeList();
        expDocumentTypeList_3.addDocInPrintedList(new Type1(zero));
        expDocumentTypeList_3.addDocInPrintedList(new Type2(one));
        expDocumentTypeList_3.addDocInPrintedList(new Type3(two));
        expDocumentTypeList_3.addDocInPrintedList(new Type4(three));
        testCases.add(
                new Pair<>(expDocumentTypeList_3,
                        new PrintManagerController("test_print", origDocumentTypeList_3)));
        //case 4
        DocumentTypeList origDocumentTypeList_4 = new DocumentTypeList();
        origDocumentTypeList_4.addDocInQueue(new Type1(zero));
        origDocumentTypeList_4.addDocInQueue(new Type2(one));
        origDocumentTypeList_4.addDocInQueue(new Type3(two));
        origDocumentTypeList_4.addDocInQueue(new Type4(three));
        origDocumentTypeList_4.addDocInQueue(new Type5(four));
        DocumentTypeList expDocumentTypeList_4 = new DocumentTypeList();
        expDocumentTypeList_4.addDocInPrintedList(new Type1(zero));
        expDocumentTypeList_4.addDocInPrintedList(new Type2(one));
        expDocumentTypeList_4.addDocInPrintedList(new Type3(two));
        expDocumentTypeList_4.addDocInPrintedList(new Type4(three));
        expDocumentTypeList_4.addDocInPrintedList(new Type5(four));
        testCases.add(
                new Pair<>(expDocumentTypeList_4,
                        new PrintManagerController("test_print", origDocumentTypeList_4)));
    }

    private void assertEquals(List<DocumentType> exp, List<DocumentType> act){
        System.out.println("Expected document type list:");
        printDocTypeList(exp);
        System.out.println("Actual document type list:");
        printDocTypeList(act);

        if (exp == null & act != null) {
            throw new AssertionError("It is expected a null list");
        } else if (exp != null & act == null) {
            throw new AssertionError("It isn't expected a null list");
        } else if (exp.size() != act.size()) {
            throw new AssertionError("The same size of lists is expected");
        } else {
            for (int i = 0; i < exp.size(); i++) {
                DocumentType expDocType = exp.get(i);
                DocumentType actDocType = act.get(i);
                if ((!expDocType.getNumberInQueue().equals(actDocType.getNumberInQueue())) ||
                        (!expDocType.getTypeName().equals(actDocType.getTypeName())) ||
                        (expDocType.getDurationOfPrint().intValue() != actDocType.getDurationOfPrint().intValue()) ||
                        (expDocType.getPaperSize().getKey().intValue() != actDocType.getPaperSize().getKey().intValue()) ||
                        (expDocType.getPaperSize().getValue().intValue() != actDocType.getPaperSize().getValue().intValue())) {
                    throw new AssertionError(
                            String.format("List items %i do not match", i));
                }
            }
        }
    }

    private void printDocTypeList(@NonNull List<DocumentType> documentTypeList) {
        for (DocumentType doc :
                documentTypeList) {
            System.out.println(doc.toString());
        }
    }

    private void assertEquals(DocumentTypeList exp, DocumentTypeList act) {
        System.out.println("Test print queue");
        assertEquals(exp.getPrintQueue(), act.getPrintQueue());
        System.out.println("Test printed docs");
        assertEquals(exp.getPrintedDocs(), act.getPrintedDocs());
        System.out.println();
    }

    private void assertEquals(@NonNull BigDecimal exp, @NonNull BigDecimal act) {
        System.out.println(String.format("The expected mean duration of print -> %s;\n" +
                "The actual  mean duration of print -> %s", exp.toString(), act.toString()));
        if (!exp.equals(act)){
            throw new AssertionError(exp.toString() + " not equals " + act.toString());
        }
        System.out.println();
    }

    @Test
    public void run() {
        System.out.println("Test method \"run()\"");
        for (Pair<DocumentTypeList, PrintManagerController> testCase :
                testCases) {
            int timeOut = 0;
            for (DocumentType doc :
                    testCase.getKey().getPrintedDocs()) {
                timeOut += doc.getDurationOfPrint();
            }
            testCase.getValue().start();
            try {
                Thread.sleep(2 * timeOut);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            testCase.getValue().setToByContinue(false);
            assertEquals(testCase.getKey(), testCase.getValue().getDocumentTypeList());
        }
        System.out.println();
    }

    @Test
    public void getMeanDurationOfPrint() {
        System.out.println("Test method \"getMeanDurationOfPrint()\"");
        for (Pair<DocumentTypeList, PrintManagerController> testCase :
                testCases) {
            BigDecimal expMeanPrintTime = new BigDecimal(0);
            for (DocumentType doc :
                    testCase.getKey().getPrintedDocs()) {
                expMeanPrintTime = expMeanPrintTime.add(new BigDecimal(doc.getDurationOfPrint()));
            }
            expMeanPrintTime = expMeanPrintTime.divide(
                    new BigDecimal(testCase.getKey().getPrintedDocs().size()),
                    2, RoundingMode.UP);
            int timeOut = 0;
            for (DocumentType doc :
                    testCase.getKey().getPrintedDocs()) {
                timeOut += doc.getDurationOfPrint();
            }
            testCase.getValue().start();
            try {
                Thread.sleep(2 * timeOut);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            testCase.getValue().setToByContinue(false);
            assertEquals(expMeanPrintTime, testCase.getValue().getMeanDurationOfPrint());
        }
        System.out.println();
    }
}