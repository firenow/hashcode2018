package model;

public class Slice {

    int row1;
    int row2;
    int column1;
    int column2;

    public Slice(int row1, int row2, int column1, int column2) {
        this.row1 = row1;
        this.row2 = row2;
        this.column1 = column1;
        this.column2 = column2;
    }

    @Override
    public String toString() {
        return "Slice{" +
                "row1=" + row1 +
                ", row2=" + row2 +
                ", column1=" + column1 +
                ", column2=" + column2 +
                '}';
    }


}
