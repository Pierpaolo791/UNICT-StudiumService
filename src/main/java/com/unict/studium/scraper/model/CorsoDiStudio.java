package com.unict.studium.scraper.model;

public class CorsoDiStudio implements Model {

    private String id;
    private String name;
    private String idDipartimento;
    private String anno;

    public CorsoDiStudio() {
    }

    public CorsoDiStudio(String idDipartimento, String anno) {
        this.idDipartimento = idDipartimento;
        this.anno = anno;
    }

    public String getId() {
        return id;
    }

    public CorsoDiStudio setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CorsoDiStudio setName(String name) {
        this.name = name;
        return this;
    }

    public String getIdDipartimento() {
        return idDipartimento;
    }

    public CorsoDiStudio setIdDipartimento(String idDipartimento) {
        this.idDipartimento = idDipartimento;
        return this;
    }

    public String getAnno() {
        return anno;
    }

    public CorsoDiStudio setAnno(String anno) {
        this.anno = anno;
        return this;
    }

    @Override
    public String insertQuery() {
        return "INSERT INTO `cds`(`id`, `nome`, `id_dipartimento`, `anno_accademico`) VALUES ('"
                + this.id + "','" + this.name + "','" + this.idDipartimento + "','" + this.anno + "')";
    }

}
