package ru.itgirl.libraryproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itgirl.libraryproject.models.Book;
public interface BookRepository extends JpaRepository<Book,Long> {
}
