package ru.itgirl.libraryproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ru.itgirl.libraryproject.models.Author;
import ru.itgirl.libraryproject.models.Book;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Long>, JpaSpecificationExecutor<Author> {
    Optional <Author> findAuthorByName (String name);

//    @Query(nativeQuery = true, value = "SELECT * FROM BOOK, AUTHOR, AUTHOR_BOOK WHERE AUTHOR.id=AUTHOR_BOOK.author_id AND BOOK.id=AUTHOR_BOOK.book_id AND AUTHOR.name=?")
    @Query(nativeQuery = true, value = "SELECT * FROM AUTHOR WHERE AUTHOR.name=?")
    Optional<Author> findAuthorByNameBySql(String name);


}
