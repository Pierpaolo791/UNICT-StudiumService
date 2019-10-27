package com.unict.studium.scraper.scraper;

import com.unict.studium.scraper.model.Avviso;
import com.unict.studium.scraper.model.Materia;
import org.jsoup.nodes.Document;

public class ScraperAvviso extends Scraper {

    public ScraperAvviso(String anno) {
        super(anno);
    }

    public int getLastAvviso(Materia m) {
        Document doc = super.getDocument("http://studium.unict.it/dokeos/2020/main/announcements/announcements.php?cidReq=" + m.getId());
        if (doc == null) {
            return 0;
        }
        if (doc.getElementsByClass("announcement_list_item").isEmpty()) {
            return 0;
        }
        String lastAvviso = doc.getElementsByClass("announcement_list_item").get(0).attr("id");
        if (lastAvviso.isEmpty()) {
            return 0;
        }
        int avviso = Integer.parseInt(lastAvviso.replace("announcement", ""));
        return avviso;
    }

    public Avviso getAvviso(Materia m) {
        int lastAvviso = getLastAvviso(m);
        if (lastAvviso == 0) {
            return null; // non ha trovato nessun avviso.
        }
        String link = "http://studium.unict.it/dokeos/" + super.anno + "/main/announcements/announcements.php?cidReq=" + m.getId() + "&action=view&ann_id="
                + lastAvviso;
        return getAvvisoFromLink(link).setIdSubject(m.getId()).setId("" + lastAvviso);
    }

    private Avviso getAvvisoFromLink(String link) {
        Document doc = getDocument(link);
        if (doc == null) {
            return null;
        }

        if (doc.getElementsByClass("announcement_title").text().isEmpty()
                || doc.getElementsByClass("announcement_sender").text().isEmpty()) {
            return null; // la news non esiste ancora
        }
        Avviso x = new Avviso();
        x.setDate(doc.getElementsByClass("announcement_date").text());
        x.setTeacher(doc.getElementsByClass("announcement_sender").text());
        x.setTitle(doc.getElementsByClass("announcement_title").text());
        x.setText(doc.getElementsByClass("announcement_content").text());

        return x;

    }

}
