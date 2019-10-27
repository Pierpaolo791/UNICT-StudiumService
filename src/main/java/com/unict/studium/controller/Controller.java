package com.unict.studium.controller;

import com.google.gson.Gson;
import com.unict.studium.scraper.model.Avviso;
import com.unict.studium.service.Studium;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Value("${token.app}")
    private String token;
    @Value("${anno.app}")
    private String anno;

    @RequestMapping(value = "/")
    public String home() {
        return anno;
    }

    @RequestMapping(value = "/materie")
    public String materie(HttpServletRequest req) {
        if (req.getParameter("token").equals(token)) {
            return new Gson().toJson(new Studium(req.getParameter("anno")).getMaterie());
        } else {
            return "404";
        }
    }

    @RequestMapping(value = "/cdl")
    public String cdl(HttpServletRequest req) {
        if (req.getParameter("token").equals(token)) {
            return new Gson().toJson(new Studium(req.getParameter("anno")).getCdS());
        } else {
            return "404";
        }
    }

    @RequestMapping(value = "/avvisi")
    public String avvisi(HttpServletRequest req) {
        if (!req.getParameter("token").equals(token)) {
            return "404";
        }
        Studium s = new Studium(req.getParameter("anno"));
        List<Avviso> avvisiNonLetti = s.getAvvisi();

        return new Gson().toJson(avvisiNonLetti);
    }

    @RequestMapping(value = "/scraping")
    public String scraper(HttpServletRequest req) {
        if (!req.getParameter("token").equals(token)) {
            return "404";
        }
        Studium s = new Studium(req.getParameter("anno"));
        s.scrapAvvisi();

        return "scraped";
    }

    public String getAnno() {
        return anno;
    }

}
