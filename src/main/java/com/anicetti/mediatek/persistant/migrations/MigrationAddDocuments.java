package com.anicetti.mediatek.persistant.migrations;

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
            "   id SERIAL PRIMARY KEY," +
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
