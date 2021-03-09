package com.anicetti.mediatek.persistant.migrations;

import com.anicetti.mediatek.persistant.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MigrationAddDocuments implements Migration {
    @Override
    public String getName() {
        return "Add Documents";
    }

    @Override
    public String[] up() {
        return new String[] {
            "CREATE TYPE type_doc AS ENUM ('LIVRE','CD','DVD');",
            "CREATE TABLE documents (" +
            "   id INTEGER PRIMARY KEY," +
            "   name varchar(64) NOT NULL," +
            "   author varchar(64) NOT NULL," +
            "   type type_doc NOT NULL" +
            ");",
        };
    }

    @Override
    public String[] down() {
        return new String[] {
            "DROP TABLE documents;",
            "DROP TYPE type_doc;"
        };
    }
}
