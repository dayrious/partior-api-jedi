package org.jedi.repository;

import org.springframework.stereotype.Component;

@Component
public class CrewRepositoryImpl implements CrewRepository {
    @Override
    public int getDeathStarCrewCount() {
        // Simulate getting crew count for the Death Star from DB
        return 5000;
    }
}
