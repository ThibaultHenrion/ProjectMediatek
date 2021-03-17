package com.anicetti.mediatek.persistant.migrations;

public class MigrationAddCD implements Migration {
    @Override
    public String getName() {
        return "Add Cds";
    }

    @Override
    public String[] up() {
        return new String[] {
            "CREATE TYPE genre_cd AS ENUM ('ROCK','ORCHESTRAL','PODCAST','POP');",
            "CREATE TABLE cds (" +
            "   id INTEGER NOT NULL," +
            "   document_id INTEGER NOT NULL," +
            "   genre genre_cd NOT NULL," +
            "   CONSTRAINT fk_document_id" +
            "       FOREIGN KEY(document_id)" +
            "           REFERENCES documents(id)," +
            "   PRIMARY KEY(id, document_id)" +
            ");",
        };
    }

    @Override
    public String[] down() {
        return new String[] {
            "DROP TABLE cds;",
            "DROP TYPE genre_cd;"
        };
    }
}
