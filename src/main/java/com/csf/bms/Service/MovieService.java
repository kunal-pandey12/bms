package com.csf.bms.Service;

import com.csf.bms.Dto.MovieDto;
import com.csf.bms.Model.Movie;
import com.csf.bms.Repo.MovieRepo;
import com.csf.bms.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepo movieRepo;

    public MovieDto createMovie(MovieDto movieDto) {

        Movie movie = mapToEntity(movieDto);
        Movie saveMovie = movieRepo.save(movie);
        return mapToDto(saveMovie);
    }

    public MovieDto getMovieById(Long id){
        Movie movie=movieRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Movie not found with id : "+id));
                return mapToDto(movie);
    }

    public List<MovieDto>getAllMovies() {

        List<Movie> movies = movieRepo.findAll();
        return movies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

    }


    public List<MovieDto>getMovieByLanguage(String language){
        List<Movie>movies=movieRepo.findByLanguage(language);
        return movies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<MovieDto> getMovieByGenre(String genre){

        List<Movie> movies=movieRepo.findByLanguage(genre);
        return movies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<MovieDto> searchMovies(String title){

        List<Movie> movies=movieRepo.findByLanguage(title);
        return movies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public MovieDto updateMovie(Long id,MovieDto movieDto){

        Movie movie=movieRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Movie not found with id: "+id));
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setLanguage(movieDto.getLanguage());
        movie.setGenre(movieDto.getGenre());
        movie.setDurationMins(movieDto.getDurationMins());
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setPosterUrl(movieDto.getPosterUrl());

        Movie updateMovie = movieRepo.save(movie);
        return mapToDto(updateMovie);
    }


    public void deleteMovie(Long id){

        Movie movie=movieRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Movie not found with id: "+id));
        movieRepo.delete(movie);
    }


    private MovieDto mapToDto(Movie movie) {
        MovieDto movieDto = new MovieDto();

        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setDescription(movie.getDescription());
        movieDto.setLanguage(movie.getLanguage());
        movieDto.setGenre(movie.getGenre());
        movieDto.setDurationMins(movie.getDurationMins());
        movieDto.setReleaseDate(movie.getReleaseDate());
        movieDto.setPosterUrl(movie.getPosterUrl());
        return movieDto;
    }

    private Movie mapToEntity(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setId(movieDto.getId());
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setLanguage(movieDto.getLanguage());
        movie.setGenre(movieDto.getGenre());
        movie.setDurationMins(movieDto.getDurationMins());
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setPosterUrl(movieDto.getPosterUrl());
        return movie;
    }

}
