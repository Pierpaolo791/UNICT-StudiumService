package com.unict.studium.scraper.repository;

import com.unict.studium.scraper.model.CorsoDiStudio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CorsoDiStudioRepository extends Repository {

    public void save(CorsoDiStudio obj) {
        db.executeUpdate(obj.insertQuery());
    }

    public void save(List<CorsoDiStudio> list) {
        list.stream()
                .forEach(x -> save(x));
    }

    public List<CorsoDiStudio> get() {
        List<CorsoDiStudio> corsi = new ArrayList<>();
        Connection conn = db.openConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(getQuery());
            while (rs.next()) {
                CorsoDiStudio x = new CorsoDiStudio();
                x.setId(rs.getString("id"));
                x.setName(rs.getString("nome"));
                x.setIdDipartimento(rs.getString("id_dipartimento"));
                x.setAnno(rs.getString("anno_accademico"));
                corsi.add(x);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Non si è chiusa la connessione al DB");
            }
        }
        return corsi;
    }

    private String getQuery() {
        return "SELECT * FROM `cds`";
    }

}
