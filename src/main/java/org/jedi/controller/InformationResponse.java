package org.jedi.controller;

import org.jedi.model.Starship;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InformationResponse {
    private Starship starship;
    private int crew;
    private boolean isLeiaOnPlanet;

}
