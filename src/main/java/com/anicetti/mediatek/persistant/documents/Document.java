package com.anicetti.mediatek.persistant.documents;


import com.anicetti.mediatek.persistant.ConnectionPool;
import mediatek2021.NewDocException;

import java.sql.*;

public abstract class Document implements mediatek2021.Document {

    private final String SQL_INSERT = "INSERT INTO documents (name, author, type) VALUES(?,?,?)";

    public enum TypeDoc{
        LIVRE,
        CD,
        DVD
    }

    private long primaryKey;
    private String name;
    private String author;
    private TypeDoc typeDoc;

    public Document(String name, String author, TypeDoc typeDoc) {
        this.name = name;
        this.author = author;
        this.typeDoc = typeDoc;
    }

    public Long getPrimaryKey(){
        return primaryKey;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public TypeDoc getTypeDoc() {
        return typeDoc;
    }

    public void insert() throws NewDocException {
        try {
            Connection con = ConnectionPool.getConnection();
            PreparedStatement statement = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            statement.setObject(1,this.name);
            statement.setObject(2,this.author);
            statement.setObject(3,this.typeDoc.name());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0){
                throw new NewDocException("Echec de création de document, aucune ligne créée.");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()){
                this.primaryKey = generatedKeys.getLong(1);
            }else {
                throw new NewDocException("Echec de création de document, aucun id retourné.");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
