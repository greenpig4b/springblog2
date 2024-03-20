package shop.mtcoding.blog.reply;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.utils.ApiUtil;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardService;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.user.UserService;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RequiredArgsConstructor
@RestController
public class ReplyController {
    private final ReplyService replyService;
    private final HttpSession session;

    @PostMapping("/api/replys")
    public ResponseEntity<?> save(@RequestBody ReplyRequest.SaveDTO reqDTO) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        Reply reply = replyService.write(reqDTO, sessionUser);

        return ResponseEntity.ok(new ApiUtil(reply));
    }

    @DeleteMapping("/api/replys/{id}")
    public ResponseEntity<?> delete(@RequestBody @PathVariable Integer id) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        replyService.delete(id,sessionUser);

        return ResponseEntity.ok(new ApiUtil(null));
    }

}
