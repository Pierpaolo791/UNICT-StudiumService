package com.unict.studium.scraper.repository;

import com.unict.studium.scraper.database.FacadeJDBC;

public abstract class Repository {

    protected FacadeJDBC db;

    public Repository() {
        db = new FacadeJDBC();
    }

}
