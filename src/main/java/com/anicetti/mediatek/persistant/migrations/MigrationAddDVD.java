package com.anicetti.mediatek.persistant.migrations;

public class MigrationAddDVD implements Migration {
    @Override
    public String getName() {
        return "Add Dvds";
    }

    @Override
    public String[] up() {
        return new String[] {
                "CREATE TYPE genre_dvd AS ENUM ('HORROR','ROMANTIC','COMEDY','SCIENCE_FICTION');",
                "CREATE TABLE dvds (" +
                        "   id SERIAL NOT NULL," +
                        "   document_id SERIAL NOT NULL," +
                        "   genre genre_dvd NOT NULL," +
                        "   for_adults BOOLEAN NOT NULL," +
                        "   CONSTRAINT fk_document_id" +
                        "       FOREIGN KEY(document_id)" +
                        "           REFERENCES documents(id) ON DELETE CASCADE," +
                        "   PRIMARY KEY(id, document_id)" +
                        ");",
        };
    }

    @Override
    public String[] down() {
        return new String[]{
                "DROP TABLE dvds;",
                "DROP TYPE genre_dvd;"
        };
    }
}