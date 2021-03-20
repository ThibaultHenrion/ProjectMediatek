package com.anicetti.mediatek.persistant.migrations;

import com.anicetti.mediatek.persistant.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class MigrationRunner {
    public static Migration[] migrations = new Migration[] {
            new MigrationAddDocuments(),
            new MigrationAddCD(),
            new MigrationAddDVD(),
            new MigrationAddUsers()
    };

    public static void main(String[] args) {
        Connection con = ConnectionPool.getConnection();

        Scanner sc = new Scanner(System.in);
        System.out.println("MIGRATIONS UTIL\n1 - down\n2 - up\n3 - down and up\n");
        System.out.print(">");
        int choice = sc.nextInt();

        if(choice == 1 || choice == 3) {
            ArrayList<Migration> invlist = new ArrayList<>(Arrays.asList(migrations));
            Collections.reverse(invlist);
            for(Migration m:invlist) {
                System.out.println("Migration: "+m.getName());
                //DOWN
                System.out.println("Starting down...");
                for(String q:m.down()) {
                    try (Statement stmt = con.createStatement()) {
                        stmt.executeUpdate(q);
                        System.out.println("Down success!");
                    } catch (SQLException e) {
                        System.out.println("Down error vvvvvvvvvvvvvvvvvvvvvv");
                        e.printStackTrace();
                    }
                }
            }
        }

        if(choice == 2 || choice == 3) {
            for(Migration m:migrations) {
                System.out.println("Migration: "+m.getName());
                //UP
                System.out.println("Starting up...");
                for(String q:m.up()) {
                    try (Statement stmt = con.createStatement()) {
                        stmt.executeUpdate(q);
                        System.out.println("Up success!");
                    } catch (SQLException e) {
                        System.out.println("Up error vvvvvvvvvvvvvvvvvvvvvv");
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
