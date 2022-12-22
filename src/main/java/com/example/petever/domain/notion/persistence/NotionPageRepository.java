package com.example.petever.domain.notion.persistence;

import com.example.petever.domain.notion.domain.notion.page.NotionPage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotionPageRepository extends MongoRepository<NotionPage, String> {
}
