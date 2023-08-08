package org.jedi.controller;

import org.jedi.model.Planet;
import org.jedi.model.Starship;
import org.jedi.repository.CrewRepository;
import org.jedi.repository.PlanetRepository;
import org.jedi.repository.StarshipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@SpringJUnitConfig
public class InformationControllerTest {

    @InjectMocks
    private InformationController informationController;

    @Mock
    private StarshipRepository starshipRepository;

    @Mock
    private CrewRepository crewRepository;

    @Mock
    private PlanetRepository planetRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        Starship mockStarship = new Starship("Moon Ship", "Moon-class", "M-1");
        when(starshipRepository.findFirstByOrderById()).thenReturn(mockStarship);

        when(crewRepository.getDeathStarCrewCount()).thenReturn(50);

        Planet mockPlanet = new Planet("Alderaan", true);
        when(planetRepository.findByName("Alderaan")).thenReturn(mockPlanet);

        mockMvc = MockMvcBuilders.standaloneSetup(informationController).build();
    }

    @Test
    public void testGetInformation() {
        InformationResponse response = informationController.getInformation();

        assertEquals("Moon Ship", response.getStarship().getName());
        assertEquals("Moon-class", response.getStarship().getShipClass());
        assertEquals("M-1", response.getStarship().getModel());
        assertEquals(50, response.getCrew());
        assertTrue(response.isLeiaOnPlanet());
    }

    @Test
    public void testInformationJsonString() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/information")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String expectedJson = "{\"starship\":{\"name\":\"Moon Ship\",\"model\":\"M-1\",\"class\":\"Moon-class\"},\"crew\":50,\"leiaOnPlanet\":true}";
        String actualJson = mvcResult.getResponse().getContentAsString();

        assertEquals(expectedJson, actualJson);
    }
}