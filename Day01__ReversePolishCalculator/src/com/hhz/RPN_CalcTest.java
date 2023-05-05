package com.hhz;

import static org.junit.jupiter.api.Assertions.*;
import com.hhz.RPN_Calc;

class RPN_CalcTest {

    RPN_Calc rpnCalc = null;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        rpnCalc = new RPN_Calc();
        System.out.println("Setup");
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        rpnCalc = null;
        System.out.println("Teardown");
    }

    @org.junit.jupiter.api.Test
    void calc() {
        String[] rpnInstructions = { "32", "8", "14", "6", "+", "+", "+"};

        int actual = rpnCalc.calc(rpnInstructions);
        int expected = 60;
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void calcWithException_01() {
        String[] rpnInstructions = { "+", "+", "+"};

        Exception actual = assertThrows(Exception.class, () -> {
            rpnCalc.calc(rpnInstructions);
        });
        int expected = 60;
        assertTrue(actual != null );
    }
}