package shop.mtcoding.blog.reply;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardService;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.user.UserService;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RequiredArgsConstructor
@Controller
public class ReplyController {
    private final ReplyService replyService;
    private final HttpSession session;

    @PostMapping("/api/replys")
    public String save(ReplyRequest.SaveDTO reqDTO) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        replyService.write(reqDTO, sessionUser);

        return "redirect:/board/" + reqDTO.getBoardId();
    }

    @DeleteMapping("/api/replys/{id}")
    public String delete(@PathVariable Integer id) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        replyService.delete(id,sessionUser);

        return "redirect:/board";
    }

}
