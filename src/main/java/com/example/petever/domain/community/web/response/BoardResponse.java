package com.example.petever.domain.community.web.response;

import com.example.petever.domain.community.domain.CommunityBoard;
import com.example.petever.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResponse {

    private String id;
    private String title;
    private String contents;
    private String preview;
    // Author 객체
    private User author;
    private Integer commentCount;
    private Integer viewCount;

    public BoardResponse(CommunityBoard board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.preview = board.getContents();
        this.author = board.getAuthor();
        this.commentCount = board.getCommentCount();
        this.viewCount = board.getViewCount();
    }
}
