import java.util.Scanner;

public class Simplex {
  public static void main(String[] args) {

    // Getting input from user
    Scanner sc = new Scanner(System.in);
    int response = 0;
    while (response < 1 || response > 5) {
      System.out.println("Please input 1, 2, 3, 4, or, 5:");
      System.out.println(
          "1. Create your own LP     2. Run example LP (successful)      3. Run example LP (unbounded)     4. Run example LP (cycling no Bland's rule)     5. Run example LP (4 but using Bland's rule)");
      response = Integer.parseInt(sc.nextLine());
    }

    // processing user-input
    switch (response) {

      // 1. Create your own LP
      case 1:
        // getting m and n
        System.out.println("How many rows in the matrix?");
        int m = Integer.parseInt(sc.nextLine());
        System.out.println("How many columns in the matrix?");
        int n = Integer.parseInt(sc.nextLine());

        // getting elements of matrix
        double[][] A = new double[m][n];
        System.out.println("A: \n" + printMatrix(A));

        for (int i = 0; i < m; i++) {
          for (int j = 0; j < n; j++) {
            System.out.println("Enter a value for position m = " + (i + 1) + ", n = " + (j + 1));
            A[i][j] = Double.parseDouble(sc.nextLine());
            System.out.println("A: \n" + printMatrix(A));
          }
        }

        // getting objective values
        double[] c = new double[n];
        System.out.println("c: \n" + printVector(c) + "\n");
        for (int i = 0; i < n; i++) {
          System.out.println("Enter a value for c" + (i + 1));
          c[i] = Double.parseDouble(sc.nextLine());
          System.out.println("c: \n" + printVector(c) + "\n");
        }

        // getting b vector
        double[] b = new double[m];
        System.out.println("b: \n" + printVector(b) + "\n");
        for (int i = 0; i < m; i++) {
          System.out.println("Enter a value for b" + (i + 1));
          b[i] = Double.parseDouble(sc.nextLine());
          System.out.println("b: \n" + printVector(b) + "\n");
        }

        // getting x_0 (initial BFS)
        double[] x_0 = new double[n];
        System.out.println("x_0: \n" + printVector(x_0) + "\n");
        for (int i = 0; i < n; i++) {
          System.out.println("Enter a value for position " + (i + 1) + " of x_0");
          x_0[i] = Double.parseDouble(sc.nextLine());
          System.out.println("x_0: \n" + printVector(c) + "\n");
        }

        // getting bland's bool
        System.out.println("Would you like to use Bland's rule to prevent cycling?");
        System.out.println("Enter 'y' for yes or 'n' for no");
        String bland_response = sc.nextLine().toLowerCase();
        boolean blands = false;
        if (bland_response.equals("y"))
          blands = true;

        System.out.println("\nA: \n" + printMatrix(A));
        System.out.println("c: \n" + printVector(c));
        System.out.println("\nb: \n" + printVector(b));
        System.out.println("\nx_0: \n" + printVector(x_0));
        System.out.println();

        Result answer = solve(A, c, b, x_0, blands);
        if (answer.flag != 0)
          System.out.println("Flag: " + answer.flag);
        if (answer.flag == 1) {
          System.out.println("Optimal solution: " + printVector(answer.optimal_sol));
          System.out.println("Optimal value: " + answer.optimal_val + "\n");
        }
        System.exit(1);
        break;

      // 2. Run example LP (successful)
      case 2:
        // input
        double[][] A_1 = { { 1, -2, 1, 0, 0 }, { 2, 1, 0, 1, 0 }, { 0, 1, 0, 0, 1 } };
        double[] c_1 = { 2, 3, 0, 0, 0 };
        double[] b_1 = { 4, 18, 10 };
        double[] x_0_1 = { 0, 0, 4, 18, 10 };
        System.out.println("\nA: \n" + printMatrix(A_1));
        System.out.println("c: \n" + printVector(c_1));
        System.out.println("\nb: \n" + printVector(b_1));
        System.out.println("\nx_0: \n" + printVector(x_0_1));
        System.out.println();

        Result answer_1 = solve(A_1, c_1, b_1, x_0_1, false);
        System.out.println("Flag: " + answer_1.flag);
        System.out.println("Optimal solution: " + printVector(answer_1.optimal_sol));
        System.out.println("Optimal value: " + answer_1.optimal_val + "\n");
        System.exit(1);
        break;

      // 3. Run example LP (unbounded)
      case 3:
        // input
        double[][] A_4 = { { -1, 1, 1, 0 }, { 1, -2, 0, 1 } };
        double[] c_4 = { 5, 3, 0, 0 };
        double[] b_4 = { 4, 6 };
        double[] x_0_4 = { 0, 0, 4, 6 };
        System.out.println("\nA: \n" + printMatrix(A_4));
        System.out.println("c: \n" + printVector(c_4));
        System.out.println("\nb: \n" + printVector(b_4));
        System.out.println("\nx_0: \n" + printVector(x_0_4));
        System.out.println();

        Result answer_4 = solve(A_4, c_4, b_4, x_0_4, false);
        System.out.println("Flag: " + answer_4.flag + "\n");
        System.exit(1);
        break;

      // 4. Run example LP (cycling no bland's)
      case 4:
        // input
        double[][] A_2 = { { .5, -5.5, -2.5, 9, 1, 0, 0 }, { .5, -1.5, -.5, 1, 0, 1, 0 }, { 1, 0, 0, 0, 0, 0, 1 } };
        double[] c_2 = { 10, -57, -9, -24, 0, 0, 0 };
        double[] b_2 = { 0, 0, 1 };
        double[] x_0_2 = { 0, 0, 0, 0, 0, 0, 1 };

        System.out.println("\nA: \n" + printMatrix(A_2));
        System.out.println("c: \n" + printVector(c_2));
        System.out.println("\nb: \n" + printVector(b_2));
        System.out.println("\nx_0: \n" + printVector(x_0_2));
        System.out.println();

        Result answer_2 = solve(A_2, c_2, b_2, x_0_2, false);
        System.out.println("Flag: " + answer_2.flag + "\n");
        System.exit(1);
        break;

      // 5. Run example LP (4 but using bland's rule)
      case 5:
        // input
        double[][] A_3 = { { .5, -5.5, -2.5, 9, 1, 0, 0 }, { .5, -1.5, -.5, 1, 0, 1, 0 }, { 1, 0, 0, 0, 0, 0, 1 } };
        double[] c_3 = { 10, -57, -9, -24, 0, 0, 0 };
        double[] b_3 = { 0, 0, 1 };
        double[] x_0_3 = { 0, 0, 0, 0, 0, 0, 1 };

        System.out.println("\nA: \n" + printMatrix(A_3));
        System.out.println("c: \n" + printVector(c_3));
        System.out.println("\nb: \n" + printVector(b_3));
        System.out.println("\nx_0: \n" + printVector(x_0_3));
        System.out.println();

        Result answer_3 = solve(A_3, c_3, b_3, x_0_3, true);
        System.out.println("Flag: " + answer_3.flag);
        System.out.println("Optimal solution: " + printVector(answer_3.optimal_sol));
        System.out.println("Optimal value: " + answer_3.optimal_val + "\n");
        System.out.println("Optimal value: " + answer_3.optimal_val + "\n");
        System.exit(1);
        break;
    }
    sc.close();
  }

  // This function performs the simplex method given a matrix, vector of objective
  // values, b vector, and an initial BFS
  // There is also an additional boolean which enforces Bland's rule (to prevent
  // cycling) when set to true
  public static Result solve(double[][] A, double[] c, double[] b, double[] x_0, boolean bland) {

    // num rows and cols of A
    int m = A.length;
    int n = A[0].length;

    // constructing c_b
    double[] c_b = new double[m];
    int pos = 0;
    for (int i = 0; i < n; i++) {
      int sum = 0;
      boolean basic = true;
      double[] col = getCol(A, i);
      for (double element : col) {
        sum += element;
        if (element > 1 || element < 0) {
          basic = false;
        }
      }
      if (sum == 1 && basic) {
        c_b[pos] = c[i];
      }
    }

    // computing row 0 of simplex tableau
    System.out.println("Calculating reduced costs:");
    double[] reduced_costs = computeCosts(c_b, A, c);
    double z = multVectors(c_b, b);

    // constructing simplex tableau
    // row 0
    double[][] tableau = new double[m + 1][n + 1];
    for (int i = 0; i < reduced_costs.length; i++) {
      tableau[0][i] = reduced_costs[i];
    }
    tableau[0][n] = z;

    // constructing simplex tableau
    // rows 1 through m
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        tableau[i + 1][j] = A[i][j];
      }
      tableau[i + 1][n] = b[i];
    }
    System.out.println("\nTableau: \n" + printMatrix(tableau));

    int timer = 0;
    while (timer < 300) {

      // checking for optimality and finding most negative reduced cost
      boolean optimal = true;
      int most_negative_index = 0;
      for (int i = 0; i < tableau[0].length - 1; i++) {
        if (tableau[0][i] < 0) {
          optimal = false;
          if (tableau[0][i] < tableau[0][most_negative_index])
            most_negative_index = i;
        }
      }
      if (optimal) {

        double[] optimal_sol = new double[n];

        // constructing optimal solution
        for (int i = 0; i < n; i++) {
          double[] col = getCol(tableau, i);
          int sum = 0;
          boolean basic = true;
          for (double element : col) {
            sum += element;
            if (element > 1 || element < 0 || sum > 1) {
              basic = false;
            }
          }
          if (basic) {
            int b_index = -1;
            for (int j = 0; j < col.length; j++) {
              if (col[j] == 1)
                b_index = j;
            }
            optimal_sol[i] = tableau[b_index][tableau[0].length - 1];
          } else {
            optimal_sol[i] = 0;
          }
        }

        return new Result(1, optimal_sol, tableau[0][tableau[0].length - 1]);
      }

      // determining pivot by min ratio test
      int pivot_index = 1;
      double min_ratio = Integer.MAX_VALUE;
      for (int i = 1; i < tableau.length; i++) {
        if (tableau[i][most_negative_index] > 0) {
          if (tableau[i][tableau[0].length - 1] / tableau[pivot_index][most_negative_index] < min_ratio) {
            pivot_index = i;
            min_ratio = tableau[i][tableau[0].length - 1] / tableau[pivot_index][most_negative_index];
          }
        }
      }
      if (pivot_index == 1 && tableau[pivot_index][most_negative_index] <= 0) {
        // reduced cost is negative and no valid pivot element => problem is unbounded
        System.out.println("This problem is unbounded\n");
        return new Result(-3);
      }
      System.out.println("Pivot around : row = " + pivot_index + ", col = " + most_negative_index + "...\n");

      // Bland's rule
      // find first (smallest index) negative reduced cost rather than most negative
      if (bland) {
        for (int i = 0; i < tableau[0].length - 1; i++) {
          if (tableau[0][i] < 0) {
            most_negative_index = i;
            break;
          }
        }
      }

      // pivot
      tableau = pivot(tableau, pivot_index, most_negative_index);
      System.out.println("Tableau: \n" + printMatrix(tableau));

      timer++;
    }

    System.out.println(
        "Pivoted " + timer + " times and still no optimal result, consider setting bland to true to prevent cycling\n");
    return new Result(0);
  }

  // This function returns the matrix pivoted around the element at row=r, col=c
  public static double[][] pivot(double[][] tableau, int r, int c) {

    if (tableau[r][c] != 1) {
      double divisor = tableau[r][c];
      for (int i = 0; i < tableau[r].length; i++) {
        tableau[r][i] = tableau[r][i] / divisor;
      }
    }

    // "zero out" rows above pivot row
    for (int i = 0; i < r; i++) {
      if (tableau[i][c] != 0) {
        double factor = -tableau[i][c];
        for (int j = 0; j < tableau[0].length; j++) {
          tableau[i][j] = Math.round((tableau[i][j] + factor * tableau[r][j]) * 10000000.0) / 10000000.0;
        }
      }
    }

    // "zero out" rows below pivot row
    for (int i = r + 1; i < tableau.length; i++) {
      if (tableau[i][c] != 0) {
        double factor = -tableau[i][c];
        for (int j = 0; j < tableau[0].length; j++) {
          tableau[i][j] = Math.round((tableau[i][j] + factor * tableau[r][j]) * 10000000.0) / 10000000.0;
        }
      }
    }

    return tableau;

  }

  // This function returns the reduced costs of each variable
  // Note: this function could be further optimized by only calculating the
  // reduced costs of nonbasic variables (since the reduced costs of basic
  // variables are always zero)
  public static double[] computeCosts(double[] c_b, double[][] A, double[] objective_vals) {
    int n = A[0].length;
    double[] reduced_costs = new double[n];
    for (int i = 0; i < reduced_costs.length; i++) {
      System.out.println(printVector(c_b) + " * " + printVector(getCol(A, i)) + " - " + objective_vals[i] + " = "
          + (multVectors(c_b, getCol(A, i)) - objective_vals[i]));
      reduced_costs[i] = multVectors(c_b, getCol(A, i)) - objective_vals[i];
    }
    return reduced_costs;
  }

  // Helper function that returns the multiplication of two vectors a,b
  public static double multVectors(double[] a, double[] b) {
    double result = 0;
    for (int i = 0; i < a.length; i++) {
      result += a[i] * b[i];
    }
    return result;
  }

  // Helper function that returns column c of a matrix A
  public static double[] getCol(double[][] A, int c) {
    int n = A.length;
    double[] col = new double[n];
    for (int i = 0; i < n; i++) {
      col[i] = A[i][c];
    }
    return col;
  }

  // Helper function that prints a vector a
  public static String printVector(double[] a) {
    String s = "[ ";
    for (int i = 0; i < a.length; i++) {
      s += a[i];
      if (a.length - i > 1) {
        s += ", ";
      }
    }
    s += " ]";
    return s;
  }

  // Helper function that prints a matrix A
  public static String printMatrix(double[][] A) {
    String s = "";
    for (int i = 0; i < A.length; i++) {
      s += printVector(A[i]) + '\n';
    }
    return s;
  }

}
