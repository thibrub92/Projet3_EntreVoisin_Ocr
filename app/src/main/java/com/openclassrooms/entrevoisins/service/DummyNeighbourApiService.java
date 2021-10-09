package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public List<Neighbour>  getFavoriteNeighbours() {
        List <Neighbour> neighboursFavoriteList= new ArrayList<>();

        for( Neighbour neigh : neighbours) {

            if (neigh.isFavorite()) {
                neighboursFavoriteList.add(neigh);
            }
        }
        return neighboursFavoriteList;
    }

    @Override
    public void invertNeighbourFavoriteStatus(Neighbour neighbour) {
        neighbour.setFavorite(!neighbour.isFavorite());
        neighbours.set(neighbours.indexOf(neighbour),neighbour);

    }
}
