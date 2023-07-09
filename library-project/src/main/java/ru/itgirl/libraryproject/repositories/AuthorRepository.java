package ru.itgirl.libraryproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itgirl.libraryproject.models.Author;

public interface AuthorRepository extends JpaRepository<Author,Long> {
}
