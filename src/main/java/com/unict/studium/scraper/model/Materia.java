package com.unict.studium.scraper.model;

public class Materia implements Model {

    private String name;
    private String id;
    private String idCorsoDiStudio;
    private String semestre;
    private int annoAccademico;
    private int anno;

    public Materia() {
    }

    public String getName() {
        return name;
    }

    public Materia setName(String name) {
        name = name.replace("'", "\\'");
        this.name = name;
        return this;
    }

    public String getId() {
        return id;
    }

    public Materia setId(String id) {
        this.id = id;
        return this;
    }

    public Materia setSemestre(String semestre) {
        this.semestre = semestre;
        return this;
    }

    public Materia setAnnoAccademico(int annoAccademico) {
        this.annoAccademico = annoAccademico;
        return this;
    }

    public Materia setAnno(int anno) {
        this.anno = anno;
        return this;
    }

    public Materia setIdCorsoDiStudio(String idCorsoDiStudio) {
        this.idCorsoDiStudio = idCorsoDiStudio;
        return this;
    }

    @Override
    public String insertQuery() {
        return "INSERT INTO `materie` (`id`, `id_cds`, `nome`, `anno_accademico`, `anno`, `semestre`) VALUES ('"
                + this.id + "','" + this.idCorsoDiStudio + "','" + this.name + "','" + this.annoAccademico + "',"
                + "'" + this.anno + "','" + this.semestre + "')";
    }

}
