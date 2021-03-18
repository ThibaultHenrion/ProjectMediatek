package com.anicetti.mediatek.persistant.migrations;

public class MigrationAddUsers implements Migration {
    @Override
    public String getName() {
        return "Add Users";
    }

    @Override
    public String[] up() {
        return new String[] {
            "CREATE TABLE users (" +
            "   id SERIAL PRIMARY KEY," +
            "   login varchar(64) NOT NULL," +
            "   password_hash varchar(64) NOT NULL" +
            ");",
        };
    }

    @Override
    public String[] down() {
        return new String[] {
            "DROP TABLE users;",
        };
    }
}
