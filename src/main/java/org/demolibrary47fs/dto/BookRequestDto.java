package org.demolibrary47fs.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDto {

    @NotBlank(message = "Book title is required")
    private String title;

    @NotBlank(message = "Book author is required")
    private String author;

    private List<String> genres;

}
