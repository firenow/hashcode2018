package algos;

import model.Problem;
import model.Slice;
import model.Solution;

import java.util.ArrayList;
import java.util.List;

public class AlgoNaifCommeThierry {


    Solution solution;

    Problem problem;

    public AlgoNaifCommeThierry(Solution solution) {
        this.solution = solution;


        this.problem = solution.problem;

        run();


    }


    void run() {
        double baseSize = Math.sqrt(problem.getMaxtotalIngredientsPerSlice());
        int sizeRow = (int)Math.ceil(baseSize) - 1;
        int sizeCol = (int)Math.floor(baseSize) - 1;

        List<Slice> sliceList = new ArrayList<>();


        for (int i = 0; i < this.solution.problem.getRowNumber(); i += sizeRow) {
            for (int j = 0; j < this.solution.problem.getColumnNumber(); j += sizeCol) {

                Slice slice = new Slice(i, i + sizeRow, j, j + sizeCol);
                System.out.println(slice);
                if (isValidSlice(slice)) sliceList.add(slice);
            }
        }
        this.solution.slices = sliceList;
    }

    boolean isValidSlice(Slice slice) {

        int tomates = 0;
        int mushrooms = 0;

        if (
                slice.getColumn2() > problem.getPizza()[0].length
                        ||
                        slice.getRow2() > problem.getPizza().length
                ) {
            return false;
        }


        if (problem.getMaxtotalIngredientsPerSlice() < (slice.getRow2() - slice.getRow1()) * (slice.getColumn2() - slice.getColumn1()))
            return false;

        boolean[][] pizza = problem.getPizza();

        for (int i = slice.getRow1(); i < slice.getRow2(); i++) {
            for (int j = slice.getColumn1(); j < slice.getColumn2(); j++) {
              //  System.out.println( i +" / "+ j);
                if (pizza[i][j]) tomates++;
                else mushrooms++;
            }
        }

        return
                tomates > problem.getMinEachIngredientPerSlice()
                        &&
                        mushrooms > problem.getMinEachIngredientPerSlice();

    }

}
