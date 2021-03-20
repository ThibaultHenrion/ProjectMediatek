package com.anicetti.mediatek.persistant.documents;


import com.anicetti.mediatek.persistant.ConnectionPool;
import mediatek2021.NewDocException;
import mediatek2021.SuppressException;

import java.sql.*;

public abstract class DocumentPersistant implements mediatek2021.Document {

    private static final String SQL_INSERT =
            "INSERT INTO documents (name, author, type) VALUES(?,?,?::type_doc) RETURNING id";
    private static final String SQL_DELETE =
            "DELETE FROM documents WHERE id = ?";

    public enum TypeDoc{
        CD,
        DVD,
        LIVRE
    }

    private int primaryKey;
    private String name;
    private String author;
    private TypeDoc typeDoc;

    public DocumentPersistant(String name, String author, TypeDoc typeDoc) {
        this.name = name;
        this.author = author;
        this.typeDoc = typeDoc;
    }

    public int getPrimaryKey(){
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
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
            PreparedStatement statement = con.prepareStatement(SQL_INSERT);

            statement.setObject(1,this.name);
            statement.setObject(2,this.author);
            statement.setObject(3,this.typeDoc.name());

            statement.execute();

            ResultSet rs = statement.getResultSet();

            if (rs.next()) {
                primaryKey = rs.getInt(1);
            } else {
                throw new NewDocException("Echec de création de document, aucun id retourné.");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void delete(int primaryKey) throws SuppressException{
        try{
            Connection con = ConnectionPool.getConnection();
            PreparedStatement statement = con.prepareStatement(SQL_DELETE);

            statement.setObject(1, primaryKey);

            int affectedRows = statement.executeUpdate();

            /*if (affectedRows == 0){
                throw new SuppressException("Echec de suppression du document, aucune ligne supprimée");
            }*/
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
