import java.math.BigInteger;
import java.io.PrintWriter;
import java.util.Arrays;

public class BFCalculator {

    // +------------------+---------------------------------------------
  // | Design Decisions |
  // +------------------+
  /*
   * BFCalculator generates objects that can calculate the expressions including
   * integers and fractions.
   */

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  /** The array of stored registers */
  BigFraction[] registers;

  /** The results that the expression evaluates to */
  BigFraction result;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new BFCalculator with no register and initilized result.
   * 
   */
  public BFCalculator() {
    this.registers = new BigFraction[26];
    Arrays.fill(this.registers, new BigFraction(0 , 1));
    this.result = new BigFraction(0 , 1);
  } // BFCalculator()

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Evaluate an expression given in the string, exp, ignoring priority
   */
  public BigFraction evaluate(String exp) {
    PrintWriter pen = new PrintWriter(System.out, true);

    // store the elements in string into an array
    String[] expressions = exp.split("\\s+");

    /*
    for (int i = 0; i < expressions.length; i++){
      pen.println(expressions[i]);
    }
    */

    String[] values = new String[(expressions.length / 2) + 1];
    String[] operators = new String[expressions.length - values.length];

/* 
    // store all values in the expression in another array
    for (int i = 0; i  < expressions.length; i += 2) {
      for (int j = 0; j < values.length; j++) {
        values[j] = expressions[i];
      } // for
    } // for

    // store all operators in the expression in another array
    for (int i = 1; i  < expressions.length; i += 2) { 
      for (int j = 0; j < operators.length; j++) {
        operators[j] = expressions[i];
      } //for
    } // for
*/

// for
    for (int i = 0; i < expressions.length; i++) {
      if ((i % 2) == 0)
        values[i/2] = expressions[i];
      else 
        operators[i/2] = expressions[i];
    } // for


    // Store all values in an array of BigFraction
    BigFraction[] fractionVals= new BigFraction[values.length];
    //convert all values to their BigFraction form
    for (int i = 0; i < values.length; i++) {
      if (Character.isLowerCase(values[i].charAt(0))){
        fractionVals[i] = registers[(int)values[i].charAt(0) - 'a'];
      } else if (values[i].contains("/")){
        fractionVals[i] = new BigFraction (values[i]);
      } else {
        BigInteger intValue = new BigInteger(values[i]);
        fractionVals[i] = new BigFraction(intValue);
      } // if
    } // for

    // Initialize the result with the first value in the expression
    this.result = fractionVals[0];
    // Calculate the result based on different operators
    for (int i = 0; i < operators.length; i++) {
      switch (operators[i]){
        case "+":
          this.result = this.result.add(fractionVals[i + 1]);
          break;
        case "-":
          this.result = this.result.substract(fractionVals[i + 1]);
          break;
        case "*":
          this.result = this.result.multiply(fractionVals[i + 1]);
          break;
        case "/":
          this.result = this.result.divide(fractionVals[i + 1]);
          break;
        default:
          System.err.println("Invalid operator");
      } // switch
    } // for

  return this.result;
  } // evaluate(String exp)


  /**
   * Store the last value computed in the named register
   */
  public void store(char register) {
    int regIndex = (int)register - 'a';
    this.registers[regIndex] = this.result;
  } //store(char register)
} // class BFCalculator