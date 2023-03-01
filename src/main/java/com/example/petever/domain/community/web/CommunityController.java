package com.example.petever.domain.community.web;

import com.example.petever.domain.community.application.CommunityBoardService;
import com.example.petever.domain.community.domain.CommunityBoard;
import com.example.petever.domain.community.enumuration.BoardType;
import com.example.petever.domain.community.web.request.BoardRequest;
import com.example.petever.domain.community.web.response.BoardResponse;
import com.example.petever.domain.community.web.request.BoardUpdateRequest;
import com.example.petever.domain.user.application.UserSessionService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
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

    @PostMapping("/{boardType}")
    public void write(HttpServletRequest request, @RequestBody BoardRequest boardRequest, @PathVariable BoardType boardType) {
        communityBoardService.write(boardRequest, boardType, userSessionService.getUserSession(request));
    }

    @DeleteMapping("/{boardType}/{id}")
    public void delete(HttpServletRequest request, @PathVariable BoardType boardType, @PathVariable String id) {
        communityBoardService.delete(boardType, id, userSessionService.getUserSession(request));
    }

    @PatchMapping("/{boardType}/{id}")
    public void update(HttpServletRequest request, BoardRequest boardRequest, @PathVariable BoardType boardType, @PathVariable String id) {
        communityBoardService.update(boardRequest, boardType, id, userSessionService.getUserSession(request));
    }

    @GetMapping("/session")
    public String session(HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println("session id = " + session.getId());
        System.out.println("session user = " + session.getAttribute("user"));
        return session.getId();
    }
}
