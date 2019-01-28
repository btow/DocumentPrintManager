package ru.bww.document_print_manager.model;

import javafx.util.Pair;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by btow on 10.01.2019.
 */
public interface DocumentType {

    /**
     * @param numberInQueue - the number of BigInteger in print queue.
     */
    void setNumberInQueue(final BigInteger numberInQueue);

    /**
     * @return - the number of BigInteger in print queue.
     */
    BigInteger getNumberInQueue();

    /**
     * @return the type name of document.
     */
    String getTypeName();

    /**
     * @return the duration of the print in milliseconds.
     */
    Integer getDurationOfPrint();

    /**
     * @return the width and height of the paper in millimeters.
     */
    Pair<Integer, Integer> getPaperSize();

    /**
     * @return representing an instance of a class as a string
     */
    String toString();

}
