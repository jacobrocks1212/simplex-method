public class Result {
  public int flag;
  public double[] optimal_sol;
  public double optimal_val;

  public Result(int flag, double[] optimal_sol, double optimal_val) {
    this.flag = flag;
    this.optimal_sol = optimal_sol;
    this.optimal_val = optimal_val;
  }

  public Result(int flag) {
    this.flag = flag;
  }

}
