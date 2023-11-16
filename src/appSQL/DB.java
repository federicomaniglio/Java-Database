package appSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;


public class DB {

    private Connection conn;


    public DB() {
        connetti();
    }

    private void connetti() {
        String db = "jdbc:mysql://127.0.0.1:3306/provasql";
        String username = "root";
        String password = "";

        try {
            conn = DriverManager.getConnection(db, username, password);
            if (conn != null)
                System.out.println("connessione avvenuta");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public StringBuilder select() {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        try {
            if (!conn.isValid(5)) {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "SELECT * FROM users";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                result.add(new ArrayList<>(Arrays.asList(rs.getString("id"), rs.getString("name"), rs.getString("surname"), rs.getString("age"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        StringBuilder output = new StringBuilder("");
        for (ArrayList<String> r : result) {
            output.append(r.get(0));
            output.append("\t\t");
            output.append(r.get(1));
            output.append("\t\t");
            output.append(r.get(2));
            output.append("\t\t");
            output.append(r.get(3));

            output.append("\n");
        }

        return output;
    }


    public String insert(String name, String surname, int age) {
        try {
            if (!conn.isValid(5)) {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        String query = "INSERT INTO users(name, surname, age) VALUES(?,?,?)";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setInt(3, age);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return "Inserimento non avvenuto";
        }



        return "Inserimento avvenuto con successo";
    }

}
