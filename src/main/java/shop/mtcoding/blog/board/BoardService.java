package shop.mtcoding.blog.board;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.user.User;

import java.util.Optional;

@RequiredArgsConstructor
@Service // IOC 등록
public class BoardService {
    private final BoardJPARepository boardJPARepository; // D.I 의존성주입

    //글쓰기
    @Transactional
    public Board write(BoardRequest.SaveDTO reqDTO, User sessionUser){
        Board board = boardJPARepository.save(reqDTO.toEntity(sessionUser));
        return board;
    }

    //글수정 Form
    public Board updateForm(Integer boardId){
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다"));

        return board;
    }

    //글수정하기
    @Transactional
    public Board update(Integer boardId,Integer sessionUserId,BoardRequest.UpdateDTO reqDTO){
        //Optional 정리필요

        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다"));

        //권한처리
        if (sessionUserId != board.getUser().getId()){
            throw new Exception403("수정할 권한이없습니다.");
        }

        // 수정
        board.setTitle(reqDTO.getTitle());
        board.setContent(reqDTO.getContent());

        // 더티채킹
        return null;
    }

    //글삭제하기
    @Transactional
    public void delete(Integer boardId,Integer sessionUserId ){
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다"));
        //런타임 입섹션이 터지면 롤백된다.

        //권한처리
        if (sessionUserId != board.getUser().getId()){
            throw new Exception403("수정할 권한이없습니다.");
        }

        boardJPARepository.deleteById(boardId);
    }

}
