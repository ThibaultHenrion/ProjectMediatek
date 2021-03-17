package com.anicetti.mediatek.persistant.documents;

import com.anicetti.mediatek.persistant.ConnectionPool;
import mediatek2021.NewDocException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class Cd extends Document{

    private final String SQL_INSERT = "INSERT INTO cds (id, document_id, genre) VALUES (?,?,?::genre_cd)";

    public enum GenreCd{
        ROCK,
        ORCHESTRAL,
        PODCAST,
        POP
    }

    private GenreCd genreCd;


    @Override
    public Object[] data() {
        Object[] obj = new Object[4];
        obj[0] = getName();
        obj[1] = getAuthor();
        obj[2] = getTypeDoc().name();
        obj[3] = genreCd.name();
        return obj;
    }

    public Cd(String name, String author, GenreCd genreCd) {
        super(name, author, TypeDoc.CD);
        this.genreCd = genreCd;
    }

    @Override
    public void insert()throws NewDocException {
        try {
            super.insert();
        }catch (NewDocException e){
            throw new NewDocException(e.getMessage());
        }


        try{
            Connection con = ConnectionPool.getConnection();
            PreparedStatement statement = con.prepareStatement(SQL_INSERT);

            statement.setObject(1,0);
            statement.setObject(2, getPrimaryKey());
            statement.setObject(3,genreCd.name());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0){
                throw new NewDocException("Echec creation cd, aucune ligne créée.");
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
