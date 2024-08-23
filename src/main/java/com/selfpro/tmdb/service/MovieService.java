package com.selfpro.tmdb.service;

import com.selfpro.tmdb.exception.InvalidDataException;
import com.selfpro.tmdb.exception.NotFoundException;
import com.selfpro.tmdb.model.Movie;
import com.selfpro.tmdb.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional          //THese all are database operations so must use transaction annotation
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    // CRUD Operations   --- Create, Read, Update and Delete

    public Movie create(Movie movie) {
        if(movie == null) {
            throw new InvalidDataException("Invalid Movie: null");
        }
        return movieRepository.save(movie);
    }

    public Movie read(Long id) {
    return movieRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Movie not found with id"+id));
    }

    public void update(Long id, Movie movie) {
        if(movie == null || id == null) {
            throw new InvalidDataException("Invalid movie: null");
        }
        if (movieRepository.existsById(id)) {
            Movie movie1 = movieRepository.getReferenceById(id);
            movie1.setName(movie.getName());
            movie1.setDirector(movie.getDirector());
            movie1.setActors(movie.getActors());
            movieRepository.save(movie1);
        } else {
            throw new NotFoundException("Movie not found with id: "+id);
        }
    }

    public void delete(Long id) {
        if(movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
        } else {
            throw new NotFoundException("Movie not found with id: "+id);
        }
    }

}
