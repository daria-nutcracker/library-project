package ru.itgirl.libraryproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ru.itgirl.libraryproject.models.Users;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findByLogin (String login);

}
