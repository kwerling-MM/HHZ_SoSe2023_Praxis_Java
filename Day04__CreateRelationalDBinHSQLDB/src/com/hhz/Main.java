package com.hhz;

import com.kwerlingit.CreateAndFillDatabase;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    static final String DB_URL = "jdbc:hsqldb:file:testdb/testdb";
    // "jdbc:hsqldb:file:testdb/testdb"
    // "jdbc:hsqldb:mem:mymemdb:"
    // "jdbc:hsqldb:file:testdb"
    // "jdbc:hsqldb:file:///c/temp/testdb"
    static final String USER = "SA";
    static final String PASS = "";

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {

            CreateAndFillDatabase doDB = new CreateAndFillDatabase( conn );

            System.out.println("Successfully created the DB ( " + DB_URL + " ).");

        } catch ( Exception ex ) {
            System.err.println("ERROR: " + ex.getMessage());
        }
    }
}
