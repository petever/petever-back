package com.example.petever.domain.community.domain;

import com.example.petever.domain.community.enumuration.BoardType;
import com.example.petever.domain.community.web.request.BoardRequest;
import com.example.petever.domain.community.web.request.BoardUpdateRequest;
import com.example.petever.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.CollectionUtils;

import javax.persistence.Id;
import java.util.Collections;
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

    public CommunityBoard(BoardRequest boardRequest, BoardType boardType, User user) {
        this.title = boardRequest.getTitle();
        this.boardType = boardType;
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

    public void changeTitle(String title) {
        this.title = title;
    }

    public boolean isOwner(User user) {
        return user.equals(author);
    }

    public void change(BoardRequest request) {
        if (request.getTitle() != null) {
            this.title = request.getTitle();
        }

        if (request.getContents() != null) {
            this.contents = request.getContents();
        }

        if (!CollectionUtils.isEmpty(request.getTags())) {
            this.tags = request.getTags();
        }
    }
}
