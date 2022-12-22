package com.example.petever.domain.board.application;

import com.example.petever.domain.notion.domain.notion.block.NotionBlocks;
import com.example.petever.domain.notion.domain.notion.page.NotionPage;
import com.example.petever.domain.notion.persistence.NotionBlocksRepository;
import com.example.petever.domain.notion.persistence.NotionPageRepository;
import com.example.petever.domain.board.web.response.BoardSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final NotionPageRepository notionPageRepository;
    private final NotionBlocksRepository notionBlocksRepository;

    private List<List<String>> getBoards() {
        List<NotionPage> boardSummary = notionPageRepository.findAll();

        List<String> ids = boardSummary.stream()
                .map(NotionPage::getId)
                .collect(Collectors.toList());

        return ids.stream()
                .map(notionBlocksRepository::findById)
                .map(notionBlocks -> notionBlocks.map(NotionBlocks::getContentBlockAndLineSeparator).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

    }

    private List<BoardSummary> getBoardSummary() {
        List<NotionPage> notionPages = notionPageRepository.findAll();

        return notionPages.stream()
                .map(notionPage -> new BoardSummary(notionPage))
                .collect(Collectors.toList());

    }

    // FIXME: 게시물 객체 필요
    public List<String> getBoard(String boardId) {
        return notionBlocksRepository.findById(boardId)
                .map(NotionBlocks::getContentBlockAndLineSeparator)
                .orElseThrow(() -> new RuntimeException("Not Board Contents"));
    }

    public List<BoardSummary> getSummary() {
        return getBoardSummary();
    }
}
