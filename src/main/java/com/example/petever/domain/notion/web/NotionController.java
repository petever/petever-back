package com.example.petever.domain.notion.web;

import com.example.petever.domain.notion.application.NotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/notion")
@RequiredArgsConstructor
public class NotionController {

    private final NotionService notionService;

    @GetMapping("/pages/{pageId}")
    public void page(@PathVariable String pageId) {
        notionService.getPage(pageId);
    }
}
