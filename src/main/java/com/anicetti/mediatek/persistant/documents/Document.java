package com.anicetti.mediatek.persistant.documents;


import com.anicetti.mediatek.persistant.ConnectionPool;
import mediatek2021.NewDocException;

import java.sql.*;

public abstract class Document implements mediatek2021.Document {

    private final String SQL_INSERT = "INSERT INTO documents (id, name, author, type) VALUES(?,?,?,?::type_doc)";
    private final String SQL_DELETE = "DELETE FROM documents WHERE id = ?";

    public enum TypeDoc{
        CD,
        DVD,
        LIVRE
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

            statement.setObject(1,0);
            statement.setObject(2,this.name);
            statement.setObject(3,this.author);
            statement.setObject(4,this.typeDoc.name());

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

    public void delete() throws NewDocException{
        try{
            Connection con = ConnectionPool.getConnection();
            PreparedStatement statement = con.prepareStatement(SQL_DELETE);

            statement.setObject(1, primaryKey);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0){
                throw new NewDocException("Echec de suppression du document, aucune ligne supprimée");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
