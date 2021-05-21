package me.puraz.sqlite;

import me.puraz.sqlite.connector.SQLiteConnector;

public class Main {

    public static void main(String[] args) {
        System.out.println("Test.");
        SQLiteConnector.connectdb("db.db");
        SQLiteConnector.createTable("db.db");
        SQLiteConnector.readTable("db.db");
    }

}
