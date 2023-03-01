package com.example.petever.domain.community.application;

import com.example.petever.domain.community.domain.CommunityBoard;
import com.example.petever.domain.community.domain.Tag;
import com.example.petever.domain.community.web.request.BoardUpdateRequest;
import com.example.petever.domain.community.web.response.BoardResponse;
import com.example.petever.domain.community.enumuration.BoardType;
import com.example.petever.domain.community.web.request.BoardRequest;
import com.example.petever.domain.user.domain.User;
import com.example.petever.domain.user.enumuration.SocialType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void write() throws JsonProcessingException {
        BoardRequest boardRequest = new BoardRequest("제목", "내용", Arrays.asList(new Tag("cat")));
        CommunityBoard board = communityBoardService.write(boardRequest, BoardType.QUESTIONS, new User("213", "124214@naver.com", "", SocialType.KAKAO));
        assertThat(board).isNotNull();

        BoardResponse boardResponse = new BoardResponse(board);
        assertThat(boardResponse.getTitle()).isEqualTo("제목");
    }

    @Test
    @Transactional
    public void boards() throws JsonProcessingException {
        write();
        List<CommunityBoard> boards = communityBoardService.boards(BoardType.QUESTIONS);
        Assertions.assertThat(boards.size()).isGreaterThan(0);
    }

    @Test
    @Transactional
    public void update() {
        BoardRequest boardRequest = new BoardRequest("제목", "내용", Arrays.asList(new Tag("cat")));
        CommunityBoard board = communityBoardService.write(boardRequest, BoardType.QUESTIONS, new User("213", "124214@naver.com", "", SocialType.KAKAO));
        assertThat(board).isNotNull();

        CommunityBoard foundBoard = communityBoardService.board(BoardType.QUESTIONS, board.getId());
        Assertions.assertThat(board.getTitle()).isEqualTo("제목");

        BoardUpdateRequest request = new BoardUpdateRequest("제목변경", board.getContents(), board.getAuthor().getUserId(), board.getTags());
        assertThatThrownBy(() -> communityBoardService.update(request, board.getBoardType() ,board.getId(), new User("21321223", "124214@naver.com", "", SocialType.KAKAO)))
                .isInstanceOf(RuntimeException.class).hasMessage("작성자만 수정이 가능합니다");

        CommunityBoard updatedBoard = communityBoardService.update(request, board.getBoardType(), board.getId(), new User("213", "124214@naver.com", "", SocialType.KAKAO));

        Assertions.assertThat(updatedBoard.getId()).isEqualTo(foundBoard.getId());
        Assertions.assertThat(updatedBoard.getTitle()).isEqualTo("제목변경");
    }

    @Test
    @Transactional
    public void delete() {
        BoardRequest boardRequest = new BoardRequest("제목", "내용", Arrays.asList(new Tag("cat")));
        CommunityBoard board = communityBoardService.write(boardRequest, BoardType.QUESTIONS, new User("213", "124214@naver.com", "", SocialType.KAKAO));
        assertThat(board).isNotNull();

        assertThatThrownBy(() -> communityBoardService.delete(board.getBoardType(), board.getId(), new User("21321223", "124214@naver.com", "", SocialType.KAKAO)))
                .isInstanceOf(RuntimeException.class).hasMessage("작성자만 삭제가 가능합니다");

        communityBoardService.delete(board.getBoardType(), board.getId(), new User("213", "124214@naver.com", "", SocialType.KAKAO));
        CommunityBoard foundBoard = communityBoardService.board(BoardType.QUESTIONS, board.getId());

        Assertions.assertThat(foundBoard).isNull();
    }

    @Test
    public void equals() {
        User user = new User("213", "124214@naver.com", "", SocialType.KAKAO);
        User user2 = new User("213", "124214@naver.com", "", SocialType.KAKAO);

        Assertions.assertThat(user).isEqualTo(user2);
    }
}