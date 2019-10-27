package com.unict.studium.scraper.repository;

import com.unict.studium.scraper.model.Materia;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MateriaRepository extends Repository {

    public void save(Materia obj) {
        db.executeUpdate(obj.insertQuery());
    }

    public void save(List<Materia> list) {
        list.stream()
                .forEach(x -> this.save((Materia) x));
    }

    public List<Materia> get() {
        List<Materia> materie = new ArrayList<>();
        Connection conn = db.openConnection();
        if (conn == null) {
            System.out.println("connessione nulla");
        }
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(getQuery());
            if (stmt == null || rs == null) {
                System.out.println("STMT O RS NULL");
            }
            while (rs.next()) {
                Materia x = new Materia();
                x.setId(rs.getString("id"));
                x.setName(rs.getString("nome"));
                x.setIdCorsoDiStudio(rs.getString("id_cds"));
                x.setAnnoAccademico(Integer.parseInt(rs.getString("anno_accademico")));
                x.setSemestre(rs.getString("semestre"));
                x.setAnno(Integer.parseInt(rs.getString("anno")));
                materie.add(x);
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
        return materie;
    }

    private String getQuery() {
        return "SELECT * FROM `materie`";
    }
}
