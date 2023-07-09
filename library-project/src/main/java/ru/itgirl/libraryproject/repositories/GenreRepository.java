package ru.itgirl.libraryproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itgirl.libraryproject.models.Genre;
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
