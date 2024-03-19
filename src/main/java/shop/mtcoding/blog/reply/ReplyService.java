package shop.mtcoding.blog.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardJPARepository;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyJPARepository replyJPARepository;
    private final BoardJPARepository boardJPARepository;

    //댓글쓰기
    public void write(ReplyRequest.SaveDTO reqDTO, User sessionUSer) {
        Board board = boardJPARepository.findById(reqDTO.getBoardId())
                .orElseThrow(() -> new Exception404("찾을 수 없습니다"));

        Reply reply = reqDTO.toEntity(sessionUSer, board);

        replyJPARepository.save(reply);
    }

    //댓글삭제
    public void delete(Integer replyId, User sessionUser) {
        Board board = boardJPARepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception404("댓글을 찾을 수가 없습니다."));
        board.getId();
        if (sessionUser.getId() != board.getUser().getId()){
            throw  new Exception400("삭제할 권한이 없습니다.");
        }

        replyJPARepository.deleteById(replyId);

    }
}
