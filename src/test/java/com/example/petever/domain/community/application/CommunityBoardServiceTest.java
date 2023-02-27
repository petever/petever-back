package com.example.petever.domain.community.application;

import com.example.petever.domain.community.domain.CommunityBoard;
import com.example.petever.domain.community.domain.Tag;
import com.example.petever.domain.community.web.BoardResponse;
import com.example.petever.domain.community.web.BoardType;
import com.example.petever.domain.community.web.request.BoardRequest;
import com.example.petever.domain.user.domain.User;
import com.example.petever.domain.user.enumuration.SocialType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class CommunityBoardServiceTest {

    @Autowired
    private CommunityBoardService communityBoardService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void write() throws JsonProcessingException {
        BoardRequest boardRequest = new BoardRequest("제목", "내용", Arrays.asList(new Tag("cat")),  BoardType.QUESTIONS);
        CommunityBoard board = communityBoardService.write(boardRequest, new User("213", "124214@naver.com", "", SocialType.KAKAO));
        assertThat(board).isNotNull();

        BoardResponse boardResponse = new BoardResponse(board);
        assertThat(boardResponse.getTitle()).isEqualTo("제목");
    }

    @Test
    public void boards() {
        List<CommunityBoard> boards = communityBoardService.boards(BoardType.QUESTIONS);
        boards.stream()
                .forEach(System.out::println);
    }
}