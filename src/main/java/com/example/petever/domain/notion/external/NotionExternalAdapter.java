package com.example.petever.domain.notion.external;

import com.example.petever.domain.notion.domain.notion.block.NotionBlock;
import com.example.petever.domain.notion.domain.notion.page.NotionPage;
import com.example.petever.domain.notion.enumuration.NotionAPi;
import com.example.petever.domain.notion.domain.notion.block.NotionBlocks;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NotionExternalAdapter {

    private final RestTemplate restTemplate;
    private final HttpEntity httpEntity;
    private final ObjectMapper snakeCaseObjectMapper;

    public Optional<NotionPage> getNotionPage(String pageId) throws JsonProcessingException {
        ResponseEntity<Map> response = restTemplate.exchange(NotionAPi.PAGE.endPoint(), HttpMethod.GET, httpEntity, Map.class, pageId);
        NotionPage notionPage = null;

        if (response.getStatusCode() == HttpStatus.OK) {
            response.getBody().put("id", pageId);
            notionPage = snakeCaseObjectMapper.readValue(snakeCaseObjectMapper.writeValueAsString(response.getBody()), NotionPage.class);
        }

        return Optional.ofNullable(notionPage);
    }

    public Optional<NotionBlocks> getNotionBlockChildren(String blockId) throws IOException {
        ResponseEntity<Map> response = restTemplate.exchange(NotionAPi.BLOCK_CHILDREN.endPoint(), HttpMethod.GET, httpEntity, Map.class, blockId);
        NotionBlocks notionBlocks = null;

        if (response.getStatusCode() == HttpStatus.OK) {
            response.getBody().put("id", blockId);
            notionBlocks = snakeCaseObjectMapper.readValue(snakeCaseObjectMapper.writeValueAsString(response.getBody()), NotionBlocks.class);
        }

        return Optional.ofNullable(notionBlocks);
    }

    public Optional<NotionBlock> getNotionBlock(String blockId) throws IOException {
        ResponseEntity<Map> response = restTemplate.exchange(NotionAPi.BLOCK.endPoint(), HttpMethod.GET, httpEntity, Map.class, blockId);
        NotionBlock notionBlock = null;

        if (response.getStatusCode() == HttpStatus.OK) {
            notionBlock = snakeCaseObjectMapper.readValue(snakeCaseObjectMapper.writeValueAsString(response.getBody()), NotionBlock.class);
        }

        return Optional.ofNullable(notionBlock);
    }

    public Optional<NotionBlocks> getNotionDatabase(String databaseId) throws IOException {
        ResponseEntity<Map> response = restTemplate.exchange(NotionAPi.DATA_BASE.endPoint(), HttpMethod.POST, httpEntity, Map.class, databaseId);
        NotionBlocks notionBlocks = null;

        if (response.getStatusCode() == HttpStatus.OK) {
            notionBlocks = snakeCaseObjectMapper.readValue(snakeCaseObjectMapper.writeValueAsString(response.getBody()), NotionBlocks.class);
        }

        return Optional.ofNullable(notionBlocks);
    }
}
