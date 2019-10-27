package com.unict.studium.scraper.scraper;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Scraper {

    protected String anno;

    public Scraper(String anno) {
        this.anno = anno;
    }

    protected Document getDocument(String link) {
        try {
            return Jsoup.connect(link).get();
        } catch (IOException e) {
            System.err.println("Errore nel caricare la pagina " + link);
        }
        return null;
    }
    //public abstract String assemblerLink();
}
