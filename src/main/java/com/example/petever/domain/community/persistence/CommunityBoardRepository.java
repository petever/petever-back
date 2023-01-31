package com.example.petever.domain.community.persistence;

import com.example.petever.domain.community.domain.CommunityBoard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityBoardRepository extends MongoRepository<CommunityBoard, String> {
}
