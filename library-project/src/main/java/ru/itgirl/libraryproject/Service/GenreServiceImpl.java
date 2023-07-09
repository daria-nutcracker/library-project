package ru.itgirl.libraryproject.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirl.libraryproject.dto.BookDto;
import ru.itgirl.libraryproject.dto.GenreDto;
import ru.itgirl.libraryproject.models.Author;
import ru.itgirl.libraryproject.models.Book;
import ru.itgirl.libraryproject.models.Genre;


import ru.itgirl.libraryproject.repositories.BookRepository;
import ru.itgirl.libraryproject.repositories.GenreRepository;
import ru.itgirl.libraryproject.repositories.AuthorRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    @Override
    public GenreDto getGenreById (Long id) {
    Genre genre = genreRepository.findById(id).orElseThrow();
    List <Book> books = bookRepository.findAll();
    List <Author> authors = authorRepository.findAll();
    return convertToDto(genre, books, authors);
    }

    private GenreDto convertToDto (Genre genre, List <Book> books,List <Author> authors) {

        List<BookDto> bookDtoList = books
                .stream()
                .filter(book -> book.getGenre().equals(genre))
                .map(book -> BookDto.builder()
                        .name(book.getName())
                        .id(book.getId())
                        .build()
                ).toList();
        return GenreDto.builder()
                .id(genre.getId())
                .name(genre.getName())
               // .books(bookDtoList)
                .build();

    }
}
