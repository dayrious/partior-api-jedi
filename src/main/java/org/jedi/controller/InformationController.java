package org.jedi.controller;

import org.jedi.model.Planet;
import org.jedi.model.Starship;
import org.jedi.repository.StarshipRepository;
import org.jedi.repository.CrewRepository;
import org.jedi.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/information")
public class InformationController {

    private final StarshipRepository starshipRepository;
    private final CrewRepository crewRepository;
    private final PlanetRepository planetRepository;

    @Autowired
    public InformationController(
            StarshipRepository starshipRepository,
            CrewRepository crewRepository,
            PlanetRepository planetRepository) {
        this.starshipRepository = starshipRepository;
        this.crewRepository = crewRepository;
        this.planetRepository = planetRepository;
    }

    @GetMapping
    public InformationResponse getInformation() {
        Starship starshipInfo = getStarshipInfo();
        int crewCount = getCrewCount();
        boolean isLeiaOnPlanet = isLeiaOnAlderaan();

        return new InformationResponse(starshipInfo, crewCount, isLeiaOnPlanet);
    }

    private Starship getStarshipInfo() {
        Starship starship = starshipRepository.findFirstByOrderById();
        if (starship == null) {
            return new Starship();
        }
        return starship;
    }

    private int getCrewCount() {
        int crewCount = crewRepository.getDeathStarCrewCount();
        return crewCount == 0 ? 0 : crewCount;
    }

    private boolean isLeiaOnAlderaan() {
        Planet leiaPlanet = planetRepository.findByName("Alderaan");
        return leiaPlanet != null && leiaPlanet.hasLeia();
    }
}
