package com.example.petever.domain.board.application;

import com.example.petever.domain.board.web.response.BoardBlock;
import com.example.petever.domain.board.web.response.BoardBlocks;
import com.example.petever.domain.board.web.response.BoardTitle;
import com.example.petever.domain.notion.domain.notion.block.NotionBlock;
import com.example.petever.domain.notion.domain.notion.block.NotionBlocks;
import com.example.petever.domain.notion.domain.notion.page.NotionPage;
import com.example.petever.domain.notion.external.NotionExternalAdapter;
import com.example.petever.domain.notion.persistence.NotionBlocksRepository;
import com.example.petever.domain.notion.persistence.NotionPageRepository;
import com.example.petever.domain.board.web.response.BoardSummary;
import com.example.petever.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final NotionPageRepository notionPageRepository;
    private final NotionBlocksRepository notionBlocksRepository;
    private final NotionExternalAdapter notionExternalAdapter;
    private final String DATA_BASE_ID = "bad14153-a13d-4812-8ea4-ca342ce5b2b4";

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

    private BoardTitle makeBoardTitle(String boardId) {
        Optional<NotionPage> notionPages = notionPageRepository.findById(boardId);
        NotionPage page = notionPages.orElseThrow(() -> new RuntimeException("Not Found Board"));
        return new BoardTitle(page.getTitle(), page.getCreated(), page.getEdited());
    }

    // FIXME: 게시물 객체 필요
    public BoardBlocks getBoard(String boardId) {
        List<BoardBlock> boardContents = notionBlocksRepository.findById(boardId)
                .map(NotionBlocks::getContentBlock)
                .orElse(Collections.EMPTY_LIST);

        return new BoardBlocks(makeBoardTitle(boardId), boardContents);
    }

    public List<BoardSummary> getSummary() {
        return getBoardSummary();
    }


    public void synchronization() throws IOException {
        notionPageRepository.deleteAll();
        notionBlocksRepository.deleteAll();

        Optional<NotionBlocks> notionDataBase = notionExternalAdapter.getNotionDatabase(DATA_BASE_ID);
        List<String> ids = notionDataBase.map(NotionBlocks::getPageIds).orElse(Collections.singletonList(""));

        ids.stream()
                .forEach(id -> {
                    try {
                        Optional<NotionPage> notionBlock = notionExternalAdapter.getNotionPage(id);
                        notionBlock
                                .map(notionPage -> {
                                    String savePath = FileUtil.save(notionPage.getImage(), notionPage.getId() + ".png");
                                    notionPage.changeImage(savePath);
                                    return notionPageRepository.save(notionPage);
                                })
                                .orElseThrow(() -> new RuntimeException("Notion Block is Null"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });


        ids.stream()
                .forEach(id -> {
                    try {
                        Optional<NotionBlocks> notionBlocks = notionExternalAdapter.getNotionBlockChildren(id);
                        notionBlocks
                                .map(block -> {
                                    block.getResults().stream()
                                            .forEach(result -> {
                                                if (!result.getImage().isBlank()) {
                                                    String savePath = FileUtil.save(result.getImage(), result.getId() + ".png");
                                                    result.changeImage(savePath);
                                                }
                                            });
                                    return notionBlocksRepository.save(block);
                                })
                                .orElseThrow(() -> new RuntimeException("Notion Blocks is Null"));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                });

        ids.stream()
                .forEach(id -> {
                    System.out.println("id = " + id);
                    Optional<NotionBlocks> foundNotionBlock = notionBlocksRepository.findById(id);

                    List<String> contents = foundNotionBlock.map(NotionBlocks::getContentBlockAndLineSeparator)
                            .orElse(Collections.singletonList(""));

                    System.out.println("contents = " + contents);
                });
    }
}
