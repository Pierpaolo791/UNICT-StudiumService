package com.unict.studium.scraper.scraper;

import com.unict.studium.scraper.model.CorsoDiStudio;
import com.unict.studium.scraper.model.Materia;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Document;

public class ScraperMateria extends Scraper {

    private final String DIV_CLASS_DEP = "home_cats";

    public ScraperMateria(String anno) {
        super(anno);
    }

    public List<Materia> getSubjectsOfCorses(List<CorsoDiStudio> myCourses) {
        String URL = "http://studium.unict.it/dokeos/" + anno + "/index.php?category=";
        List<Materia> materie = new LinkedList<>();
        myCourses.stream()
                .map(x -> getSubjectsOfCorse(getDocument(URL + x.getId()), x))
                .forEach(x -> materie.addAll(x));
        return materie;
    }

    public List<Materia> getSubjectsOfCorses(String id) {

        String URL = "http://studium.unict.it/dokeos/" + anno + "/index.php?category=";
        List<CorsoDiStudio> myCourses = new ScraperCorsoDiStudio(anno).getListCourses(id);
        List<Materia> materie = new ArrayList<>();
        myCourses.stream()
                .map(x -> getSubjectsOfCorse(getDocument(URL + x.getId()), x))
                .forEach(x -> materie.addAll(x));
        return materie;
    }

    private String parseStringOfIdSubject(String link) {
        /* A volte (in alcuni insegnamenti, non ho ancora individuato il motivo esatto)
         il l'id del corso si trova in nella stringa "syllabus?cid=18670/", oppure direttamente ..path.../18670/ 
         faccio il parsing della stringa*/
        if (link.contains("syllabus")) {
            return link.split("=")[1];
        } else {
            return link;
        }
    }

    public List<Materia> getSubjectsOfCorse(Document doc, CorsoDiStudio corso) {
        return doc.getElementsByClass(DIV_CLASS_DEP)
                .get(0)
                .getElementsByTag("ul")
                .get(0)
                .getElementsByTag("li")
                .stream()
                .map(x -> (new Materia()
                        .setId(parseStringOfIdSubject(x.getElementsByTag("a").get(0).attr("href").split("/")[6]))
                        .setName(x.getElementsByTag("a").text())
                        .setIdCorsoDiStudio(corso.getId()))
                        .setAnno(getAnnoAccademico(x.getElementsByTag("span").text()))
                        .setSemestre(getSemestre(x.getElementsByTag("span").text()))
                        .setAnnoAccademico(Integer.parseInt(anno))
                )
                .collect(Collectors.toList());
    }

    public int getAnnoAccademico(String id) {
        id = id.split(" ")[0];
        String content = getPageContent("http://188.213.170.165/OPIS-Manager/API/public/index.php/api/materia/" + id);
        String annoAccademico = "0";
        JSONArray jsonAr;
        try {
            jsonAr = new JSONArray(content);
            JSONObject jsonO = jsonAr.getJSONObject(0);
            annoAccademico = (String) jsonO.get("anno");
        } catch (JSONException ex) {

        }
        return Integer.parseInt(annoAccademico);
    }

    public String getSemestre(String id) {
        id = id.split(" ")[0];
        String content = getPageContent("http://188.213.170.165/OPIS-Manager/API/public/index.php/api/materia/" + id);
        String semestre = "0";
        JSONArray jsonAr;
        try {
            jsonAr = new JSONArray(content);
            JSONObject jsonO = jsonAr.getJSONObject(0);
            semestre = (String) jsonO.get("semestre");
        } catch (JSONException ex) {
        }
        return semestre;
    }

    public static String getPageContent(String url) {
        String result = null;
        URLConnection connection = null;
        try {
            connection = new URL(url).openConnection();
            try (Scanner scanner = new Scanner(connection.getInputStream())) {
                scanner.useDelimiter("\\\\Z");
                result = scanner.next();
            }
        } catch (IOException ex) {

        }
        return result;
    }
}
