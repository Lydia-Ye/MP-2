import java.math.BigInteger;

/**
 * A simple implementation of Fractions. Extended versionn of Fraction class we
 * worked in lab.
 * 
 * @author Samuel A. Rebelsky
 * @author Lydia, Maria
 * @version Sept. 2023
 */
public class BigFraction {
  // +------------------+---------------------------------------------
  // | Design Decisions |
  // +------------------+
  /*
   * (1) Denominators are always positive. Therefore, negative fractions are represented 
   * with a negative numerator. Similarly, if a fraction has a negative numerator, it 
   * is negative.
   * 
   * (2) Fractions will be stored in simplified form with the `simplify` method.
   */

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  /** The numerator of the fraction. Can be positive, zero or negative. */
  BigInteger num;

  /** The denominator of the fraction. Must be non-negative. */
  BigInteger denom;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new fraction with numerator num and denominator denom in the 
   * simplest form.
   * 
   */
  public BigFraction(BigInteger num, BigInteger denom) {
    // Reduce to the simplest form
    this.num = num.divide(num.gcd(denom));
    this.denom = denom.divide(num.gcd(denom));
  } // BigFraction(BigInteger, BigInteger)

  /**
   * Build a new fraction with numerator num and denominator denom in the 
   * simplest form.
   * 
   */
  public BigFraction(int num, int denom) {
    // Convert integer to BigInteger
    BigInteger bigNum = BigInteger.valueOf(num);
    BigInteger bigDenom = BigInteger.valueOf(denom);
    // Reduce to the simplest form
    this.num = bigNum.divide(bigNum.gcd(bigDenom));
    this.denom = bigDenom.divide(bigNum.gcd(bigDenom));
  } // BigFraction(int, int)

  /**
   * Build a new fraction with an integer 
   * 
   */
  public BigFraction(int num) {
    // Convert it to BigFraction with denominator of 1
    this.num = BigInteger.valueOf(num);
    this.denom = BigInteger.valueOf(1);
  } // BigFraction(int, int)

  /**
   * Build a new fraction with an integer 
   * 
   */
  public BigFraction(BigInteger num) {
    // Convert it to BigFraction with denominator of 1
    this.num = num;
    this.denom = BigInteger.valueOf(1);
  } // BigFraction(int, int)
  

  /**
   * Build a new fraction in the simplest form by parsing a string.
   *
   */
  public BigFraction(String str) {
    // Convert string to BigInteger
    String[] numStr = str.split("/");
    BigInteger strNum = new BigInteger(numStr[0]);
    BigInteger strDenom = new BigInteger(numStr[1]);
    // Reduce to the simplest form
    this.num = strNum.divide(strNum.gcd(strDenom));
    this.denom = strDenom.divide(strNum.gcd(strDenom));
  } // BigFraction(String str)

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Add the fraction `addMe` to this fraction.
   */
  public BigFraction add(BigFraction addMe) {
    BigInteger resultNum;
    BigInteger resultDenom;

    // The denominator of the result is the product of this object's denominator
    // and addMe's denominator
    resultDenom = this.denom.multiply(addMe.denom);
    // The numerator is more complicated
    resultNum = (this.num.multiply(addMe.denom)).add(addMe.num.multiply(this.denom));

    // Return the computed value
    return new BigFraction(resultNum, resultDenom);
  }// add(BigFraction addMe)

  /**
   * Substract the fraction `substractMe` to this fraction.
   */
  public BigFraction substract(BigFraction subtractMe) {
    BigInteger resultNum;
    BigInteger resultDenom;

    // The denominator of the result is the product of this object's denominator
    // and addMe's denominator
    resultDenom = this.denom.multiply(subtractMe.denom);
    // The numerator is more complicated
    resultNum = (this.num.multiply(subtractMe.denom)).subtract(subtractMe.num.multiply(this.denom));

    // Return the computed value
    return new BigFraction(resultNum,resultDenom);
  }// substract(BigFraction substractMe) 

  /**
   * Multiply this fraction with another fraction, multiplyMe.
   */
  public BigFraction multiply(BigFraction multiplyMe){
    BigInteger resultNum = this.num.multiply(multiplyMe.num);
    BigInteger resultDenom = this.denom.multiply(multiplyMe.denom);
    return new BigFraction(resultNum, resultDenom);
  } // multiply(BigFracton multiplyMe)

  /**
   * Divide this fraction with another fraction, divideMe.
   * divideMe can not be zero.
   */
  public BigFraction divide(BigFraction divideMe){
    BigInteger resultNum = this.num.multiply(divideMe.denom);
    BigInteger resultDenom = this.denom.multiply(divideMe.num);
    return new BigFraction(resultNum, resultDenom);
  } // divide(BigFracton divideMe)

  /**
   * Identify and return the fractional value of this fraction as a fraction.
   */
  public BigFraction fractional() {
    BigInteger remain = this.num.remainder(this.denom);
    return new BigFraction(remain, this.denom);
  } // fractional()

  /**
   * Express this fraction as a double.
   */
  public double doubleValue() {
    return this.num.doubleValue() / this.denom.doubleValue();
  } // doubleValue()


  /**
   * Get the denominator of this fraction.
   */
  public BigInteger denominator() {
    return this.denom;
  } // denominator()
  
  /**
   * Get the numerator of this fraction.
   */
  public BigInteger numerator() {
    return this.num;
  } // numerator()

  
  /**
   * Convert this fraction to a string for ease of printing.
   */
  public String toString() {
    // Special case: It's zero
    if (this.num.equals(BigInteger.ZERO)) {
      return "0";
    } // if it's zero

    // Lump together the string represention of the numerator,
    // a slash, and the string representation of the denominator
    // return this.num.toString().concat("/").concat(this.denom.toString());
    return this.num + "/" + this.denom;
  } // toString()
} // class BigFraction