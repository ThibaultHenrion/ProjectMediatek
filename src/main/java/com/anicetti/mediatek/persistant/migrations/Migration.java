package com.anicetti.mediatek.persistant.migrations;

import com.anicetti.mediatek.persistant.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public interface Migration {

    public static Migration[] migrations = new Migration[] {
            new MigrationAddDocuments()
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
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Down success!");
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
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Up success!");
            }
        }
    }

    public abstract String getName();
    public abstract String[] up();
    public abstract String[] down();
}
