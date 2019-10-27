package com.unict.studium.scraper.model;

public class Avviso implements Model {

    private String idSubject;
    private String id;
    private String title;
    private String text;
    private String teacher;
    private String date; // La conservo come Stringa perché attualmente voglio solo mostrarla nel corpo della news

    public Avviso setIdSubject(String idSubject) {
        this.idSubject = idSubject;
        return this;
    }

    public Avviso setId(String id) {
        this.id = id;
        return this;
    }

    public void setTitle(String title) {
        title = title.replace("'", "\\'");
        this.title = title;
    }

    public void setText(String text) {
        text = text.replace("'", "\\'");
        this.text = text;
    }

    public void setTeacher(String teacher) {
        teacher = teacher.replace("'", "\\'");
        this.teacher = teacher;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public String insertQuery() {
        return "INSERT INTO `avvisi` (`id`, `id_materia`, `titolo`, `contenuto`, `docente`, `data`,`spammed`) "
                + "VALUES('" + id + "','" + idSubject + "','" + title + "','" + text + "','" + teacher + "','" + date + "','0');";
    }

    public String updateSpammed() {
        return "UPDATE Avvisi SET spammed='1' WHERE id=" + id + " AND id_materia=" + idSubject;
    }

    public Avviso() {

    }

    public String getIdSubject() {
        return idSubject;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getDate() {
        return date;
    }

}
