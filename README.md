# Project 1 part 2: Nondeterministic Finite Automata

* Author: Cody Palin and Dominick Edmonds
* Class: CS361 Section 1
* Semester: Spring 2019

## Overview

This program takes an input NFA and converts it into an equivilent DFA,
which it then simulates and verifys the provided language can be accepted by it.

## Compiling and Using

Similar to the previous project, the progam to run is the NFADriver class,
and it's parameter is the file (matching the outlined format on the project specs)
you would like to test

## Discussion

As projected, a lot of the time spent was on the getDFA funciton, since
it was the "bread and butter" function of this program. Most of the NFA.java
implementation was really easy otherwise.

We also had a few issues with the eClosure method in the NFA class as well.

## Testing

Using the provided tests we were able to ensure that test 0 and 1 functioned
correctly, however test 3 and 4 seem to fail, despite our best efforts to fix
any issues. While debugging test 3, we found that the program worked as 
intended while debugging, printing out the correct string, but while
running it throws an error, this greatly confused us and made debugging the 
issue impossible.
