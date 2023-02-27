package com.example.petever.domain.community.domain;

import com.example.petever.domain.community.web.BoardType;
import com.example.petever.domain.community.web.request.BoardRequest;
import com.example.petever.domain.user.domain.SocialUser;
import com.example.petever.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document(collection = "community_board")
@Getter

@NoArgsConstructor
public class CommunityBoard {

    @Id
    private String id;
    private String title;
    private String contents;
    private Integer viewCount;
    private User author;
    private BoardType boardType;
    private List<Comment> comment;
    private List<Tag> tags;

    public CommunityBoard(BoardRequest boardRequest, User user) {
        this.title = boardRequest.getTitle();
        this.boardType = boardRequest.getBoardType();
        this.contents = boardRequest.getContents();
        this.tags = boardRequest.getTags();
        this.author = user;
    }

    public CommunityBoard(String id, String title, String contents, Integer viewCount, User author, BoardType boardType, List<Comment> comment, List<Tag> tags) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.viewCount = viewCount;
        this.author = author;
        this.boardType = boardType;
        this.comment = comment;
        this.tags = tags;
    }

    public void addAuthor(User user) {
        this.author = user;
    }

    public Integer getCommentCount() {
        if (this.comment != null) {
            return this.comment.size();
        }

        return 0;
    }
}
