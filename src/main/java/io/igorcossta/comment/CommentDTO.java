package io.igorcossta.comment;

import java.time.ZonedDateTime;

public record CommentDTO(
        String owner,
        String comment,
        ZonedDateTime createdAt
) {

}
