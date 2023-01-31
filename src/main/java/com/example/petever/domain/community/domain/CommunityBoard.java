package com.example.petever.domain.community.domain;

import com.example.petever.domain.user.domain.User;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document(collection = "community_board")
@NoArgsConstructor
public class CommunityBoard {
    @Id
    private String id;
    private String title;
    private String contents;
    private Integer viewCount;
    private User author;
    private List<Comment> comment;
    private List<Tag> tags;

    public CommunityBoard(String title, String contents, Integer viewCount, User author, List<Comment> comment, List<Tag> tags) {
        this.title = title;
        this.contents = contents;
        this.viewCount = viewCount;
        this.author = author;
        this.comment = comment;
        this.tags = tags;
    }
}
