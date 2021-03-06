package com.microservices.movieinfoservice.resources;

import com.microservices.movieinfoservice.models.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieResource {
    @GetMapping("/{movieId}")
    private Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        return new Movie(movieId, "Doctor Strange", "Action, Fantasy, Superhero, Science Fiction, Adventure");
    }
}
