package ru.itgirl.libraryproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.libraryproject.dto.AuthorDto;
import ru.itgirl.libraryproject.Service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/author/{id}")
    AuthorDto getAuthorById (@PathVariable("id") Long id) {
        return authorService.getAuthorById(id);
    }

    @GetMapping("/genre/{id}")
    List<AuthorDto> getGenreById (@PathVariable("id") Long id) {return authorService.getGenreById(id);
    }

}
