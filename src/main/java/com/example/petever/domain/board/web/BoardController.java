package com.example.petever.domain.board.web;

import com.example.petever.domain.board.application.BoardService;
import com.example.petever.domain.board.web.response.BoardSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/boards")
@RequiredArgsConstructor
@CrossOrigin(value = "*")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/summary")
    public List<BoardSummary> summary() {
        return boardService.getSummary();
    }

    @GetMapping("/{boardId}")
    public List<String> getBoard(@PathVariable String boardId) {
        return boardService.getBoard(boardId);
    }
}
