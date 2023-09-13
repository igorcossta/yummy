package io.igorcossta.comment;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentCreationDTO {
    @NotEmpty(message = "Please ensure you've written a comment before sending.")
    @Size(min = 1, max = 300, message = "Your comment should be between 1 and 300 characters in length.")
    private String comment;
}
