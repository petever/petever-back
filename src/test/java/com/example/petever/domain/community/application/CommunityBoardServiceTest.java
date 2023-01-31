package com.example.petever.domain.community.application;

import com.example.petever.domain.community.domain.CommunityBoard;
import com.example.petever.domain.community.domain.Tag;
import com.example.petever.domain.community.web.BoardResponse;
import com.example.petever.domain.community.web.request.BoardRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class CommunityBoardServiceTest {

    @Autowired
    private CommunityBoardService communityBoardService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void write() {
        BoardRequest boardRequest = new BoardRequest("제목", "내용", Arrays.asList(new Tag("cat")));
        CommunityBoard board = communityBoardService.write(boardRequest);
        assertThat(board).isNotNull();

        BoardResponse boardResponse = objectMapper.convertValue(board, BoardResponse.class);
        assertThat(boardResponse.getTitle()).isEqualTo("제목");
    }

}