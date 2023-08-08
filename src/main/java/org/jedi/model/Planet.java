package org.jedi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Planet {
    private String name;
    private boolean hasLeia;
    public boolean hasLeia() {
        return hasLeia;
    }

}
