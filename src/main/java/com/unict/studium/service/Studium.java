package com.unict.studium.service;

import com.unict.studium.scraper.model.Avviso;
import com.unict.studium.scraper.model.CorsoDiStudio;
import com.unict.studium.scraper.model.Materia;
import com.unict.studium.scraper.repository.AvvisoRepository;
import com.unict.studium.scraper.repository.CorsoDiStudioRepository;
import com.unict.studium.scraper.repository.MateriaRepository;
import com.unict.studium.scraper.scraper.ScraperAvviso;
import com.unict.studium.scraper.scraper.ScraperService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Studium {

    private final String anno;

    public Studium(String anno) {
        this.anno = anno;
    }

    public List<String> getDipartimenti() { // Ancora da implementare
        return Arrays.asList("D251");
    }

    public List<CorsoDiStudio> getCdS() {
        return new CorsoDiStudioRepository().get();
    }

    public List<Materia> getMaterie() {
        return new MateriaRepository().get();
    }

    public List<Avviso> getAvvisi() {
        AvvisoRepository repository = new AvvisoRepository();
        List<Avviso> avvisi = repository.notRead();
        repository.read(avvisi);
        return avvisi;

    }

    public List<Avviso> scrapAvvisi() {
        List<Materia> materie = getMaterie();
        List<Avviso> avvisi = new ArrayList<>();
        ScraperAvviso scraper = new ScraperAvviso("2020");
        materie.stream()
                .map(x -> scraper.getAvviso(x))
                .filter(x -> x != null)
                .forEach(x -> avvisi.add(x));
        new AvvisoRepository().save(avvisi);
        return avvisi;
    }

    public List<String> scrapDipartimenti() { // Ancora da implementare
        //TODO
        return null;
    }

    public List<CorsoDiStudio> scrapCdS() {
        List<CorsoDiStudio> cds = new ScraperService().getAllCorsoDiStudi(anno, "D251");
        new CorsoDiStudioRepository().save(cds);
        return cds;
    }

    public List<Materia> scrapMaterie() {
        List<Materia> materie = new ScraperService().getAllMaterie(anno, getCdS());
        new MateriaRepository().save(materie);
        return materie;
    }
}
