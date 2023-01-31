package com.example.petever.domain.community.application;

import com.example.petever.domain.community.domain.CommunityBoard;
import com.example.petever.domain.community.persistence.CommunityBoardRepository;
import com.example.petever.domain.community.web.request.BoardRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityBoardService {

    private final CommunityBoardRepository communityBoardRepository;
    private final ObjectMapper objectMapper;

    public List<CommunityBoard> boards() {
        return communityBoardRepository.findAll();
    }

    public CommunityBoard board() {
        return null;
    }

    public CommunityBoard write(BoardRequest boardRequest) {
        CommunityBoard communityBoard = objectMapper.convertValue(boardRequest, CommunityBoard.class);
        // TODO: 세션으로 넣어야함
        // communityBoard.setAuthor();
        return communityBoardRepository.save(communityBoard);

    }
}
