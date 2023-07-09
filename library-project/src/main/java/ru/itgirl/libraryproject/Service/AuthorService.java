package ru.itgirl.libraryproject.Service;
import ru.itgirl.libraryproject.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    AuthorDto getAuthorById (Long id);
    List<AuthorDto> getGenreById (Long id);

}
