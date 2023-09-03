package ru.itgirl.libraryproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ru.itgirl.libraryproject.models.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long>{

}
