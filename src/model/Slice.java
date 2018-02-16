package model;

public class Slice {

    public int row1;
    public int row2;
    public int column1;
    public int column2;

    public Slice(int row1, int row2, int column1, int column2) {
        this.row1 = row1;
        this.row2 = row2;
        this.column1 = column1;
        this.column2 = column2;
    }

    public int getRow1() {
        return row1;
    }

    public int getRow2() {
        return row2;
    }

    public int getColumn1() {
        return column1;
    }

    public int getColumn2() {
        return column2;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s", row1, column1, row2, column2);
    }


}
