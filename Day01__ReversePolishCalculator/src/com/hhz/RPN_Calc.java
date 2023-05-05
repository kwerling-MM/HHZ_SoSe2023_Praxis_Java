package com.hhz;

import java.util.Stack;

public class RPN_Calc {

    Stack<Integer> stack = new Stack<Integer>();

    public RPN_Calc() {}

    public int calc( String[] rpnInstructions ) {

        while ( ! stack.empty() ) { stack.pop(); }

        for( int i = 0; i < rpnInstructions.length; i++ ) {
            if( isInteger( rpnInstructions[i] ) ) {
                stack.push(new Integer(Integer.parseInt(rpnInstructions[i])));
            } else {
                switch( rpnInstructions[i] ) {
                    case "+":
                        stack.push(stack.pop() + stack.pop());
                        break;
                }
            }
        }

        return (Integer) stack.pop();
    }

    boolean isInteger( String str )  {
        boolean retVal = false;
        try {
            Integer.parseInt( str );
            retVal = true;
        } catch( Exception ex ) {
            retVal = false;
        }

        return retVal;
    }
}
