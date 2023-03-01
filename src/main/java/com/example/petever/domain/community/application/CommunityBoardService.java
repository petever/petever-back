package com.example.petever.domain.community.application;

import com.example.petever.domain.community.domain.CommunityBoard;
import com.example.petever.domain.community.persistence.CommunityBoardRepository;
import com.example.petever.domain.community.enumuration.BoardType;
import com.example.petever.domain.community.web.request.BoardRequest;
import com.example.petever.domain.community.web.request.BoardUpdateRequest;
import com.example.petever.domain.user.domain.User;
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

    public CommunityBoard write(BoardRequest boardRequest, BoardType boardType, User user) {
        CommunityBoard communityBoard = new CommunityBoard(boardRequest, boardType, user);
        return communityBoardRepository.save(communityBoard);

    }

    public CommunityBoard update(BoardUpdateRequest request, BoardType boardType, String boardId,  User user) {
        CommunityBoard foundBoard = communityBoardRepository.findByBoardTypeAndId(boardType, boardId);

        if (!foundBoard.isOwner(user)) {
            throw new RuntimeException("작성자만 수정이 가능합니다");
        }

        foundBoard.change(request);
        return communityBoardRepository.save(foundBoard);
    }

    public void delete(BoardType boardType, String boardId, User user) {
        CommunityBoard foundBoard = communityBoardRepository.findByBoardTypeAndId(boardType, boardId);

        if (!foundBoard.isOwner(user)) {
            throw new RuntimeException("작성자만 삭제가 가능합니다");
        }

        communityBoardRepository.delete(foundBoard);
    }

}
