package ru.itgirl.libraryproject.controllers.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itgirl.libraryproject.dto.AuthorCreateDto;
import ru.itgirl.libraryproject.dto.AuthorDto;
import ru.itgirl.libraryproject.Service.AuthorService;
import ru.itgirl.libraryproject.dto.AuthorUpdateDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "library-users")
public class AuthorRestController {
    private final AuthorService authorService;

    @GetMapping("/author/{id}")
    AuthorDto getAuthorById (@PathVariable("id") Long id) {
        return authorService.getAuthorById(id);
    }

    @GetMapping("/genre/{id}")
    List<AuthorDto> getGenreById (@PathVariable("id") Long id) {return authorService.getGenreById(id);
    }

    @GetMapping ("/author")
    AuthorDto getAuthorByName(@RequestParam("name") String name) {
        return authorService.getByNameV1(name);
    }

    @GetMapping("/author/v2")
    AuthorDto getAuthorByNameV2 (@RequestParam("name") String name) {
        return authorService.getByNameV2(name);
    }

    @GetMapping("/author/v3")
    AuthorDto getAuthorByNameV3 (@RequestParam("name") String name) {
        return authorService.getByNameV3(name);
    }

    @PostMapping("/author/create")
    AuthorDto createAuthor (@RequestBody @Valid AuthorCreateDto authorCreateDto) {
        return authorService.createAuthor(authorCreateDto);
    }

    @PutMapping("/author/update")
    AuthorDto updateAuthor (@RequestBody AuthorUpdateDto authorUpdateDto) {
        return authorService.updateAuthor(authorUpdateDto);
    }


    @DeleteMapping ("/author/delete/{id}")
    void updateAuthor (@PathVariable("id") Long id) {
        authorService.deleteAuthor(id);
    }
}
