package com.unict.studium.scraper.scraper;

import com.unict.studium.scraper.model.CorsoDiStudio;
import java.util.List;
import java.util.stream.Collectors;

public class ScraperCorsoDiStudio extends Scraper {

    private final String DIV_CLASS_DEP = "home_cats";

    public ScraperCorsoDiStudio(String anno) {
        super(anno);
    }

    public List<CorsoDiStudio> getListCourses(String id) {
        return getDocument(assemblerLink(anno, id)).getElementsByClass(DIV_CLASS_DEP)
                .get(0)
                .getElementsByTag("ul")
                .get(0)
                .getElementsByTag("a")
                .stream()
                .map(x -> new CorsoDiStudio(id, anno)
                        .setId(x.attr("href").split("=")[1])
                        .setName(x.text())
                        .setIdDipartimento(id)
                        .setAnno(anno)
                )
                .collect(Collectors.toList());
    }

    private String assemblerLink(String anno, String id) {
        return "http://studium.unict.it/dokeos/"
                .concat(anno)
                .concat("/index.php?category=")
                .concat(id);
    }

}
