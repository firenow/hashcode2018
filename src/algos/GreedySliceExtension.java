package algos;

import model.Slice;
import model.Solution;

/**
 * 
 * @author ThierryBARTHEL
 *
 */
public class GreedySliceExtension {

  private Solution solution;

  public GreedySliceExtension(Solution sol) {
    this.solution = sol;
  }

  /**
   * 
   * @param grSol
   * @return
   */
  public void solve(Solution grSol) {
    //
    boolean[][] pizza = grSol.problem.getPizza();

    //
    for (int row = 0; row < pizza.length; row++) {
      for (int col = 0; col < pizza[row].length; col++) {
        Slice potentialSlice = new Slice(row, row, col, col);

        //
        while (true) {
          Slice geometricallyValidSlice = extendsPotentialSlice(potentialSlice);

          //
          if (geometricallyValidSlice == null) {
            break;
          } else {
            boolean sliceValid = isSliceIngredientsValid(geometricallyValidSlice);

            if (sliceValid) {
              this.solution.slices.add(geometricallyValidSlice);
            }
          }
        }
      }
    }

    //
    grSol.computeScore();
  }

  /**
   * 
   * @param potentialSlice
   * @return
   */
  private Slice extendsPotentialSlice(Slice potentialSlice) {
    //
    Slice returnedSlice = null;

    if (isSliceGeometricallyValid(potentialSlice)) {
      returnedSlice = potentialSlice;
      potentialSlice.column2++;
      potentialSlice.row2++;
    }

    return returnedSlice;
  }

  /**
   * 
   */
  private boolean isSliceGeometricallyValid(Slice potentialSlice) {
    // general dimension
    if (potentialSlice.getColumn2() > solution.problem.getColumnNumber()
        && potentialSlice.getRow2() > solution.problem.getRowNumber()) {
      return false;
    }
    // TODO : check each point (row, col) is not between (row1 - row2 && col1, col2)

    return true;
  }


  /**
   * 
   */
  private boolean isSliceIngredientsValid(Slice potentialSlice) {
    // TODO : count ingredients
    // TODO : verify each ingredients is between min / max
    return false;
  }
}
