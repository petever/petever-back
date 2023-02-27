package com.example.petever.domain.community.application;

import com.example.petever.domain.community.domain.CommunityBoard;
import com.example.petever.domain.community.persistence.CommunityBoardRepository;
import com.example.petever.domain.community.web.BoardType;
import com.example.petever.domain.community.web.request.BoardRequest;
import com.example.petever.domain.user.domain.SocialUser;
import com.example.petever.domain.user.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityBoardService {

    private final CommunityBoardRepository communityBoardRepository;

    public List<CommunityBoard> boards(BoardType boardType) {
        return communityBoardRepository.findByBoardType(boardType);
    }

    public CommunityBoard board(BoardType boardType, String boardId) {
        return communityBoardRepository.findByBoardTypeAndId(boardType, boardId);
    }

    public CommunityBoard write(BoardRequest boardRequest, User user) {
        CommunityBoard communityBoard = new CommunityBoard(boardRequest, user);
        return communityBoardRepository.save(communityBoard);

    }
}
