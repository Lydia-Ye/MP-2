import java.io.PrintWriter;

/**
 * A simple implementation of a quick calculator that takes expressions from 
 * command line arguments and prints the results.
 * 
 * @author Samuel A. Rebelsky
 * @author Lydia
 * @version Oct. 2023
 */

public class QuickCalculator {
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);

    // Check if there are input expressions from command line
    if (args.length < 1) {        //invalid number of command line arguments
      System.err.println("Please enter expressions in the command line");
      System.exit(1);
        } // if

    // Create a new BFCalculator
    BFCalculator calculator = new BFCalculator();

    // Calculator the expression in every command line argument
    for (int i = 0; i < args.length; i++) {
      String[] expArray = args[i].split("\\s+");
      if (expArray[0].equals("STORE")) {
        calculator.store(expArray[1].charAt(0));
      } else {
        BigFraction result = calculator.evaluate(args[i]);
        pen.println(args[i] + " = " + result);
        } // if
      } // for
        
    pen.flush();
    return;
  } // main
}
