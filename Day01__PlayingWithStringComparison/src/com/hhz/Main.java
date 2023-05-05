package com.hhz;

public class Main {

    public static void main(String[] args) {

        System.out.println("Explain the following results:" );

        // Bad : Variables are to be declared on top of the block

        System.out.println( "" );  System.out.println( "" );
        String S1 = "Hello World";
        String S2 = S1;
        System.out.println( " S1 and S2 are equal            : "  +  ( S1 == S2 ) );
        System.out.println( " S1 and S2 have the same content: "  +  ( S1.compareTo(S2) == 0 ) );

        System.out.println( "" );  System.out.println( "" );
        String S3 = "Hello World";
        System.out.println( " S1 and S3 are equal            : "  +  ( S1 == S3 ) );
        System.out.println( " S2 and S3 are equal            : "  +  ( S2 == S3 ) );
        System.out.println( " S1 and S3 have the same content: "  +  ( S1.compareTo(S3) == 0 ) );
        System.out.println( " S2 and S3 have the same content: "  +  ( S2.compareTo(S3) == 0 ) );

        System.out.println( "" );  System.out.println( "" );
        String S4 = new String("Hello World");
        System.out.println( " S1 and S4 are equal            : "  +  ( S1 == S4 ) );
        System.out.println( " S1 and S4 have the same content: "  +  ( S1.compareTo(S4) == 0 ) );

        System.out.println( "" );  System.out.println( "" );
        String S5 = "Hello " + "World";
        System.out.println( " S1 and S5 are equal            : "  +  ( S1 == S5 ) );
        System.out.println( " S1 and S5 have the same content: "  +  ( S1.compareTo(S5) == 0 ) );

        System.out.println( "" );  System.out.println( "" );
        String S6 = "Hello ";
        S6 = S6 + "World";
        System.out.println( " S1 and S6 are equal            : "  +  ( S1 == S6 ) );
        System.out.println( " S1 and S6 have the same content: "  +  ( S1.compareTo(S6) == 0 ) );

        System.out.println( "" );  System.out.println( "" );
        System.out.println( " S1 identity hash code          : " + System.identityHashCode(S1));
        System.out.println( " S2 identity hash code          : " + System.identityHashCode(S2));
        System.out.println( " S3 identity hash code          : " + System.identityHashCode(S3));
        System.out.println( " S4 identity hash code          : " + System.identityHashCode(S4));
        System.out.println( " S5 identity hash code          : " + System.identityHashCode(S5));
        System.out.println( " S6 identity hash code          : " + System.identityHashCode(S6));

        System.out.println( "" );  System.out.println( "" );
        S5 = "Another text";
        System.out.println( " S5 identity hash code          : " + System.identityHashCode(S5));
    }
}
