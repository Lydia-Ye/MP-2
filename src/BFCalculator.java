import java.math.BigInteger;
import java.util.Arrays;

/**
 * A simple implementation of a calculator for fractions and integers. 
 * 
 * @author Lydia Ye
 * @version Oct. 2023
 */

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
   * @throws InvalidFormatException
   */
  public BigFraction evaluate(String exp) throws InvalidFormatException {
    // store the elements in string into an array
    String[] expressions = exp.split("\\s+");

    if ((expressions.length % 2) == 0) {
      System.err.println("Wrong expression form: incorrect number of terms in the expression");   
      System.exit(0);
    } // if

    String[] values = new String[(expressions.length / 2) + 1];
    String[] operators = new String[expressions.length - values.length];


    for (int i = 0; i < expressions.length; i++) {
      if ((i % 2) == 0) {
        values[i/2] = expressions[i];
      } else {
        operators[i/2] = expressions[i];
      } // if   
    } // for

    // Store all values in an array of BigFraction
    BigFraction[] fractionVals= new BigFraction[values.length];
    //convert all values to their BigFraction form
    for (int i = 0; i < values.length; i++) {
      if ((values[i].length() == 1) && 
          Character.isLowerCase(values[i].charAt(0))){
        // if value at i is a register
        fractionVals[i] = registers[(int)values[i].charAt(0) - 'a'];
      } else if (isNumber(values[i]) && values[i].contains("/")){
        // if value at i is a fraction
        fractionVals[i] = new BigFraction (values[i]);
      } else if (isNumber(values[i])) {
        // if value at i is a integer
        BigInteger intValue = new BigInteger(values[i]);
        fractionVals[i] = new BigFraction(intValue);
      } else {
        // if value at i is not in a valid form
        System.err.println("Wrong expression form: there appears to be" +
                           " invalid value!"); 
        System.exit(0);
      }// if
    } // for

    // Checks if all operators are valid
    for (int i = 0; i < operators.length; i++) {
      if (!operators[i].equals("+") && !operators[i].equals("-") && 
          !operators[i].equals("*") && !operators[i].equals("/")) {
        // Prints error message
        System.err.println("Wrong expression form: there appears to be" +
                           " invalid operator!");   
        System.exit(0);
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
  } // store(char register)

  /**
   * Check if the input sting val represent a numeric value
   */
  public boolean isNumber(String val) {
    int numSlash = 0;
    // Check if val contains only numeric value with at most one slash
    for (int i = 0; i < val.length(); i++) {
      if (val.charAt(i) ==  '/') {
        numSlash++;
      } else if (numSlash > 1) {
        return false;
      } else if ((val.charAt(i) < '0') || (val.charAt(i) > '9')) {
        return false;
      } //if
    } // for
    return true;
  } // isNumber(String)

} // class BFCalculator 