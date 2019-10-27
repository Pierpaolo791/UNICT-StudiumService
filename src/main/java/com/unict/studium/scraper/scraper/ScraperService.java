package com.unict.studium.scraper.scraper;

import com.unict.studium.scraper.model.CorsoDiStudio;
import com.unict.studium.scraper.model.Materia;
import java.util.List;

public class ScraperService {

    public List<CorsoDiStudio> getAllCorsoDiStudi(String anno, String id) {
        return new ScraperCorsoDiStudio(anno).getListCourses(id);
    }

    public List<Materia> getAllMaterie(String anno, List<CorsoDiStudio> cdl) {
        return new ScraperMateria(anno).getSubjectsOfCorses(cdl);
    }
    /*
     public List<Avviso> getAllAvvisi(String anno, List<Materia> materie) {
     return new ScraperAvviso(anno).getAvvisiOfMaterie(materie);
     }*/

}
