package ru.itgirl.libraryproject.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String name;

    @Setter
    @Column(nullable=false)
    private String surname;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;

}
