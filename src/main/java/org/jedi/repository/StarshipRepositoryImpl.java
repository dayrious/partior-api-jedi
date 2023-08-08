package org.jedi.repository;

import org.jedi.model.Starship;
import org.springframework.stereotype.Component;

@Component
public class StarshipRepositoryImpl implements StarshipRepository {
    @Override
    public Starship findFirstByOrderById() {
        // Simulate the type of startships that is with the Death Star
        return new Starship("Star Destroyer", "Imperial-class", "ISD-1");
    }
}