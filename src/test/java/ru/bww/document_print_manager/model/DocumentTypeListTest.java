package ru.bww.document_print_manager.model;

import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import ru.bww.document_print_manager.model.document_types.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by btow on 11.01.2019.
 */
public class DocumentTypeListTest {

    private List<Pair<List<Quad>, DocumentTypeList>> testCasesGetters;

    @Before
    public void setUp() throws Exception {
        BigInteger zero = new BigInteger("0");
        BigInteger one = new BigInteger("1");
        BigInteger two = new BigInteger("2");
        BigInteger three = new BigInteger("3");
        BigInteger four = new BigInteger("4");

        testCasesGetters = new ArrayList<>();
        //case 0
        List<Quad> expVals_0 = new ArrayList<>();
        DocumentTypeList origVals_0 = new DocumentTypeList();
        testCasesGetters.add(new Pair<>(expVals_0, origVals_0));
        //case 1
        List<Quad> expVals_1 = new ArrayList<>();
        expVals_1.add(new Quad(zero,"Type1", 3000, new Pair<>(105, 148)));
        DocumentTypeList origVals_1 = new DocumentTypeList();
        origVals_1.addDocInQueue(new Type1(zero));
        origVals_1.addDocInPrintedList(new Type1(zero));
        testCasesGetters.add(new Pair<>(expVals_1, origVals_1));
        //case 2
        List<Quad> expVals_2 = new ArrayList<>();
        expVals_2.add(new Quad(zero, "Type1", 3000, new Pair<>(105, 148)));
        expVals_2.add(new Quad(one, "Type2", 4500, new Pair<>(148, 210)));
        DocumentTypeList origVals_2 = new DocumentTypeList();
        origVals_2.addDocInQueue(new Type1(zero));
        origVals_2.addDocInQueue(new Type2(one));
        origVals_2.addDocInPrintedList(new Type1(zero));
        origVals_2.addDocInPrintedList(new Type2(one));
        testCasesGetters.add(new Pair<>(expVals_2, origVals_2));
        //case 3
        List<Quad> expVals_3 = new ArrayList<>();
        expVals_3.add(new Quad(zero, "Type1", 3000, new Pair<>(105, 148)));
        expVals_3.add(new Quad(one, "Type2", 4500, new Pair<>(148, 210)));
        expVals_3.add(new Quad(two, "Type3", 6000, new Pair<>(210, 297)));
        DocumentTypeList origVals_3 = new DocumentTypeList();
        origVals_3.addDocInQueue(new Type1(zero));
        origVals_3.addDocInQueue(new Type2(one));
        origVals_3.addDocInQueue(new Type3(two));
        origVals_3.addDocInPrintedList(new Type1(zero));
        origVals_3.addDocInPrintedList(new Type2(one));
        origVals_3.addDocInPrintedList(new Type3(two));
        testCasesGetters.add(new Pair<>(expVals_3, origVals_3));
        //case 4
        List<Quad> expVals_4 = new ArrayList<>();
        expVals_4.add(new Quad(zero, "Type1", 3000, new Pair<>(105, 148)));
        expVals_4.add(new Quad(one, "Type2", 4500, new Pair<>(148, 210)));
        expVals_4.add(new Quad(two, "Type3", 6000, new Pair<>(210, 297)));
        expVals_4.add(new Quad(three, "Type4", 7500, new Pair<>(297, 420)));
        DocumentTypeList origVals_4 = new DocumentTypeList();
        origVals_4.addDocInQueue(new Type1(zero));
        origVals_4.addDocInQueue(new Type2(one));
        origVals_4.addDocInQueue(new Type3(two));
        origVals_4.addDocInQueue(new Type4(three));
        origVals_4.addDocInPrintedList(new Type1(zero));
        origVals_4.addDocInPrintedList(new Type2(one));
        origVals_4.addDocInPrintedList(new Type3(two));
        origVals_4.addDocInPrintedList(new Type4(three));
        testCasesGetters.add(new Pair<>(expVals_4, origVals_4));
        //case 5
        List<Quad> expVals_5 = new ArrayList<>();
        expVals_5.add(new Quad(zero, "Type1", 3000, new Pair<>(105, 148)));
        expVals_5.add(new Quad(one, "Type2", 4500, new Pair<>(148, 210)));
        expVals_5.add(new Quad(two, "Type3", 6000, new Pair<>(210, 297)));
        expVals_5.add(new Quad(three, "Type4", 7500, new Pair<>(297, 420)));
        expVals_5.add(new Quad(four, "Type5", 12000, new Pair<>(420, 594)));
        DocumentTypeList origVals_5 = new DocumentTypeList();
        origVals_5.addDocInQueue(new Type1(zero));
        origVals_5.addDocInQueue(new Type2(one));
        origVals_5.addDocInQueue(new Type3(two));
        origVals_5.addDocInQueue(new Type4(three));
        origVals_5.addDocInQueue(new Type5(four));
        origVals_5.addDocInPrintedList(new Type1(zero));
        origVals_5.addDocInPrintedList(new Type2(one));
        origVals_5.addDocInPrintedList(new Type3(two));
        origVals_5.addDocInPrintedList(new Type4(three));
        origVals_5.addDocInPrintedList(new Type5(four));
        testCasesGetters.add(new Pair<>(expVals_5, origVals_5));
        //case 6
        List<Quad> expVals_6 = new ArrayList<>();
        expVals_6.add(new Quad(zero, "Type3", 6000, new Pair<>(210, 297)));
        expVals_6.add(new Quad(one, "Type3", 6000, new Pair<>(210, 297)));
        expVals_6.add(new Quad(two, "Type1", 3000, new Pair<>(105, 148)));
        expVals_6.add(new Quad(three, "Type1", 3000, new Pair<>(105, 148)));
        DocumentTypeList origVals_6 = new DocumentTypeList();
        origVals_6.addDocInQueue(new Type3(zero));
        origVals_6.addDocInQueue(new Type3(one));
        origVals_6.addDocInQueue(new Type1(two));
        origVals_6.addDocInQueue(new Type1(three));
        origVals_6.addDocInPrintedList(new Type3(zero));
        origVals_6.addDocInPrintedList(new Type3(one));
        origVals_6.addDocInPrintedList(new Type1(two));
        origVals_6.addDocInPrintedList(new Type1(three));
        testCasesGetters.add(new Pair<>(expVals_6, origVals_6));

    }

    private void assertEquals(List<Quad> exp, List<DocumentType> act) {
        if (exp != null & act == null) {
            throw new AssertionError("It isn't expected a null list");
        } else if (exp == null & act != null) {
            throw new AssertionError("It is expected a null list");
        } else if (exp.size() != act.size()) {
            throw new AssertionError("The same size of lists is expected");
        } else {
            for (int i = 0; i < exp.size(); i++) {
                if ((!(exp.get(i).numberInList).equals(act.get(i).getNumberInQueue())) ||
                        (!(exp.get(i).docType).equals(act.get(i).getTypeName())) ||
                        (!(exp.get(i).durationOfPrint).equals(act.get(i).getDurationOfPrint())) ||
                        (!(exp.get(i).paperSize).equals(act.get(i).getPaperSize()))) {
                    throw new AssertionError(
                            String.format("List items %i do not match", i));
                }
            }
        }
    }

    private void listPrint(List list) {
        BigInteger s0 = null;
        String s1 = null;
        Integer s2 = 0;
        Pair s3 = null;
        for (Object item :
                list) {
            switch (item.getClass().getSimpleName()) {
                case "Quad":
                    s0 = ((Quad) item).numberInList;
                    s1 = ((Quad) item).docType;
                    s2 = ((Quad) item).durationOfPrint;
                    s3 = ((Quad) item).paperSize;
                    break;
                case "Type1":
                case "Type2":
                case "Type3":
                case "Type4":
                case "Type5":
                    s0 = ((DocumentType) item).getNumberInQueue();
                    s1 = ((DocumentType) item).getTypeName();
                    s2 = ((DocumentType) item).getDurationOfPrint();
                    s3 = ((DocumentType) item).getPaperSize();
                    break;
            }
            assert s3 != null;
            System.out.println(
                    String.format(
                            "Number in list: %s; Document type: %s; Duration of print: %s; Paper size: %s",
                            s0, s1, s2.toString(), s3.toString())
            );
        }
    }

    @Test
    public void getPrintQueue() {
        System.out.println("Test method \"getPrintQueue()\"");
        for (Pair<List<Quad>, DocumentTypeList> testCase :
                testCasesGetters) {
            System.out.println("Expected print queue list:");
            listPrint(testCase.getKey());
            System.out.println("Actual print queue list:");
            listPrint(testCase.getValue().getPrintQueue());
            System.out.println();
            assertEquals(testCase.getKey(), testCase.getValue().getPrintQueue());
        }
    }

    @Test
    public void getPrintedDocs() {
        System.out.println("Test method \"getPrintedDocs()\"");
        for (Pair<List<Quad>, DocumentTypeList> testCase :
                testCasesGetters) {
            System.out.println("Expected printed docs list:");
            listPrint(testCase.getKey());
            System.out.println("Actual printed docs list:");
            listPrint(testCase.getValue().getPrintedDocs());
            System.out.println();
            assertEquals(testCase.getKey(), testCase.getValue().getPrintedDocs());
        }
    }
}