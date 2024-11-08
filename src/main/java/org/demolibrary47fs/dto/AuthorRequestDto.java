package org.demolibrary47fs.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.demolibrary47fs.entity.Book;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequestDto {

    @NotBlank(message = "Author name is required")
    private String name;

}
