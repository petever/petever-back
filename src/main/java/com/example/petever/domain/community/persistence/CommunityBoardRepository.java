package com.example.petever.domain.community.persistence;

import com.example.petever.domain.community.domain.CommunityBoard;
import com.example.petever.domain.community.web.BoardType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityBoardRepository extends MongoRepository<CommunityBoard, String> {
    List<CommunityBoard> findByBoardType(BoardType boardType);
    CommunityBoard findByBoardTypeAndId(BoardType boardType, String id);
}
