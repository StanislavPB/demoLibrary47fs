package org.demolibrary47fs.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreRequestDto {

    @NotBlank(message = "Genre name is required")
    private String name;

}
