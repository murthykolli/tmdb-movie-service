package com.selfpro.tmdb.api;

import com.selfpro.tmdb.model.Movie;
import com.selfpro.tmdb.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) {
        Movie movie = movieService.read(id);
        log.info("Retrieved movie data with id: {}", id);
        return ResponseEntity.ok(movie);
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        Movie movie1 = movieService.create(movie);
        log.info("Created movie data with id: {}", movie1.getId());
        return ResponseEntity.ok(movie1);
    }

    @PutMapping("/{id}")
    public void updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        movieService.update(id, movie);
        log.info("Updated movie data with id: {}", id);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.delete(id);
        log.info("Deleted movie data with id: {}", id);
    }


}
