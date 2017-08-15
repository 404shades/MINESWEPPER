package com.example.rohanmalik.mineswepper;

import android.content.Context;
import android.widget.Button;

/**
 * Created by Rohan Malik on 16-06-2017.
 */

public class Blocks extends Button {
    int number;
    Boolean mine=false;
    int rowNumber=0;
    int columnNumber=0;
    Boolean visited=false;
    Boolean flagged=false;


    public Blocks(Context context) {
        super(context);
    }

    public Boolean getMine() {
        return mine;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }
}
