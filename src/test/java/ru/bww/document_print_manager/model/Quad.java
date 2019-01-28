package ru.bww.document_print_manager.model;

import javafx.util.Pair;

import java.math.BigInteger;

/**
 * Created by btow on 11.01.2019.
 */
public class Quad {
    public BigInteger numberInList;
    public String docType;
    public Integer durationOfPrint;
    public Pair<Integer, Integer> paperSize;

    public Quad(BigInteger numberInList, String docType, Integer durationOfPrint, Pair<Integer, Integer> paperSize) {
        this.numberInList = numberInList;
        this.docType = docType;
        this.durationOfPrint = durationOfPrint;
        this.paperSize = paperSize;
    }
}
