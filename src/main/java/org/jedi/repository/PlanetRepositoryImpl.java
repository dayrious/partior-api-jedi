package org.jedi.repository;

import org.jedi.model.Planet;
import org.springframework.stereotype.Component;

@Component
public class PlanetRepositoryImpl implements PlanetRepository {
    @Override
    public Planet findByName(String name) {
        // Simulate check on if Leia is on Alderaan from DB
        if (name.equals("Alderaan")) {
            return new Planet("Alderaan", true);
        } else {
            return new Planet("Coruscant", false);
        }
    }
}