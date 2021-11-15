package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class RemoveNeighbourFavoriteEvent {

    public Neighbour neighbour;



    public RemoveNeighbourFavoriteEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }

}
