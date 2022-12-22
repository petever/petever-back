package com.example.petever.domain.notion.persistence;

import com.example.petever.domain.notion.domain.notion.block.NotionBlocks;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotionBlocksRepository extends MongoRepository<NotionBlocks, String> {
}
