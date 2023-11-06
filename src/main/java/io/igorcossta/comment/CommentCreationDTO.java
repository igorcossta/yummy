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
    @NotEmpty(message = "{validation.messages.comment.notEmpty}")
    @Size(min = 1, max = 300, message = "{validation.messages.comment.size}")
    private String comment;
}
