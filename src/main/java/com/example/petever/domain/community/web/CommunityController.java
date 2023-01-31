package com.example.petever.domain.community.web;

import com.example.petever.domain.community.application.CommunityBoardService;
import com.example.petever.domain.community.domain.CommunityBoard;
import com.example.petever.domain.community.web.request.BoardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/community")
@RestController
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityBoardService communityBoardService;

    @GetMapping("/boards")
    public List<CommunityBoard> boards() {
        return communityBoardService.boards();
    }

    @GetMapping("/boards/{id}")
    public CommunityBoard board() {
        return communityBoardService.board();
    }

    @PostMapping("/board")
    public void write(@RequestBody BoardRequest boardRequest) {
        communityBoardService.write(boardRequest);
    }
}
