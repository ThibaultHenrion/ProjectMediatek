package com.anicetti.mediatek.persistant.documents;

import com.anicetti.mediatek.persistant.ConnectionPool;
import mediatek2021.NewDocException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Cd extends DocumentPersistant {

    private static final String SQL_INSERT = "INSERT INTO cds (document_id, genre) VALUES (?,?::genre_cd)";

    public enum GenreCd{
        ROCK,
        ORCHESTRAL,
        PODCAST,
        POP
    }

    private GenreCd genreCd;


    @Override
    public Object[] data() {
        Object[] obj = new Object[5];
        obj[0] = getPrimaryKey();
        obj[1] = getTypeDoc().name();
        obj[2] = getName();
        obj[3] = getAuthor();
        obj[4] = genreCd.name();
        return obj;
    }

    public Cd(String name, String author, String genreCd) {
        super(name, author, TypeDoc.CD);
        this.genreCd = getGenreByName(genreCd);
    }

    public Cd(String name, String author, GenreCd genreCd) {
        super(name, author, TypeDoc.CD);
        this.genreCd = genreCd;
    }

    private GenreCd getGenreByName(String name) {
        for(GenreCd genre:GenreCd.values()) {
            if(genre.name().equalsIgnoreCase(name)) {
                return genre;
            }
        }
        return null;
    }

    public static List<Cd> getAll() {
        ArrayList<Cd> all = new ArrayList<>();
        Connection con = ConnectionPool.getConnection();

        String queryDocuments = "SELECT * FROM cds LEFT JOIN documents d on cds.document_id = d.id";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(queryDocuments);
            while(rs.next()) {
                Cd c = new Cd(
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getString("genre")
                );
                c.setPrimaryKey(rs.getInt("document_id"));
                all.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return all;
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

            statement.setObject(1, getPrimaryKey());
            statement.setObject(2,genreCd.name());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0){
                throw new NewDocException("Echec creation cd, aucune ligne créée.");
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
            throw new NewDocException("Echec creation cd.");
        }
    }
}
