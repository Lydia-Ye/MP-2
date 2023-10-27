import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A simple implementation of an interactive calculator that repeatedly takes 
 * expressions from user's input and computes the results.
 * 
 * @author Lydia Ye
 * @version Oct. 2023
 */

public class InteractiveCalculator {
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);

    // Prompts user to enter expression
    Scanner input = new Scanner(System.in);
    pen.println(" Please enter the expression (enter QUIT to stop): ");
        
    // Create a BF calculator
    BFCalculator calculator = new BFCalculator();

    // Convert user's input into a string array
    String exp = input.nextLine(); 
    String[] expArray = exp.split("\\s+");
      
    // Evaluate the input
    while (!expArray[0].equals("QUIT")) {
      if (expArray[0].equals("STORE")) {
        // Store register
        calculator.store(expArray[1].charAt(0));
      } else {
        // Evaluates numeric expression
        BigFraction result = calculator.evaluate(exp);
        pen.println(result);
      } // if

      // Take new input until the user quit program
      exp = input.nextLine(); 
      expArray = exp.split("\\s+");
    } // while

    pen.flush();
    input.close();
    return;
  } // main
} // class InterativeCalculator