package org.jedi.repository;

import org.jedi.model.Planet;

public interface PlanetRepository {
    Planet findByName(String name);
}
