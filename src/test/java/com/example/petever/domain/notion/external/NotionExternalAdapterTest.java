package com.example.petever.domain.notion.external;

import com.example.petever.domain.notion.domain.notion.block.NotionBlock;
import com.example.petever.domain.notion.domain.notion.block.NotionBlocks;
import com.example.petever.domain.notion.domain.notion.page.NotionPage;
import com.example.petever.domain.notion.persistence.NotionBlockRepository;
import com.example.petever.domain.notion.persistence.NotionBlocksRepository;
import com.example.petever.domain.notion.persistence.NotionPageRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class NotionExternalAdapterTest {

    @Autowired
    private NotionExternalAdapter notionExternalAdapter;

    @Autowired
    private NotionPageRepository notionPageRepository;

    @Autowired
    private NotionBlockRepository notionBlockRepository;

    @Autowired
    private NotionBlocksRepository notionBlocksRepository;

    private String blockId = "ecebb2ed-ebf1-4f98-b003-52d8c176dff0";
    private String dataBaseId = "bad14153-a13d-4812-8ea4-ca342ce5b2b4";

    @Test
    @DisplayName("노션 데이터베이스 가져오는지?")
    public void getDatabase() throws IOException {
        Optional<NotionBlocks> notionDataBase = notionExternalAdapter.getNotionDatabase(dataBaseId);
        Assertions.assertThat(notionDataBase.isPresent()).isTrue();
    }

    @Test
    @DisplayName("노션 데이터베이스 게시물 아이디를 불러오는지?")
    public void getBoardIds() throws IOException {
        Optional<NotionBlocks> notionDataBase = notionExternalAdapter.getNotionDatabase(dataBaseId);
        Assertions.assertThat(notionDataBase.get().getPageIds().size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("노션 블럭을 불러오는지?")
    public void getBoard() throws IOException {
        Optional<NotionBlock> notionBlock = notionExternalAdapter.getNotionBlock(blockId);
        Assertions.assertThat(notionBlock.isPresent()).isTrue();
    }

    @Test
    @DisplayName("노션 블럭 전체를 불러오는지?")
    public void getBoardContents() throws IOException {
        Optional<NotionBlocks> notionBlocks = notionExternalAdapter.getNotionBlockChildren(blockId);
        Assertions.assertThat(notionBlocks.isPresent()).isTrue();
    }

    @Test
    @DisplayName("노션 페이지를 가져오는지?")
    public void getBoardSummary() throws JsonProcessingException {
        Optional<NotionPage> notionBlocks = notionExternalAdapter.getNotionPage(blockId);
        Assertions.assertThat(notionBlocks.isPresent()).isTrue();
    }

    @Test
    @DisplayName("TEST")
    public void test() throws IOException {
        Optional<NotionBlocks> notionDataBase = notionExternalAdapter.getNotionDatabase(dataBaseId);
        List<String> ids = notionDataBase.map(NotionBlocks::getPageIds).orElse(Collections.singletonList(""));

        ids.stream()
                .forEach(id -> {
                    try {
                        Optional<NotionPage> notionBlock = notionExternalAdapter.getNotionPage(id);
                        notionBlock
                                .map(notionPageRepository::save)
                                .orElseThrow(() -> new RuntimeException("Notion Block is Null"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        ids.stream()
                .forEach(id -> {
                    System.out.println("id = " + id);
                    Optional<NotionBlock> foundNotionBlock = notionBlockRepository.findById(id);
                    System.out.println("foundId.get() = " + foundNotionBlock.orElse(null));
                    System.out.println("foundId.getTitle() = " + foundNotionBlock.map(NotionBlock::getTitle).orElse(null));
                });


        ids.stream()
                .forEach(id -> {
                    try {
                        Optional<NotionBlocks> notionBlocks = notionExternalAdapter.getNotionBlockChildren(id);
                        notionBlocks
                                .map(notionBlocksRepository::save)
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