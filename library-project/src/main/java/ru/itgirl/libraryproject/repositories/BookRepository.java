package ru.itgirl.libraryproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itgirl.libraryproject.models.Book;

import java.util.Optional;
public interface BookRepository extends JpaRepository<Book,Long> {
Optional <Book> findBookByName(String name);
}
