## CSC207 Mini-Project 2: Fraction Calculators

Author: Lydia Ye

Date: Sept. 17 2023

This repository contains programs (in Java) for building an interactive calculator and 
a quick calculator that uses fractions as their basic numeirc type and include registers.

## Project Overview

This project has the following classes:

- `BigFraction.java` includes the implementation of fraction data type.
- `BFCalculator.java` generates a simple calculator for arithmetic expressions including fractions and integers. Specifically, it provides two (non-static) methods for the calculator: 
    - `evaluate(String exp)` which evaluate an arithmetic expression, ignoring priority, 
    - `store(char register)` which store the last value computed in the named register.
- `Interactivecalculator.java` provides a main method that  repeatedly reads a line the user types, use a BFCalculator to compute the result, and print the result for the user.
- `QuickCalculator.java` provide a main method that takes the expressions from the command line and then print out the results.


## References

Readings and labs from csc207 course website:

- [Designing your own classes](https://rebelsky.cs.grinnell.edu/Courses/CSC207/2023Fa/labs/intro-classes.html)
- [Standard object methods](https://rebelsky.cs.grinnell.edu/Courses/CSC207/2023Fa/readings/standard-methods.html)

Online resources:

- [Examples of how to use split() method](https://stackoverflow.com/questions/7899525/how-to-split-a-string-by-space)
