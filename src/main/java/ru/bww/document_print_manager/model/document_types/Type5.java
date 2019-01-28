package ru.bww.document_print_manager.model.document_types;

import javafx.util.Pair;
import ru.bww.document_print_manager.model.DocumentType;

import java.math.BigInteger;

/**
 * Created by btow on 10.01.2019.
 */
public class Type5 implements DocumentType {

    private BigInteger numberInQueue;

    public Type5(final BigInteger numberInQueue) {
        this.numberInQueue = numberInQueue;
    }

    @Override
    public void setNumberInQueue(final BigInteger numberInQueue) {
        this.numberInQueue = numberInQueue;
    }

    @Override
    public BigInteger getNumberInQueue() {
        return this.numberInQueue;
    }

    private String typeName = "Type5";

    @Override
    public String getTypeName() {
        return this.typeName;
    }

    private Integer durationOfPrint = 12000;

    @Override
    public Integer getDurationOfPrint() {
        return this.durationOfPrint;
    }

    private Pair<Integer, Integer> paperSize = new Pair<>(420, 594);

    @Override
    public Pair<Integer, Integer> getPaperSize() {
        return this.paperSize;
    }

    @Override
    public String toString() {
        return String.format(
                "Number in queue -> %s; Type name -> %s; Duration of print -> %s; Paper size -> %s",
                this.numberInQueue, this.typeName, this.durationOfPrint.toString(), this.paperSize.toString());
    }
}
