package com.anicetti.mediatek.persistant.auth;

import com.anicetti.mediatek.persistant.ConnectionPool;
import mediatek2021.Utilisateur;

import java.sql.*;

public class User implements Utilisateur {

    private final String login;
    private final String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static User getOne(String login, String password) {
        Connection con = ConnectionPool.getConnection();

        String queryUser = "SELECT * FROM users WHERE login = ? AND password_hash = crypt(?, password_hash)";
        try {
            PreparedStatement statement = con.prepareStatement(queryUser);

            statement.setString(1, login);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                return new User(login, password);
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String login() {
        return login;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public Object[] data() {
        return new Object[] { login(), password() };
    }
}
