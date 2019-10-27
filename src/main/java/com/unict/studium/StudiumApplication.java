package com.unict.studium;

import com.unict.studium.service.Studium;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudiumApplication {

    public static void main(String[] args) {

        SpringApplication.run(StudiumApplication.class, args);
        Studium s = new Studium("2020");
        s.scrapCdS();
        s.scrapMaterie(); // Scrapa le materie e li inserisce nel DB 
        new Timer().scheduleAtFixedRate(
                new TimerTask() {
                    @Override
                    public void run() {
                        new Studium("2020").scrapAvvisi();
                    }
                }, 60000, 60000);
    }

}
