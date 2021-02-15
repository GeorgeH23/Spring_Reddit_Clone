package com.example.demo.service;

import com.example.demo.exceptions.SpringRedditException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CommentServiceTest {

    @Test
    @DisplayName("Test Should Pass When Comment do not Contains Swear Words")
    void shouldNotContainSwearWordsInsideComment() {
        CommentService commentService = new CommentService(null, null, null, null, null, null, null);
        // Junit 5
        assertFalse(commentService.containsSwearWords("This is a comment"));
        // AssertJ
        assertThat(commentService.containsSwearWords("This is a comment")).isFalse();
    }

    @Test
    @DisplayName("Should Throw Exception when Exception Contains Swear Words")
    void shouldFailWhenCommentContainsSwearWords() {
        CommentService commentService = new CommentService(null, null, null, null, null, null, null);
        // Junit 5
        SpringRedditException exception = assertThrows(SpringRedditException.class, () -> {
            commentService.containsSwearWords("This is shitty comment");
        });
        assertTrue(exception.getMessage().contains("Comments contains unacceptable language"));
        // AssertJ
        assertThatThrownBy(() -> commentService.containsSwearWords("This is a shitty comment"))
                .isInstanceOf(SpringRedditException.class)
                .hasMessage("Comments contains unacceptable language");
    }
}