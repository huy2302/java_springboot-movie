package com.example.movie3layer.service;

import com.example.movie3layer.dto.GenreDTO;
import com.example.movie3layer.mapper.GenreToGenreDTO;
import com.example.movie3layer.model.Genre;
import com.example.movie3layer.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService{
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private GenreToGenreDTO genreToGenreDTO;
    @Override
    public List<GenreDTO> getAllGenre() {
        List<Genre> genres = genreRepository.findAll();

        return genres.stream().map(genreToGenreDTO::apply).collect(Collectors.toList());
    }

    @Override
    public GenreDTO getGenreByID(int id) {
        Genre genre = genreRepository.getGenreByID(id);

        return genreToGenreDTO.apply(genre);
    }

    @Override
    public Boolean saveMovieAdmin(GenreDTO genreDTO) {
        try {
            genreRepository.addGenre(genreDTO.getGenre(), genreDTO.getGenreVie());
            return true;
        } catch (Exception e) {
            System.out.println("Ex: " + e);
            return false;
        }
    }

    @Override
    public void removeGenre(String id) {
        try {
            genreRepository.deleteMovieGenre(Integer.parseInt(id));
            genreRepository.deleteById(Integer.parseInt(id));
            if (true) {}
        } catch (Exception e) {
        }
    }

//    @Override
//    public List<GenreDTO> getGenreByMovieID(String movie_id) {
//        List<Genre> genres = genreRepository.findAll();
//
//        return genres.stream().map(genreToGenreDTO::apply).collect(Collectors.toList());
//    }
}
