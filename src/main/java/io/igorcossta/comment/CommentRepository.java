package io.igorcossta.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("""
            SELECT new io.igorcossta.comment.CommentDTO(c.commentOwner.username, c.comment, c.createdAt) 
            FROM comment c 
            WHERE c.recipe.id = :id 
            """)
    List<CommentDTO> findAllByRecipeId(Long id);
}
