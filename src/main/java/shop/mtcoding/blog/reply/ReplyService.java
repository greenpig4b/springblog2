package shop.mtcoding.blog.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyJPARepository replyJPARepository;

    //댓글쓰기

    //댓글목록보기
    public Reply findAll(){

        return null;
    }
    //댓글삭제



}
