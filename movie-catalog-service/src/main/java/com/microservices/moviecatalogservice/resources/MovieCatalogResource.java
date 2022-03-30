package com.microservices.moviecatalogservice.resources;

import com.microservices.moviecatalogservice.models.CatalogItem;
import com.microservices.moviecatalogservice.models.Movie;
import com.microservices.moviecatalogservice.models.Rating;
import com.microservices.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        /*
        List<Rating> ratings = Arrays.asList(
                new Rating("1", 4),
                new Rating("2", 5));
         */

        /*
         RestTemplate:
         return ratings.stream().map(rating -> {
         Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
         return new CatalogItem(movie.getMovieName(), movie.getMovieDescription(), rating.getMovieRating());
         }).collect(Collectors.toList());
         */

        // return Collections.singletonList(new CatalogItem("Doctor Strange", "Thriller, Scientific", 5));

        // WebClient
        /*
        return ratings.stream().map(rating -> {
            Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();
            return new CatalogItem(movie.getMovieName(), movie.getMovieDescription(), rating.getMovieRating());
        }).collect(Collectors.toList());
        */

        UserRating userRating = restTemplate.getForObject("http://localhost:8083/ratings/users/" + userId, UserRating.class);

        // RestTemplate:
        return userRating.getUserRating().stream().map(rating -> {
            // For each movieId, call movie-info-service and get the movie details.
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
            // Put them all together.
            return new CatalogItem(movie.getMovieName(), movie.getMovieDescription(), rating.getMovieRating());
        }).collect(Collectors.toList());
    }
}
