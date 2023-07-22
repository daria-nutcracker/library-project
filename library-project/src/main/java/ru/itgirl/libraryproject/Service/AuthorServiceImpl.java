package ru.itgirl.libraryproject.Service;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.itgirl.libraryproject.dto.AuthorDto;
import ru.itgirl.libraryproject.dto.BookDto;
import ru.itgirl.libraryproject.dto.GenreDto;
import ru.itgirl.libraryproject.models.Author;
import ru.itgirl.libraryproject.models.Genre;
import ru.itgirl.libraryproject.repositories.AuthorRepository;
import ru.itgirl.libraryproject.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService{
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    @Override
    public AuthorDto getAuthorById (Long id) {
        Author author = authorRepository.findById(id).orElseThrow();
        return convertToDto(author);
    }

    @Override
    public List <AuthorDto> getGenreById (Long id) {
        List <Author> author = authorRepository.findAll();
        Genre genre = genreRepository.findById(id).orElseThrow();
        return convertToDto(genre, author);
    }

    private AuthorDto convertToDto (Author author) {
        List<BookDto> bookDtoList = author.getBooks()
                .stream()
                .map(book -> BookDto.builder()
                        .genre(GenreDto.builder()
                                .id(book.getGenre().getId())
                                .name(book.getGenre().getName())
                                .build())
                        .name(book.getName())
                        .id(book.getId())
                        .build()
                ).toList();
        return AuthorDto.builder()
                .books(bookDtoList)
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .build();
    }

    private List <AuthorDto> convertToDto (Genre genre, List<Author> authors) {
        ArrayList<AuthorDto> AuthorDtoList = new ArrayList<AuthorDto>();
        for (Author author : authors) {
            List<BookDto> bookDtoList = author.getBooks()
                    .stream()
                    .filter(book -> book.getGenre().equals(genre))
                    .map(book -> BookDto.builder()
                            .genre(GenreDto.builder()
                                    .id(book.getGenre().getId())
                                    .name(book.getGenre().getName())
                                    .build())
                            .name(book.getName())
                            .id(book.getId())
                            .build()
                    ).toList();
            if (!bookDtoList.isEmpty())
                AuthorDtoList.add(AuthorDto.builder()
                    .books(bookDtoList)
                    .id(author.getId())
                    .name(author.getName())
                    .surname(author.getSurname())
                    .build());
        }


        return AuthorDtoList;
    }

    @Override
    public AuthorDto getByNameV1 (String name) {
        Author author = authorRepository.findAuthorByName(name).orElseThrow();
        return convertEntityToDto (author);
    }

    private AuthorDto convertEntityToDto (Author author) {
        List<BookDto> bookDtoList = author.getBooks()
                .stream()
                .map(book -> BookDto.builder()
                        .genre(GenreDto.builder()
                                .id(book.getGenre().getId())
                                .name(book.getGenre().getName())
                                .build())
                        .name(book.getName())
                        .id(book.getId())
                        .build()
                ).toList();
        return AuthorDto.builder()
                .books(bookDtoList)
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .build();
    }

    @Override
    public AuthorDto getByNameV2 (String name) {
        Author author = authorRepository.findAuthorByNameBySql(name).orElseThrow();
        return convertEntityToDto(author);
    }

    @Override
    public AuthorDto getByNameV3 (String name) {
        Specification <Author> specification = Specification.where(new Specification<Author>() {
            @Override
            public Predicate toPredicate(Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("name"), name);
            }
        });
        Author author = authorRepository.findOne(specification).orElseThrow();
        return convertEntityToDto(author);
    }
}
