package io.igorcossta.comment;

import java.time.ZonedDateTime;

public record CommentViewDTO(
        String owner,
        String comment,
        ZonedDateTime createdAt
) {

}
