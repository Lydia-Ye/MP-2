import java.io.PrintWriter;
import java.util.Scanner;

public class InteractiveCalculator {
    public static void main(String[] args) throws Exception {
        PrintWriter pen = new PrintWriter(System.out, true);
        Scanner input = new Scanner(System.in);
        pen.println(" Please enter the expression (enter QUIT to stop): ");
        
        BFCalculator calculator = new BFCalculator();
        String exp = input.nextLine(); 
        String[] expArray = exp.split("\\s+");
      
        while (!expArray[0].equals("QUIT")) {
          if (expArray[0].equals("STORE")) {
            calculator.store(expArray[1].charAt(0));
          } else {
            BigFraction result = calculator.evaluate(exp);
            pen.println(result);
          } // if

          exp = input.nextLine(); 
          expArray = exp.split("\\s+");
        } // while

        pen.flush();
        input.close();
        return;
    } // main

      // public static String[] getInput(Scanner in) {
      //   String exp = in.nextLine(); 
      //   String[] expArray = exp.split("\\s+");
      //   return expArray;
      // } // getInput

} // class InterativeCalculator