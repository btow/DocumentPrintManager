package ru.bww.document_print_manager.model;

import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import ru.bww.document_print_manager.model.document_types.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by btow on 11.01.2019.
 */
public class DocumentTypeTest {

    private List<Pair<Quad, DocumentType>> testCases = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        BigInteger zero = new BigInteger("0");
        BigInteger one = new BigInteger("1");
        BigInteger two = new BigInteger("2");
        BigInteger three = new BigInteger("3");
        BigInteger four = new BigInteger("4");
        //case 1
        Quad expValues_1 = new Quad(zero, "Type1", 3000, new Pair<>(105, 148));
        testCases.add(new Pair<Quad, DocumentType>(expValues_1, new Type1(zero)));
        //case 2
        Quad expValues_2 = new Quad(zero, "Type2", 4500, new Pair<>(148, 210));
        testCases.add(new Pair<Quad, DocumentType>(expValues_2, new Type2(zero)));
        //case 3
        Quad expValues_3 = new Quad(zero, "Type3", 6000, new Pair<>(210, 297));
        testCases.add(new Pair<Quad, DocumentType>(expValues_3, new Type3(zero)));
        //case 4
        Quad expValues_4 = new Quad(zero, "Type4", 7500, new Pair<>(297, 420));
        testCases.add(new Pair<Quad, DocumentType>(expValues_4, new Type4(zero)));
        //case 5
        Quad expValues_5 = new Quad(zero, "Type5", 12000, new Pair<>(420, 594));
        testCases.add(new Pair<Quad, DocumentType>(expValues_5, new Type5(zero)));

    }

    @Test
    public void getNumberInQueue() {
        System.out.println("Test method \"getNumberInQueue()\"");
        for (Pair<Quad, DocumentType> pair :
                testCases) {
            System.out.println("Expected Number In Queue: " + pair.getKey().numberInList);
            System.out.println("Actual Number In Queue:   " + pair.getValue().getNumberInQueue());
            assertEquals(pair.getKey().numberInList, pair.getValue().getNumberInQueue());
        }
        System.out.println();
    }

    @Test
    public void getTypeName() {
        System.out.println("Test method \"getTypeName()\"");
        for (Pair<Quad, DocumentType> pair :
                testCases) {
            System.out.println("Expected Type Name: " + pair.getKey().docType);
            System.out.println("Actual Type Name:   " + pair.getValue().getTypeName());
            assertEquals(pair.getKey().docType, pair.getValue().getTypeName());
        }
        System.out.println();
    }

    @Test
    public void getDurationOfPrint() {
        System.out.println("Test method \"getDurationOfPrint()\"");
        for (Pair<Quad, DocumentType> pair :
                testCases) {
            System.out.println("Expected duration of print: " + pair.getKey().durationOfPrint);
            System.out.println("Actual duration of print:   " + pair.getValue().getDurationOfPrint());
            assertEquals(pair.getKey().durationOfPrint, pair.getValue().getDurationOfPrint());
        }
        System.out.println();
    }

    @Test
    public void getPaperSize() {
        System.out.println("Test method \"getPaperSize()\"");
        for (Pair<Quad, DocumentType> pair :
                testCases) {
            System.out.println("Expected paper size " + pair.getKey().paperSize);
            System.out.println("Actual paper size:  " + pair.getValue().getPaperSize());
            assertEquals(pair.getKey().paperSize, pair.getValue().getPaperSize());
        }
        System.out.println();
    }
}