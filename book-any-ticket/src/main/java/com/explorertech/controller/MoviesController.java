package com.explorertech.controller;

import com.explorertech.entities.Movies;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class MoviesController {

    @GetMapping("/moviesList")
    public List<Movies> getAllLatestMovies(){
        List<Movies> moviesList = Arrays.asList(new Movies("Prahlad", new Date(), Double.valueOf(3.5)),
                new Movies("War", new Date(), Double.valueOf(4.0)),
                new Movies("RRR", new Date(), Double.valueOf(4.5)),
                new Movies("Bahubali", new Date(), Double.valueOf(4.7)));

        return moviesList;
    }
}
