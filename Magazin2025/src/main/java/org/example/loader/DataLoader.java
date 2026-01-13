package org.example.loader;

import org.example.entity.ComandaEntity;
import org.example.repository.ComandaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final ComandaRepository repository;

    public DataLoader(ComandaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
// evităm dublarea datelor
        if (repository.count() > 0) {
            return;
        }

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        getClass().getResourceAsStream("/comenzi.txt")
                )
        );

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");

            String client = parts[0];
            String stare = parts[1];
            LocalDate data = LocalDate.parse(parts[2]); // presupunem că în fișier ai AAAA-MM-DD

            ComandaEntity comanda = new ComandaEntity(client, stare, data);
            repository.save(comanda);
        }

        reader.close();

    }
}

