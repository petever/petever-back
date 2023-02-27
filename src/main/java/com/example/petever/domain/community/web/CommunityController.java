package com.example.petever.domain.community.web;

import com.example.petever.domain.community.application.CommunityBoardService;
import com.example.petever.domain.community.domain.CommunityBoard;
import com.example.petever.domain.community.web.request.BoardRequest;
import com.example.petever.domain.user.application.UserSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequestMapping("/community")
@RestController
@RequiredArgsConstructor
public class CommunityController {

    private final UserSessionService userSessionService;
    private final CommunityBoardService communityBoardService;

    @GetMapping("/{boardType}")
    public List<BoardResponse> boards(@PathVariable BoardType boardType) {
        List<CommunityBoard> boards = communityBoardService.boards(boardType);
        return boards.stream()
                .filter(Objects::nonNull)
                .map(BoardResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{boardType}/{id}")
    public BoardResponse board(@PathVariable BoardType boardType, @PathVariable String id) {
        CommunityBoard board = communityBoardService.board(boardType, id);
        return new BoardResponse(board);
    }

    @PostMapping
    public void write(HttpServletRequest request, BoardRequest boardRequest) {
        communityBoardService.write(boardRequest, userSessionService.getUserSession(request));
    }
}
