package ru.itgirl.libraryproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthorCreateDto {
    @Size(min=3, max=10)
    @NotBlank(message = "you should write the name")
    private String name;
    @NotBlank(message = "you should write surname!")
    private String surname;
}
