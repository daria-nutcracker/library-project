package ru.itgirl.libraryproject.Service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.itgirl.libraryproject.dto.*;
import ru.itgirl.libraryproject.models.Author;
import ru.itgirl.libraryproject.models.Book;
import ru.itgirl.libraryproject.repositories.BookRepository;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public BookDto getByNameV1 (String name) {
        Book book = bookRepository.findBookByName(name).orElseThrow();
        return convertEntityToDto (book);
    }

    private BookDto convertEntityToDto (Book book) {
        return BookDto.builder()
                .id(book.getId())
                .genre(GenreDto.builder()
                        .id(book.getGenre().getId())
                        .name(book.getGenre().getName())
                        .build())
                .name(book.getName())
                .build();
    }

    @Override
    public BookDto getByNameV2 (String name) {
        Book book = bookRepository.findBookByNameBySql(name).orElseThrow();
        return convertEntityToDto(book);
    }

    @Override
    public BookDto getByNameV3 (String name) {
        Specification <Book> specification = Specification.where(new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                return cb.equal(root.get("name"),name);
            }
        });
        Book book = bookRepository.findOne(specification).orElseThrow();

        return convertEntityToDto(book);
    }

    @Override
    public BookDto createBook (BookCreateDto bookCreateDto) {
        Book book = bookRepository.save(convertDtoToEntity(bookCreateDto));
        BookDto bookDto = convertEntityToDto(book);
        return bookDto;
    }

    private Book convertDtoToEntity (BookCreateDto bookCreateDto) {
        return Book.builder()
                .name(bookCreateDto.getName())
                .build();

    }


    private Author convertDtoToEntity (AuthorCreateDto authorCreateDto) {
        return Author.builder()
                .name(authorCreateDto.getName())
                .surname(authorCreateDto.getSurname())
                .build();
    }

    @Override
    public BookDto updateBook (BookUpdateDto bookUpdateDto) {
        Book book = bookRepository.findById(bookUpdateDto.getId()).orElseThrow();
        book.setName(bookUpdateDto.getName());
        Book savedBook = bookRepository.save(book);
        BookDto bookDto = convertEntityToDto(savedBook);
        return bookDto;
    }

    @Override
    public void deleteBook (Long id) {
        bookRepository.deleteById(id);
    }
}