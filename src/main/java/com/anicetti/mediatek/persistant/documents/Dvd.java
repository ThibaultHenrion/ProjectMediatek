package com.anicetti.mediatek.persistant.documents;

import com.anicetti.mediatek.persistant.ConnectionPool;
import mediatek2021.NewDocException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dvd extends Document{


    private final String SQL_INSERT = "INSERT INTO dvds (document_id, for_adults, genre) VALUES (?, ?, ?::genre_dvd)";

    public enum GenreDvd{
        HORROR,
        ROMANTIC,
        COMEDY,
        SCIENCE_FICTION
    }

    private GenreDvd genreDvd;
    private Boolean pourAdulte;

    @Override
    public Object[] data() {
        Object[] obj = new Object[6];
        obj[0] = getPrimaryKey();
        obj[1] = getTypeDoc().name();
        obj[2] = getName();
        obj[3] = getAuthor();
        obj[4] = genreDvd.name();
        obj[5] = pourAdulte;
        return obj;
    }

    public Dvd(String name, String author, GenreDvd genreDvd, boolean pour_adultes){
        super(name, author, TypeDoc.DVD);
        this.genreDvd = genreDvd;
        this.pourAdulte = pour_adultes;
    }

    public Dvd(String name, String author, String genreDvd, boolean pour_adultes){
        super(name, author, TypeDoc.DVD);
        this.genreDvd = getGenreByName(genreDvd);
        this.pourAdulte = pour_adultes;
    }

    private GenreDvd getGenreByName(String name){
        for (GenreDvd genre: GenreDvd.values()){
            if (genre.name().equalsIgnoreCase(name)){
                return genre;
            }
        }
        return null;
    }

    public static List<Dvd> getAll(){
        ArrayList<Dvd> all = new ArrayList<>();
        Connection con = ConnectionPool.getConnection();

        String queryDocument = "SELECT * FROM dvds LEFT JOIN documents d on dvds.document_id = d.id";
        try{
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(queryDocument);
            while (resultSet.next()){
                all.add(new Dvd(
                        resultSet.getString("name"),
                        resultSet.getString("author"),
                        resultSet.getString("genre"),
                        resultSet.getBoolean("for_adults")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return all;
    }

    @Override
    public void insert() throws NewDocException {
        try{
            super.insert();
        }catch (NewDocException e){
            throw new NewDocException(e.getMessage());
        }

        try{
            Connection con = ConnectionPool.getConnection();
            PreparedStatement statement = con.prepareStatement(SQL_INSERT);

            statement.setObject(1, getPrimaryKey());
            statement.setObject(3, pourAdulte, Types.BOOLEAN);
            statement.setObject(2, genreDvd.name());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0){
                throw new NewDocException("Echec création dvd, aucune ligne créée");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new NewDocException("Echec creation dvd");
        }
    }
}
