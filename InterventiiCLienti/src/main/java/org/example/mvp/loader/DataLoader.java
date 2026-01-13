package org.example.mvp.loader;

import org.example.mvp.entity.InterventieEntity;
import org.example.repository.InterventieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final InterventieRepository repository;

    public DataLoader(InterventieRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Evităm dublarea datelor
        if (repository.count() > 0) {
            return;
        }

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        getClass().getResourceAsStream("/interventii.txt")
                )
        );

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");

            String client = parts[0];
            String descriere = parts[1];
            String locatie = parts[2];
            String stare = parts[3];
            LocalDate data = LocalDate.parse(parts[4]); // format AAAA-MM-DD

            InterventieEntity interventie = new InterventieEntity(client, descriere, locatie, stare, data);
            repository.save(interventie);
        }

        reader.close();
    }
}