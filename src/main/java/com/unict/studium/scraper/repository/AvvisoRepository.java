package com.unict.studium.scraper.repository;

import com.unict.studium.scraper.model.Avviso;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AvvisoRepository extends Repository {

    public void save(Avviso obj) {
        db.executeUpdate(obj.insertQuery());
    }

    public void save(List<Avviso> list) {
        list.stream()
                .forEach(x -> save(x));
    }

    public void read(List<Avviso> list) {
        list.stream()
                .forEach(x -> read(x));
    }

    public void read(Avviso obj) {
        db.executeUpdate(obj.updateSpammed());
    }

    public List<Avviso> notRead() {
        List<Avviso> avvisi = new ArrayList<>();
        Connection conn = db.openConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(allNotRead());
            while (rs.next()) {
                Avviso x = new Avviso();
                x.setId(rs.getString("id"));
                x.setIdSubject(rs.getString("id_materia"));
                x.setTitle(rs.getString("titolo"));
                x.setText(rs.getString("contenuto"));
                x.setTeacher(rs.getString("docente"));
                x.setDate(rs.getString("data"));
                avvisi.add(x);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Non si è chiusa la connessione al DB");
            }
        }
        return avvisi;
    }

    private String allNotRead() {
        return "SELECT * FROM avvisi WHERE spammed='0';";
    }
}
