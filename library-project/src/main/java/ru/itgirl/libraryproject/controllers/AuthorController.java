package ru.itgirl.libraryproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itgirl.libraryproject.dto.AuthorCreateDto;
import ru.itgirl.libraryproject.dto.AuthorDto;
import ru.itgirl.libraryproject.Service.AuthorService;
import ru.itgirl.libraryproject.dto.AuthorUpdateDto;

import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/authors")
    String getBooksView(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        return "authors";
    }
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
    AuthorDto createAuthor (@RequestBody AuthorCreateDto authorCreateDto) {
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
