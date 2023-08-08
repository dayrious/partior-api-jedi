package org.jedi.repository;

import org.jedi.model.Starship;

public interface StarshipRepository {
    Starship findFirstByOrderById();
}
